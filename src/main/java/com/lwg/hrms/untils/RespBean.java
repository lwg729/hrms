package com.lwg.hrms.untils;

/**
 * @Author lwg
 * @Date 2020/12/8 16:59
 * @Version 1.0
 */
public class RespBean {

    private Integer status;
    private String msg;
    private Object data;

    public static RespBean ok(String msg){
        return new RespBean(200,msg,null);
    }

    public static RespBean ok(String msg,Object data){
        return new RespBean(200,msg,data);
    }

    public static RespBean error(String msg){
        return new RespBean(500,msg,null);
    }

    public static RespBean error(String msg,Object data){
        return new RespBean(500,msg,data);
    }

    public RespBean() {
    }

    public RespBean(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "com.lwg.hrms.untils.RespBean{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
