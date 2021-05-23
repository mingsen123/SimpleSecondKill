package com.allen.demo.dao;

import com.allen.demo.dataobject.OrderDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDOMapper {
    /**
     * 删除
     */
    int deleteByPrimaryKey(String id);

    /**
     * 新增
     */
    int insert(OrderDO record);

    /**
     * 新增
     */
    int insertSelective(OrderDO record);

    /**
     * 查询
     */
    OrderDO selectByPrimaryKey(String id);

    /**
     * 更新
     */
    int updateByPrimaryKeySelective(OrderDO record);

    /**
     * 更新
     */
    int updateByPrimaryKey(OrderDO record);
}