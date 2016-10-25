package com.dudu.service.group;

import com.dudu.model.Group;
import com.dudu.model.User;

import java.util.List;
import java.util.Map;

/**
 * @author: huyuanzhi
 * @version: 1.0
 * @date: 2016/10/13
 * @project: soundChat
 * @packageName: com.dudu.service.group
 * @description: XXXXXX
 */
public interface GroupService {

    List<User> getGroupAndFriendsByUid(String uid) throws Exception;

    Integer addGroup(Group group) throws Exception;

    void addFriends(Map friend) throws Exception;
}
