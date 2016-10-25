package com.dudu.model;

/**
 * @author: huyuanzhi
 * @version: 1.0
 * @date: 2016/9/29
 * @project: dudu
 * @packageName: com.dudu.model
 * @description: XXXXXX
 */
public class Message {

    private boolean statues;
    private String msg;
    private String ext;

    public Message() {
    }

    public Message(boolean statues, String msg, String ext) {
        this.statues = statues;
        this.msg = msg;
        this.ext = ext;
    }

    public boolean isStatues() {
        return statues;
    }

    public void setStatues(boolean statues) {
        this.statues = statues;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
