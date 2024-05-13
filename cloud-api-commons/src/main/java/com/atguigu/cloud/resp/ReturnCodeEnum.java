package com.atguigu.cloud.resp;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ReturnCodeEnum {
    RC999("999","操作XXX失敗"),
    RC200("200","success"),
    RC201("201","服務器開啟降級保護，請稍後再試!"),
    RC202("202","熱點參數限流，請稍後再試!"),
    RC203("203","系統規則不滿足需求，請稍後再試!"),
    RC204("204","授權規則不通過，請稍後再試!"),
    RC403("403","權限不足，請洽管理員"),
    RC401("401","匿名用戶訪問無權限資源時異常"),
    RC404("404","404 not found"),
    RC500("500","系統異常，請稍後再試!"),
    RC375("375","數學運算異常，請稍後再試!"),

    INVALID_TOKEN("2001","訪問令牌不合法"),
    ACCESS_DENIED("2003","沒有權限訪問該資源"),
    CLIENT_AUTHENTICATION_FAILED("1001","客戶端認證失敗"),
    USERNAME_OR_PASSWORD_ERROR("1002","用戶名或密碼錯誤"),
    BUSINESS_ERROR("1004","業務邏輯異常"),
    UNSUPPORTED_GRAND_TYPE("1003","不支持的認證方式");

    //如何定義通用的枚舉類


    //枚舉小口訣:舉值-構造-遍歷

    //1.
    private final String code;
    private final String message;

    //2.
    ReturnCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    //3.1
    public static ReturnCodeEnum getReturnCodeEnumV1(String code){
        for(ReturnCodeEnum element : ReturnCodeEnum.values()){
            if(element.getCode().equalsIgnoreCase(code)){
                return element;
            }
        }
        return null;
    }

    //3.2
    public static ReturnCodeEnum getReturnCodeEnumV2(String code){
        return Arrays.stream(ReturnCodeEnum.values()).filter(s -> s.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
    }



}
