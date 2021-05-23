package com.allen.demo.service;

import com.allen.demo.error.BusinessException;
import com.allen.demo.service.model.UserModel;

/**
 * @author allen
 * @date 2021/5/13 20:57
 */
public interface UserService {
    /**
     * 通过id获取User对象
     * @param id
     */
    UserModel getUserById(Integer id);

    /**
     * 注册方法
     * @param userModel  前端表单
     * @throws BusinessException 注册过程中可能产生的错误
     */
    void register(UserModel userModel) throws BusinessException;

    /**
     * 登陆方法
     * @param telphone 手机号
     * @param encrptPassword 密码
     */
    UserModel validateLogin(String telphone,String encrptPassword) throws BusinessException;
}
