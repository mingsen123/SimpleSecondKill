package com.allen.demo.service.impl;

import com.allen.demo.dao.UserDOMapper;
import com.allen.demo.dao.UserPasswordDOMapper;
import com.allen.demo.dataobject.UserDO;
import com.allen.demo.dataobject.UserPasswordDO;
import com.allen.demo.error.BusinessException;
import com.allen.demo.error.EmBusinessError;
import com.allen.demo.service.UserService;
import com.allen.demo.service.model.UserModel;
import com.allen.demo.validator.UserValidation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author allen
 * @date 2021/5/13 20:57
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    //不把user对象直接返回是因为在企业级编程中最好不要把user对象直接返回给前端
    //springmvc中Model对象的定义（在service层中定义的model）就是为了把服务端的数据传入到客户端的浏览器上
    @Override
    public UserModel getUserById(Integer id) {
        //通过userDOMapper获取用户对象
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        //判断userDO对象是否存在，如果不存在返回null
        if(null == userDO){
            return null;
        }
        //通过userId查找userPassword对象
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        //通过convertFromDataObject方法将得到的userDO&userPasswordDO对象传入到model中
        return convertFromDataObject(userDO,userPasswordDO);
    }

    /**
     * 注册方法
     * @param userModel
     * @throws BusinessException
     */
    @Override
    @Transactional
    public void register(UserModel userModel) throws BusinessException {
        //判断入参是否为空
        if(null == userModel){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        /**
         * 第一种非空判断
         * if(StringUtils.isEmpty(userModel.getName()) ||
            null == userModel.getAge() ||
            null == userModel.getGender() ||
            StringUtils.isEmpty(userModel.getTelphone())){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }*/
        /**第二种方法校验器校验，但是报错
         * ValidationResult result = validator.validate(userModel);
        if(result.isHasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrorMsg());
        }*/
        //第三种校验，编写一个类。
        new UserValidation().validate(userModel);
        //实现model转为dataObject方法
        //insertSelective方法会进行判空
        UserDO userDO = convertFromModel(userModel);
        try {
            userDOMapper.insertSelective(userDO);
        }catch (DuplicateKeyException e){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"手机号已被注册");
        }
        userModel.setId(userDO.getId());
        UserPasswordDO userPasswordDO = convertPasswordFromModel(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);
    }

    @Override
    public UserModel validateLogin(String telphone, String encrptPassword) throws BusinessException {
        //通过用户手机获取用户信息
        UserDO userDO = userDOMapper.selectByTelphone(telphone);
        if(null == userDO){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel = convertFromDataObject(userDO, userPasswordDO);
        //比对用户信息内加密密码是否和传输进来的密码匹配
        if(!StringUtils.equals(encrptPassword,userModel.getEncrptPassword())){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        return userModel;
    }

    /**
     * 将userModel中的属性传入到userPasswordDO中的方法
     * @param userModel
     * @return
     */
    private UserPasswordDO convertPasswordFromModel(UserModel userModel){
        if(null == userModel){
            return null;
        }
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());
        userPasswordDO.setUserId(userModel.getId());
        return userPasswordDO;
    }


    /**
     * 将userModel中的属性传入到userDO中
     * @param userModel
     * @return
     */
    private UserDO convertFromModel(UserModel userModel){
        if(null == userModel){
            return null;
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel,userDO);
        return userDO;
    }

    /**
     * 将实体类中的属性数据传入到Model中的方法
     * @param userDO
     * @param userPasswordDO
     * @return
     */
    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO){
        //判断userDO是否为空
        if(null == userDO){
            return null;
        }
        //将UserDO中的属性拷贝到userModel中
        UserModel userModel = new UserModel();
        /**Apache Common BeanUtil是一个常用的在对象之间复制数据的工具类。
         *著名的web开发框架struts就是依赖于它进行ActionForm的创建。
        /**public void copyProperties(java.lang.Object dest, java.lang.Object orig)
        此方法的作用就是把orig中的值copy到dest中.*/
        BeanUtils.copyProperties(userDO,userModel);
        //这里不能在使用拷贝,因为密码实体类中还有id属性，会造成数据混乱
        //这里使用get&set方法将密码数据传入到userModel中
        if(null != userPasswordDO){
            userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        }
        return userModel;
    }
}
