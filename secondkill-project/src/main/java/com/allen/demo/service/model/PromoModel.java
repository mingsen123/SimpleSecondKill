package com.allen.demo.service.model;

import lombok.Data;
import org.joda.time.DateTime;

import java.math.BigDecimal;


/**
 * 秒杀营销模型
 * @author allen
 * @date 2021/5/19 21:55
 */
@Data
public class PromoModel {

    private Integer id;

    /**
     * 秒杀活动状态 1为表示未开始，2表示进行中， 3表示已结束
     */
    private Integer status;

    /**
     * 秒杀活动名称
     */
    private String promoName;

    /**
     * 开始时间
     */
    private DateTime startDate;

    /**
     * 秒杀活动结束时间
     */
    private DateTime endDate;

    /**
     * 秒杀活动的试用商品
     */
    private Integer itemId;

    /**
     * 秒杀活动的商品价格
     */
    private BigDecimal promoItemPrice;

}
