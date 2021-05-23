package com.allen.demo.dao;

import com.allen.demo.dataobject.ItemStockDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemStockDOMapper {
    /**
     * 删除
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增
     */
    int insert(ItemStockDO record);

    /**
     * 新增
     */
    int insertSelective(ItemStockDO record);

    /**
     * 查找
     */
    ItemStockDO selectByPrimaryKey(Integer id);

    /**
     * 通过itemId查找商品
     * @param id
     * @return
     */
    ItemStockDO selectByItemId(Integer id);

    /**
     * 更新
     */
    int updateByPrimaryKeySelective(ItemStockDO record);

    /**
     * 更新
     */
    int updateByPrimaryKey(ItemStockDO record);

    /**
     * 扣减库存的方法
     * @param itemId
     * @param amount
     * @return
     */
    int decreaseStock(@Param("itemId") Integer itemId, @Param("amount") Integer amount);
}