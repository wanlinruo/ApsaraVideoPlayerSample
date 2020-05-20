package com.aliyun.vodplayerview.playlist;

import java.util.ArrayList;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2020/05/20/17:37
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class CusAlivcVideoInfoList {

    private int status;//状态码
    private String msg;//提示信息
    private ArrayList<CusAlivcVideoInfo> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<CusAlivcVideoInfo> getData() {
        return data;
    }

    public void setData(ArrayList<CusAlivcVideoInfo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CusAlivcVideoInfoList{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
