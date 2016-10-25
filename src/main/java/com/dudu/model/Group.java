package com.dudu.model;

import java.util.List;

/**
 * @author: huyuanzhi
 * @version: 1.0
 * @date: 2016/10/13
 * @project: soundChat
 * @packageName: com.dudu.model
 * @description: XXXXXX
 */
public class Group {

    private String groupname;
    private Integer id;
    private Integer uid;//创建人id
    private List<User> list;

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }
}
