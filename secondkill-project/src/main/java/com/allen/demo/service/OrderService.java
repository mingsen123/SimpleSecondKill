package com.allen.demo.service;

import com.allen.demo.error.BusinessException;
import com.allen.demo.service.model.OrderModel;

/**
 * @author allen
 * @date 2021/5/18 22:21
 */
public interface OrderService {

    /**
     * 创建订单方法
     * @param userId
     * @param itemId
     * @param amount
     * @return
     */
    OrderModel createOrder(Integer userId,Integer itemId,Integer promoId,Integer amount) throws BusinessException;

}
