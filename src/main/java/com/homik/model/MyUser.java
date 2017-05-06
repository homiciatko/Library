package com.homik.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by Pawel on 2017-04-27.
 */
public class MyUser extends org.springframework.security.core.userdetails.User {


    private String firstname;


    public MyUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String firstname) {
        super(username, password, authorities);
        this.firstname = firstname;
    }


    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
