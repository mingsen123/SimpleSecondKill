package com.allen.demo.controller.viewobject;

import lombok.Data;

/**
 * @author allen
 * @date 2021/5/13 23:30
 * 返回给前端信息的类
 * 因为model对象会把比较机密的用户密码传给传给前端，这里需要重新定义一个传给前端数据的类
 */
@Data
public class UserVO {
    /**
     * 用户id
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
     * 用户手机号
     */
    private String telphone;

}
