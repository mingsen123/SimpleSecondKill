package com.allen.demo.controller.viewobject;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author allen
 * @date 2021/5/17 21:04
 */
@Data
public class ItemVO {
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

    /**
     * 商品的活动状态
     */
    private Integer promoStatus;

    /**
     * 秒杀活动价格
     */
    private BigDecimal promoPrice;

    /**
     * 秒杀活动ID
     */
    private Integer promoId;

    /**
     * 秒杀活动开始时间
     */
    private String startDate;

}
