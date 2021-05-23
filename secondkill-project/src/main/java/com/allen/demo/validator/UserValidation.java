package com.allen.demo.validator;

import com.allen.demo.error.BusinessException;
import com.allen.demo.error.EmBusinessError;
import com.allen.demo.service.model.UserModel;

/**
 * 判断注册信息是否准确
 * @author allen
 * @date 2021/5/17 15:50
 */
public class UserValidation {
    public void validate(UserModel userModel) throws BusinessException {
        if(null == userModel.getName()){
            throw new BusinessException(EmBusinessError.USER_REGISTER_ERROR,"用户名不能为空");
        }
        if(null == userModel.getAge()){
            throw new BusinessException(EmBusinessError.USER_REGISTER_ERROR,"年龄不能为空");
        }
        if(userModel.getAge() >=150){
            throw new BusinessException(EmBusinessError.USER_REGISTER_ERROR,"年龄不能超过150岁");
        }
        if(userModel.getAge() <= 0){
            throw new BusinessException(EmBusinessError.USER_REGISTER_ERROR,"年龄不能低于0岁");
        }
        if(null == userModel.getGender()){
            throw new BusinessException(EmBusinessError.USER_REGISTER_ERROR,"性别不能为空");
        }
        if(null == userModel.getTelphone()){
            throw new BusinessException(EmBusinessError.USER_REGISTER_ERROR,"手机号不能为空");
        }
        if(null == userModel.getEncrptPassword()){
            throw new BusinessException(EmBusinessError.USER_REGISTER_ERROR,"密码不能为空");
        }
    }
}
