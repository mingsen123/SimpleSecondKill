package com.allen.demo.service.model;

/**
 * @author allen
 * @date 2021/5/13 21:05
 */


import lombok.Data;

/**
 * Model类
 * 作用：为了不把DO类（与数据库映射的实体类直接传入到客户端）创建的类。
 *      为了在客户端与服务端数据进行交互的时候使用，数据传输时使用。
 */
@Data
public class UserModel {

    /**
     * 用户主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 用户性别
     */
    private Byte gender;

    /**
     * 用户年龄
     */
    private Integer age;

    /**
     * 密码
     */
    private String encrptPassword;

    /**
     * 手机号
     */
    private String telphone;

    /**
     * 注册方式
     */
    private String registerMode;

    /**
     * 第三方注册id
     */
    private String thirdPartyId;

}
