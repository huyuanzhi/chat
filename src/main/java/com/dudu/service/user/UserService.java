package com.dudu.service.user;

import com.dudu.model.User;

import java.util.Map;

/**
 * @author: huyuanzhi
 * @version: 1.0
 * @date: 2016/9/29
 * @project: dudu
 * @packageName: com.dudu.service.user
 * @description: XXXXXX
 */
public interface UserService {

    User register(User user) throws Exception;

    User login(User user) throws Exception;

    Map findUserByAccount(User user) throws Exception;

    User getUserByOpenId(String openID) throws Exception;

    User getUserByUserId(String userId) throws Exception;

    Object findUserExistByEmail(String email) throws Exception;

    User getUserById(String to) throws Exception;
}
