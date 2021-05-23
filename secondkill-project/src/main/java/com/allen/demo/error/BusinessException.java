package com.allen.demo.error;

/**
 * 包装器业务异常类实现
 * @author allen
 * @date 2021/5/15 16:30
 */
public class BusinessException extends Exception implements CommonError{

    private CommonError commonError;

    /**
     * 构造方法
     * 直接接收EmBusinessError的传参用于构造业务异常
     * @param commonError
     */
    public BusinessException(CommonError commonError){
        super();
        this.commonError = commonError;
    }

    /**
     * 构造方法
     * 接收自定义错误信息构造业务异常
     * @param commonError
     * @param errMsg
     */
    public BusinessException(CommonError commonError,String errMsg){
        super();
        this.commonError = commonError;
        this.commonError.setErrorMsg(errMsg);
    }

    @Override
    public int getErrorCode() {
        return this.commonError.getErrorCode();
    }

    @Override
    public String getErrorMsg() {
        return this.commonError.getErrorMsg();
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.commonError.setErrorMsg(errorMsg);
        return this;
    }
}
