package com.allen.demo.service;

import com.allen.demo.error.BusinessException;
import com.allen.demo.service.model.ItemModel;

import java.util.List;

/**
 * @author allen
 * @date 2021/5/17 20:13
 */
public interface ItemService {

    /**
     * 创建商品
     * @param itemModel 客户端传入的创建Item对象的表单信息
     * @return 商品对象
     */
    ItemModel creatItem(ItemModel itemModel) throws BusinessException;

    List<ItemModel> listItem();

    /**
     * 通过商品id获取商品详情
     * @param id 商品id
     * @return 商品model
     */
    ItemModel getItemById(Integer id);

    /**
     * 减库存
     * @param itemId 商品id
     * @param amount 购买数量
     * @return
     * @throws BusinessException
     */
    boolean decreaseStock(Integer itemId,Integer amount)throws BusinessException;

    /**
     * 商品销量增加
     * @param itemId
     * @param amount
     */
    void increaseSales(Integer itemId,Integer amount);
}
