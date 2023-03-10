package org.simple.wx.enums;

public enum HttpMethods {
    GET("get"), POST("post");

    private String method;

    HttpMethods(String method){
        this.method = method;
    }

    // 实现字符串转枚举的静态方法
    public static HttpMethods fromMethod(String method) {
        if(method.equals("post")){
            return HttpMethods.POST;
        }

        return HttpMethods.GET;
    }
}
