package com.fly.mapper;


import com.fly.pojo.User;
import org.apache.catalina.mapper.Mapper;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    //添加用户
    int addUser(User user);

    //修改用户信息
    int updateUserById(User user);

    //删除用户
    int deleteUserById(int id);

    //根据id查询用户
    User selectUserById(int id);
    //根据name查询用户
    User selectUserByName(String user_name);

    //查询所有用户
    List<User> selectAllUser();

    //根据用户名模糊查询
    List<User> QueryUserByName(String username);

    //获取分页数据
    List<User> selectUserPage(Map<String, Object> map);
}
