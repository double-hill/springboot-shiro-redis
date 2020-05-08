package com.xl.youliao.common;

/**
 * 自定义响应数据结构
 * 200 成功
 * 401 未登录
 * 500 表示错误
 */
/**
 * @author WeiC
 * @date 2020/5/7 10:29
 */
public class JSONResult {
    //响应业务状态
    private Integer code;
    //响应消息
    private String msg;
    //响应中的数据
    private Object data;

    public static JSONResult ok() {
        return new JSONResult(200, "success");
    }

    public static JSONResult ok(String msg) {
        return new JSONResult(200, msg);
    }

    public static JSONResult ok(Object data) {
        return new JSONResult(200, "success", data);
    }

    public static JSONResult error(String msg) {
        return new JSONResult(500, msg);
    }

    public static JSONResult errorLogin(String msg) {
        return new JSONResult(401, msg);
    }


    public JSONResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JSONResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
