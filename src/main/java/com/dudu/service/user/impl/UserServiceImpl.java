package com.dudu.service.user.impl;

import com.dudu.dao.DaoSupport;
import com.dudu.model.User;
import com.dudu.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author: huyuanzhi
 * @version: 1.0
 * @date: 2016/9/29
 * @project: dudu
 * @packageName: com.dudu.service.user.impl
 * @description: XXXXXX
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private DaoSupport daoSupport;

    @Override
    public User register(User user) throws Exception {
        daoSupport.save("UserMapper.register",user);
        return user;
    }

    @Override
    public User login(User user) throws Exception {
        return (User) daoSupport.findForObject("UserMapper.login",user);
    }

    @Override
    public Map findUserByAccount(User user) throws Exception {
        return (Map) daoSupport.findForObject("UserMapper.findUserByAccount",user);
    }

    @Override
    public User getUserByOpenId(String openID) throws Exception {
        return (User) daoSupport.findForObject("UserMapper.getUserByOpenId",openID);
    }

    @Override
    public User getUserByUserId(String userId) throws Exception {
        return (User) daoSupport.findForObject("UserMapper.getUserByUserId",userId);
    }

    @Override
    public Object findUserExistByEmail(String email) throws Exception {
        return daoSupport.findForObject("UserMapper.findUserExistByEmail",email);
    }
}
