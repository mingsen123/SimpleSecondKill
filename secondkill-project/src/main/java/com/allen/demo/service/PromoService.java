package com.allen.demo.service;

import com.allen.demo.service.model.PromoModel;

/**
 * @author allen
 * @date 2021/5/19 22:17
 */
public interface PromoService {

    /**
     * 通过商品id获取秒杀活动
     * @param itemId
     * @return
     */
    PromoModel getPromoByItemId(Integer itemId);
}
