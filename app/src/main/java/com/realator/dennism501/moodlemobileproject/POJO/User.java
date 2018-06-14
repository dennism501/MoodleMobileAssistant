package com.realator.dennism501.moodlemobileproject.POJO;

import com.orm.SugarRecord;

/**
 * Created by dennism501 on 2/26/17.
 */

public class User extends SugarRecord{

    private String username;
    private String password;
    private String userToken = "355b6a7b497b2bb9aaa80206f21c078a";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
