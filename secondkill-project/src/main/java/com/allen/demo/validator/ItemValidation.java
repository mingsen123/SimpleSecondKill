package com.allen.demo.validator;

import com.allen.demo.error.BusinessException;
import com.allen.demo.error.EmBusinessError;
import com.allen.demo.service.model.ItemModel;

/**
 * 判断商品信息是否准确
 * @author allen
 * @date 2021/5/17 20:21
 */
public class ItemValidation {
    public void validate(ItemModel itemModel) throws BusinessException {
        if(null == itemModel.getTitle()){
            throw new BusinessException(EmBusinessError.ITEM_CREAT_ERROR,"商品名称不能为空");
        }
        if(null == itemModel.getPrice()){
            throw new BusinessException(EmBusinessError.ITEM_CREAT_ERROR,"商品价格不能为空");
        }
        if(itemModel.getPrice().intValue() < 0){
            throw new BusinessException(EmBusinessError.ITEM_CREAT_ERROR,"商品价格必须大于0");
        }
        if(null == itemModel.getStock()){
            throw new BusinessException(EmBusinessError.ITEM_CREAT_ERROR,"库存必须填写");
        }
        if(null == itemModel.getDescription()){
            throw new BusinessException(EmBusinessError.ITEM_CREAT_ERROR,"商品描述信息不能为空");
        }
        if(null == itemModel.getImgUrl()){
            throw new BusinessException(EmBusinessError.ITEM_CREAT_ERROR,"图片信息不能为空");
        }
    }

}
