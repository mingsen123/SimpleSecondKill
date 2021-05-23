package com.allen.demo.response;

import lombok.Data;

/**
 * @author allen
 * @date 2021/5/15 15:55
 *
 * 返回给浏览器的有用错误信息
 */
@Data
public class CommonReturnType {

    /**
     * 表明对应请求的返回结果
     * success or fail
     * 若status为success,则data返回前端需要的data数据
     * 若为fail，则data内返回统一的错误码格式
     */
    private String status;



    /**
     * 返回给前端的数据
     */
    private Object data;


    /**
     * 通用的创建方法
     * 当不传入status参数的时候，默认为success
     * @param result
     * @return
     */
    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result,"success");
    }

    public static CommonReturnType create(Object result, String status){
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return type;
    }

}
