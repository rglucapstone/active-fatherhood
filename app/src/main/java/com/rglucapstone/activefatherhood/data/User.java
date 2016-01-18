package com.rglucapstone.activefatherhood.data;

/**
 * Created by ronald on 18/01/16.
 */
public class User {

    public String login;
    public String name;

    public User(String username, String fullname){
        this.login = username;
        this.name = fullname;
    }
}
