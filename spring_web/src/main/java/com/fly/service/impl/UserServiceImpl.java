package com.fly.service.impl;


import com.fly.mapper.UserMapper;
import com.fly.pojo.User;
import com.fly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: caifeifei
 * @createDate: 2020-9-24
 * @version: 1.0
 */
@Repository
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int updateUserById(User user) {
        return userMapper.updateUserById(user);
    }

    @Override
    public int deleteUserById(int id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public User selectUserById(int id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public User selectUserByName(String user_name) {
        return userMapper.selectUserByName(user_name);
    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public List<User> QueryUserByName(String username) {

        return userMapper.QueryUserByName(username);
    }

    @Override
    public List<User> queryUserPage(int pageNo, int pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", (pageNo - 1) * pageSize);
        map.put("pagesize", pageSize);
        return userMapper.selectUserPage(map);
    }

    @Override
    public List<User> queryUserPage(String username, int pageNo, int pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user_name", username);
        map.put("start", (pageNo - 1) * pageSize);
        map.put("pagesize", pageSize);
        return userMapper.selectUserPage(map);
    }
}
