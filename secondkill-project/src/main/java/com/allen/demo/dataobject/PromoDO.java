package com.allen.demo.dataobject;

import lombok.Data;

import java.util.Date;

@Data
public class PromoDO {
    /**
     *活动主键
     */
    private Integer id;

    /**
     *活动名称
     */
    private String promoName;

    /**
     *开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     *商品id
     */
    private Integer itemId;

    /**
     *活动价格
     */
    private Double promoItemPrice;

}