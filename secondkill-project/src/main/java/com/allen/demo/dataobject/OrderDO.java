package com.allen.demo.dataobject;

import lombok.Data;

@Data
public class OrderDO {

    /**
     *订单号
     */
    private String id;

    /**
     *用户id
     */
    private Integer userId;

    /**
     *商品id
     */
    private Integer itemId;

    /**
     * 活动id
     */
    private Integer promoId;

    /**
     *商品价格
     */
    private Double itemPrice;

    /**
     *商品数量
     */
    private Integer amount;

    /**
     *订单价格
     */
    private Double orderPrice;

}