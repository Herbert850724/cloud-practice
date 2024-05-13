package com.atguigu.cloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class ResultData<T> {
    private String code;//結果狀態，具體代碼參照ReturnCodeEnum.java
    private String message;
    private T data;
    private long timestamp;

    public ResultData(){
        timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data){
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(ReturnCodeEnum.RC200.getCode())
                .setMessage(ReturnCodeEnum.RC200.getMessage())
                .setData(data);
        return resultData;

    }

    public static <T> ResultData<T> fail(String code,String message){
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(code)
                .setMessage(message)
                .setData(null);
        return resultData;

    }
}
