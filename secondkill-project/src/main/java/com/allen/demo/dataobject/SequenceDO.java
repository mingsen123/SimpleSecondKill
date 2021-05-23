package com.allen.demo.dataobject;

import lombok.Data;

@Data
public class SequenceDO {
    /**
     *用户名
     */
    private String name;

    /**
     *起步
     */
    private Integer currentValue;

    /**
     *约束条件
     */
    private Integer step;

}