package com.dudu.util;

import com.dudu.model.Group;
import com.dudu.model.User;
import com.dudu.service.group.GroupService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: huyuanzhi
 * @version: 1.0
 * @date: 2016/10/20
 * @project: soundChat
 * @packageName: com.dudu.util
 * @description: XXXXXX
 */
public class FriendUtils {

    public static void initFriendsAndGroup(User user) throws Exception {
        GroupService groupService=BeanUtils.getBean(GroupService.class);
        //初始化分组
        Group g=new Group();
        g.setUid(user.getUid());
        g.setGroupname("我的好友");
        int groupId=groupService.addGroup(g);
        //初始化好友列表
        if(user.getUid() != 1){
            Map friend=new HashMap();
            friend.put("fid",1);
            friend.put("gid",groupId);
            friend.put("status",1);
            groupService.addFriends(friend);

            Map mine=new HashMap();
            mine.put("fid",user.getUid());
            mine.put("gid",1);
            mine.put("status",1);
            groupService.addFriends(mine);

        }
    }
}
