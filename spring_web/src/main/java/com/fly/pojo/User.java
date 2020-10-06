package com.fly.pojo;

import java.util.Date;

/**
 * @description: User实体类
 * @author: caifeifei
 * @createDate: 2020-9-24
 * @version: 1.0
 */
public class User {
    private int user_id;
    private String user_name;
    private String user_password;
    private Date create_time;

    public User() {
    }

    public User(int user_id, String user_name, String user_password) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
    }

    public User(int user_id, String user_name, String user_password, Date create_time) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.create_time = create_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        /*if (create_time==null){
            Date date=new Date();
            //自定义日期格式对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.create_time=date;
        }else {
            this.create_time = create_time;
        }*/
        this.create_time = create_time;
    }
}
