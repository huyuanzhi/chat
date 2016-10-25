package com.dudu.controller;

import com.dudu.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: huyuanzhi
 * @version: 1.0
 * @date: 2016/10/18
 * @project: soundChat
 * @packageName: com.dudu.controller
 * @description: XXXXXX
 */
@Controller
@RequestMapping(value = "/app")
public class PageController {

    @RequestMapping("/chat")
    private String chat(){
        return "chat";
    }

    @RequestMapping(value = "/playChess")
    private ModelAndView playChess(String to,String type, HttpServletRequest request){
        final ModelAndView mv=new ModelAndView("wuziqi");
        User u = (User) request.getSession().getAttribute("user");
        mv.addObject("to",to);
        mv.addObject("type",type);
        return mv;
    }
}
