package com.allen.demo.dao;

import com.allen.demo.dataobject.SequenceDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SequenceDOMapper {
    /**
     * 删除
     */
    int deleteByPrimaryKey(String name);

    /**
     * 新增
     */
    int insert(SequenceDO record);

    /**
     * 新增
     */
    int insertSelective(SequenceDO record);

    /**
     * 查找
     */
    SequenceDO selectByPrimaryKey(String name);

    /**
     * 通过名称查找
     * @param name
     * @return
     */
    SequenceDO getSequenceByName(String name);

    /**
     * 更新
     */
    int updateByPrimaryKeySelective(SequenceDO record);

    /**
     * 更新
     */
    int updateByPrimaryKey(SequenceDO record);
}