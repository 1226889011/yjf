package com.cache.democache.bean;


import java.io.Serializable;

public class employee implements Serializable {
        private Integer user_id;
    private  String user_name;
    private  String user_password;
    private String phone;

public employee(){
    super();
}
    public employee(Integer user_id, String user_name, String user_password, String phone) {
        super();
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.phone = phone;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
