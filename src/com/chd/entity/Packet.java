package com.chd.entity;

import java.io.Serializable;

/**
 * 自定义通信协议包
 */
public class Packet implements Serializable{
    //命令或者返回的结果
    private String cmd;
    //附带发送的数据数据
    private Object data;

    /**
     * 含参构造方法
     */
    public Packet(String cmd, Object data) {
        this.cmd = cmd;
        this.data = data;
    }

    /**
     * 无参构造方法
     */
    public Packet() {}

    /**
     * getter and setter
     */
    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
