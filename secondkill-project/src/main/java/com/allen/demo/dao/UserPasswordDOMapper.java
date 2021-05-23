package com.allen.demo.dao;

import com.allen.demo.dataobject.UserPasswordDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPasswordDOMapper {
    /**
     * 通过id删除密码
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增
     */
    int insert(UserPasswordDO record);

    /**
     * 新增
     */
    int insertSelective(UserPasswordDO record);

    /**
     * 通过id查找用户密码
     */
    UserPasswordDO selectByPrimaryKey(Integer id);

    /**
     * 更新
     */
    int updateByPrimaryKeySelective(UserPasswordDO record);

    /**
     * 更新
     */
    int updateByPrimaryKey(UserPasswordDO record);

    /**
     * 通过用户id查找密码对象的方法
     * @param userId
     * @return
     */
    UserPasswordDO selectByUserId(Integer userId);
}