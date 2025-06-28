package com.saturn.crm.common.enums;

/**
 * Desc:
 * Author: Saturn_Wh
 * Date: 2025/6/27 16:32
 */
public enum StatusCode {

    SUCCESS_1000("1000","Success");

    private String code;

    private String explain;

    StatusCode(String code, String explain){
        this.code = code;
        this.explain = explain;
    }

    public String getCode(){
        return code;
    }

    public String getExplain(){
        return explain;
    }

}
