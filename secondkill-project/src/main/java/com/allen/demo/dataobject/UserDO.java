package com.allen.demo.dataobject;

import lombok.Data;

@Data
public class UserDO {

    /**
     *用户主键
     */
    private Integer id;

    /**
     *用户名
     */
    private String name;

    /**
     *用户性别
     */
    private Byte gender;

    /**
     *年龄
     */
    private Integer age;

    /**
     *手机号
     */
    private String telphone;

    /**
     *注册方式
     */
    private String registerMode;

    /**
     *
     */
    private String thirdPartyId;
}