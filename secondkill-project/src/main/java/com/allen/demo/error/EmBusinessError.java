package com.allen.demo.error;

/**
 * @author allen
 * @date 2021/5/15 16:20
 */
public enum EmBusinessError implements CommonError{
    //通用错误类型
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
    UNKNOWN_ERROR(10002,"未知错误"),

    //2000开头为用户信息相关错误定义
    USER_NOT_EXIST(20001,"用户不存在"),
    USER_REGISTER_ERROR(20004,"参数不合法"),
    USER_NOT_LOGIN(20003,"用户还未登陆"),
    USER_LOGIN_FAIL(20002,"用户手机号或密码不正确"),
    //3000开头为商品相关错误定义
    ITEM_CREAT_ERROR(30001,"商品创建出错"),
    //4000开头为交易信息错误
    STOCK_NOT_ENOUGH(30001,"库存不足")
    ;

    /**
     * 通过异常值及异常信息构造异常对象
     * @param errCode 异常值
     * @param errMsg 异常信息
     */
    private EmBusinessError(int errCode,String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private int errCode;
    private String errMsg;
    @Override
    public int getErrorCode() {
        return this.errCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrorMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
