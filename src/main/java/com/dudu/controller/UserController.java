package com.dudu.controller;

import com.dudu.model.User;
import com.dudu.service.user.UserService;
import com.dudu.util.FriendUtils;
import com.dudu.util.QiNiuUploadUtils;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author: huyuanzhi
 * @version: 1.0
 * @date: 2016/10/18
 * @project: soundChat
 * @packageName: com.dudu.controller
 * @description: XXXXXX
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/exist",method = RequestMethod.POST)
    private Object exist(String email) {
        try {
            Object o=userService.findUserExistByEmail(email);
            if(o == null)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping(value = "/register")
    public ModelAndView register(User user, HttpServletRequest request){
        final ModelAndView mv=new ModelAndView("redirect:/chat");
        user.setGender(user.getGender().equals("male")?"男":"女");
        user.setAvatar("http://7xp0v5.com1.z0.glb.clouddn.com/headimg.jpg");
        user.setCreateDate(new Date());
        user.setId(UUID.randomUUID().toString());
        user.setIsUse(1);
        user.setType(0);
        user.setSign("帅到深处人孤独啊！");
        try {
            User registerUser=userService.register(user);
            FriendUtils.initFriendsAndGroup(registerUser);
            request.getSession().setAttribute("user",user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/upload")
    public Object upload(MultipartFile file,String type){
        Map dataMap=new HashMap();
        Map m=new HashMap();
        dataMap.put("code",0);
        try {
            String url=QiNiuUploadUtils.upload(file.getBytes());
            m.put("src",url);
            if(type.equals("file")){
                m.put("name",file.getOriginalFilename());
            }
            dataMap.put("data",m);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataMap;
    }
}
