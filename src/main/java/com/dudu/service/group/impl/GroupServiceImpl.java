package com.dudu.service.group.impl;

import com.dudu.dao.DaoSupport;
import com.dudu.model.Group;
import com.dudu.model.User;
import com.dudu.service.group.GroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: huyuanzhi
 * @version: 1.0
 * @date: 2016/10/13
 * @project: soundChat
 * @packageName: com.dudu.service.group.impl
 * @description: XXXXXX
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private DaoSupport daoSupport;

    @Override
    public List<User> getGroupAndFriendsByUid(String uid) throws Exception {
        return (List<User>) daoSupport.findForList("GroupMapper.getGroupAndFriendsByUid",uid);
    }

    @Override
    public Integer addGroup(Group group) throws Exception {
        daoSupport.save("GroupMapper.addGroup",group);
        return group.getId();
    }

    @Override
    public void addFriends(Map friend) throws Exception {
        daoSupport.save("GroupMapper.addFriends",friend);
    }
}
