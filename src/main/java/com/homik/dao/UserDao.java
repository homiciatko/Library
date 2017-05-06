package com.homik.dao;

import com.homik.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Pawel on 2017-02-22.
 */
//uzycie springData
//@Repository
//public interface UserDao extends JpaRepository<User, Long>{
//
//
//    User findByEmail(String email);
//
//}

    @Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByEmail(String email);
}


//@Component
//@Controller - @Service - @Repository
//serwisy - wastrwa posrednia do komunikacji controllera z repository, serwisy zawieraja logike biznesowa