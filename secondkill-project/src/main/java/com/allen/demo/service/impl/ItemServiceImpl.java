package com.allen.demo.service.impl;

import com.allen.demo.dao.ItemDOMapper;
import com.allen.demo.dao.ItemStockDOMapper;
import com.allen.demo.dataobject.ItemDO;
import com.allen.demo.dataobject.ItemStockDO;
import com.allen.demo.error.BusinessException;
import com.allen.demo.error.EmBusinessError;
import com.allen.demo.service.ItemService;
import com.allen.demo.service.PromoService;
import com.allen.demo.service.model.ItemModel;
import com.allen.demo.service.model.PromoModel;
import com.allen.demo.validator.ItemValidation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author allen
 * @date 2021/5/17 20:18
 */
@Service
public class ItemServiceImpl implements ItemService {


    @Autowired
    private ItemDOMapper itemDOMapper;

    @Autowired
    private ItemStockDOMapper itemStockDOMapper;

    @Autowired
    private PromoService promoService;

    /**
     * 将ItemModel转化为ItemDO
     * @param itemModel
     * @return
     */
    private ItemDO convertItemDOFromItemModel(ItemModel itemModel){
        if(null == itemModel){return null;}
        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemModel,itemDO);
        itemDO.setPrice(itemModel.getPrice().doubleValue());
        return itemDO;
    }

    /**
     * 将ItemModel转化为ItemStockDO.也就是将Model中的ID传入Stock中的ItemId，将stock转入stock表中的stock；
     * @param itemModel
     * @return
     */
    private ItemStockDO convertItemStockDOFromItemModel(ItemModel itemModel){
        if(null == itemModel){return null;}
        ItemStockDO itemStockDO = new ItemStockDO();
        itemStockDO.setItemId(itemModel.getId());
        itemStockDO.setStock(itemModel.getStock());
        return itemStockDO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ItemModel creatItem(ItemModel itemModel) throws BusinessException {
        //校验入参
        new ItemValidation().validate(itemModel);
        //转化ItemModel -- > DO
        ItemDO itemDO = this.convertItemDOFromItemModel(itemModel);
        //写入数据库
        try{
            itemDOMapper.insertSelective(itemDO);
        }catch (Exception e){
            throw  new BusinessException(EmBusinessError.ITEM_CREAT_ERROR,"写入数据库不成功");
        }
        //从数据库中取出商品id
        itemModel.setId(itemDO.getId());
        ItemStockDO itemStockDO = this.convertItemStockDOFromItemModel(itemModel);
        itemStockDOMapper.insertSelective(itemStockDO);
        //返回创建完成的对象
        return this.getItemById(itemModel.getId());
    }

    @Override
    public List<ItemModel> listItem() {
        List<ItemDO> itemDOList = itemDOMapper.listItem();
        List<ItemModel> itemModelList = itemDOList.stream().map(itemDO -> {
            ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
            ItemModel itemModel = this.convertItemModelFromItemDO(itemDO, itemStockDO);
            return itemModel;
        }).collect(Collectors.toList());
        return itemModelList;
    }

    @Override
    public ItemModel getItemById(Integer id) {
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(id);
        if(null == itemDO){return null;}
        //操作获得库存数量
        ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
        if(null == itemStockDO){return null;}
        //将DO-->Model
        ItemModel itemModel =convertItemModelFromItemDO(itemDO, itemStockDO);
        //获取活动商品信息
        PromoModel promoModel = promoService.getPromoByItemId(itemModel.getId());
        if(null != promoModel && promoModel.getStatus().intValue() !=3){
            itemModel.setPromoModel(promoModel);
        }
        return itemModel;
    }

    @Override
    @Transactional
    public boolean decreaseStock(Integer itemId, Integer amount) throws BusinessException {
        int i = itemStockDOMapper.decreaseStock(itemId, amount);
        if(i > 0){
            //更新库存成功
            return true;
        }else {
            return false;
        }
    }

    @Override
    @Transactional
    public void increaseSales(Integer itemId, Integer amount) {
        itemDOMapper.increaseSales(itemId,amount);

    }

    private ItemModel convertItemModelFromItemDO(ItemDO itemDO,ItemStockDO itemStockDO){
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDO,itemModel);
        itemModel.setPrice(new BigDecimal(itemDO.getPrice()));
        itemModel.setStock(itemStockDO.getStock());
        return itemModel;
    }
}
