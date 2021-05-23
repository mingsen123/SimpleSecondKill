package com.allen.demo.dao;

import com.allen.demo.dataobject.ItemDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemDOMapper {
    /**
     * 查找
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增
     */
    int insert(ItemDO record);

    /**
     *新增
     */
    int insertSelective(ItemDO record);

    /**
     * 查找
     */
    ItemDO selectByPrimaryKey(Integer id);

    /**
     * 查询全部商品信息
     * @return
     */
    List<ItemDO> listItem();

    /**
     * 更新
     */
    int updateByPrimaryKeySelective(ItemDO record);

    /**
     * 更新
     */
    int updateByPrimaryKey(ItemDO record);

    int increaseSales(@Param("id")Integer id, @Param("amount") Integer amount);
}