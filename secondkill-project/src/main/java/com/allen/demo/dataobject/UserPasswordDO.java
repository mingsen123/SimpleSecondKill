package com.allen.demo.dataobject;

import lombok.Data;

@Data
public class UserPasswordDO {
    /**
     *用户密码主键
     */
    private Integer id;

    /**
     *用户密码
     */
    private String encrptPassword;

    /**
     *密码对应的用户id
     */
    private Integer userId;

}