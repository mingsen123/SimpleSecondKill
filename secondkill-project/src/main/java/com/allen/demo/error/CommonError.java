package com.allen.demo.error;

/**
 * @author allen
 * @date 2021/5/15 16:18
 */
public interface CommonError {

    /**
     * 获取异常类型对象的异常值
     * @return
     */
    int getErrorCode();

    /**
     * 获取异常类型对象的异常信息
     * @return
     */
    String getErrorMsg();

    /**
     * 针对其他异常类型设置新的异常信息
     * @param errorMsg
     * @return
     */
    CommonError setErrorMsg(String errorMsg);
}
