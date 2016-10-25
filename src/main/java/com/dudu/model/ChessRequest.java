package com.dudu.model;

/**
 * @author: huyuanzhi
 * @version: 1.0
 * @date: 2016/10/25
 * @project: soundChat
 * @packageName: com.dudu.model
 * @description: XXXXXX
 */
public class ChessRequest {

    private String mine;

    private String mineName;

    private String to;

    public ChessRequest() {
    }

    public String getMine() {
        return mine;
    }

    public void setMine(String mine) {
        this.mine = mine;
    }

    public String getMineName() {
        return mineName;
    }

    public void setMineName(String mineName) {
        this.mineName = mineName;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
