package com.allen.demo.dao;

import com.allen.demo.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDOMapper {
    /**
     * 删除
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增
     */
    int insert(UserDO record);

    /**
     * 新增
     */
    int insertSelective(UserDO record);

    /**
     *查询
     */
    UserDO selectByPrimaryKey(Integer id);

    /**
     * 通过用户手机号查询用户信息
     * @param telphone
     * @return
     */
    UserDO selectByTelphone(String telphone);

    /**
     * 更新
     */
    int updateByPrimaryKeySelective(UserDO record);

    /**
     * 更新
     */
    int updateByPrimaryKey(UserDO record);


}