package com.homik.service.impl;

import com.homik.dao.UserDao;
import com.homik.model.MyUser;
import com.homik.model.User;
import com.homik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pawel on 2017-03-07.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userDao.findByEmail(email);

        if (user == null) {
//            throw new UsernameNotFoundException(email + " nie istnieje w bazie");
            throw new UsernameNotFoundException(String.format("%s nie istnieje w bazie", email));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole())); //kazdy user ma role admin
                                                                    //TODO: zmienic na ADMIN I USER


        return new MyUser(
                user.getEmail(),
                user.getPassword(),
                authorities,
                user.getFirstName()
        );


//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                authorities
//        );
    }



}
