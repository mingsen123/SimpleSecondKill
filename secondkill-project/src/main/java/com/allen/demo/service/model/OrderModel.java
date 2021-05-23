package com.allen.demo.service.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 用户下单交易模型
 * @author allen
 * @date 2021/5/18 22:07
 */
@Data
public class OrderModel {
    /**
     * 交易号
     */
    private  String id;

    /**
     * 购买时商品的单价
     */
    private BigDecimal itemPrice;

    /**
     * 下单用户的id
     */
    private Integer userId;

    /**
     * 购买的商品id
     */
    private Integer itemId;

    /**
     * 秒杀活动ID
     * 若非空则以秒杀价格
     */
    private Integer promoId;

    /**
     * 购买的数量
     */
    private Integer amount;

    /**
     * 购买的金额
     */
    private BigDecimal orderPrice;

}
