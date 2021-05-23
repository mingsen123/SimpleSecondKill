package com.allen.demo.dataobject;

import lombok.Data;

@Data
public class ItemStockDO {
    /**
     *库存表主键
     */
    private Integer id;

    /**
     *商品库存
     */
    private Integer stock;

    /**
     *商品id
     */
    private Integer itemId;
}