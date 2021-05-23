package com.allen.demo.dataobject;

import lombok.Data;

@Data
public class ItemDO {
    /**
     *商品主键
     */
    private Integer id;

    /**
     *商品名称
     */
    private String title;

    /**
     *商品价格
     */
    private Double price;

    /**
     *商品
     */
    private String description;

    /**
     *商品销量
     */
    private Integer sales;

    /**
     *商品图片
     */
    private String imgUrl;
}