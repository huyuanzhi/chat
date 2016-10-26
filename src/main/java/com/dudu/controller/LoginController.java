package com.dudu.controller;

import com.dudu.model.Group;
import com.dudu.model.User;
import com.dudu.service.group.GroupService;
import com.dudu.service.user.UserService;
import com.dudu.util.FriendUtils;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.PageFans;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.PageFansBean;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.javabeans.weibo.Company;
import com.qq.connect.oauth.Oauth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


/**
 * @author: huyuanzhi
 * @version: 1.0
 * @date: 2016/10/13
 * @project: soundChat
 * @packageName: com.dudu.controller
 * @description: XXXXXX
 */
@Controller
public class LoginController {

    @Resource
    private UserService userService;
    @Resource
    private GroupService groupService;

    @RequestMapping(value = "/qq")
    public void login(HttpServletResponse response,HttpServletRequest request){
        try {
            response.sendRedirect(new Oauth().getAuthorizeURL(request));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/app/qq")
    public String afterLogin(HttpServletRequest request){
        try {
            User sessionUser= (User) request.getSession().getAttribute("user");
            if(sessionUser != null){
                return "chat";
            }
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            String accessToken, openID;
            long tokenExpireIn = 0L;
            if (accessTokenObj.getAccessToken().equals("")) {
                System.out.print("没有获取到响应参数");
            } else {
                accessToken = accessTokenObj.getAccessToken();
                /*tokenExpireIn = accessTokenObj.getExpireIn();

                request.getSession().setAttribute("demo_access_token", accessToken);
                request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));*/

                OpenID openIDObj =  new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();
                User user=userService.getUserByOpenId(openID);
                if(user != null && user.getIsUse() == 1){
                    request.getSession().setAttribute("user",user);
                    return "chat";
                }
                UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                if (userInfoBean.getRet() == 0) {
                    User u = new User();
                    u.setOpenId(openID);
                    u.setUsername(userInfoBean.getNickname());
                    u.setIsUse(1);
                    u.setCreateDate(new Date());
                    u.setType(1);//1为qq，2为新浪微博
                    u.setSign("帅到深处人孤独啊！");
                    u.setGender(userInfoBean.getGender());
                    u.setId(UUID.randomUUID().toString());
                    String imgUrl=userInfoBean.getAvatar().getAvatarURL50();
                    String headUrl=imgUrl.replace("qzapp.","q.").replace("qzapp","qqapp").replace("/50","/40");
                    u.setAvatar(headUrl);
                    User registerUser=userService.register(u);
                    FriendUtils.initFriendsAndGroup(registerUser);
                    request.getSession().setAttribute("user",u);
                    return "chat";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    private String frontLogin(User user,HttpServletRequest request){
        try {
            Object u=userService.login(user);
            if(u == null)
                return "redirect:/";
            request.getSession().setAttribute("user",u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "chat";
    }

}
