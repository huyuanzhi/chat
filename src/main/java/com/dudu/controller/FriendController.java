package com.dudu.controller;

import com.dudu.model.Group;
import com.dudu.model.User;
import com.dudu.service.group.GroupService;
import com.dudu.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: huyuanzhi
 * @version: 1.0
 * @date: 2016/10/13
 * @project: soundChat
 * @packageName: com.dudu.controller
 * @description: XXXXXX
 */
@Controller
public class FriendController {

    @Resource
    private GroupService groupService;
    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/group")
    public Object getFriendsList(String userId) throws Exception {
        List<User> friends=groupService.getGroupAndFriendsByUid(userId);
        Map dataMap=new HashMap();
        dataMap.put("code",0);
        dataMap.put("msg","");
        Map d=new HashMap();
        d.put("friend",friends);
        dataMap.put("data",d);
        return dataMap;
    }
}
