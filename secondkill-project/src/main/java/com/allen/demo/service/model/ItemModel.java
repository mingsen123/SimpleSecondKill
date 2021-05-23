package com.allen.demo.service.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author allen
 * @date 2021/5/17 19:44
 */
@Data
public class ItemModel {

    /**
     * 商品id
     */
    private Integer id;

    /**
     * 商品名
     */
    private String title;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品库存
     */
    private Integer stock;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品销量
     */
    private Integer sales;

    /**
     * 商品描述图片URL
     */
    private String imgUrl;

    //使用聚合模型,如果promoModel不为空
    private PromoModel promoModel;

}
