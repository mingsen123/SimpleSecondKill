package com.allen.demo.dao;

import com.allen.demo.dataobject.PromoDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PromoDOMapper {
    /**
     * 删除
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增
     */
    int insert(PromoDO record);

    /**
     * 新增
     */
    int insertSelective(PromoDO record);

    /**
     * 查找
     */
    PromoDO selectByPrimaryKey(Integer id);

    /**
     * 通过商品id查找活动信息
     * @param itemId
     * @return
     */
    PromoDO selectByItemId(Integer itemId);

    /**
     * 更新
     */
    int updateByPrimaryKeySelective(PromoDO record);

    /**
     * 更新
     */
    int updateByPrimaryKey(PromoDO record);
}