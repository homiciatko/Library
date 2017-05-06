package com.homik.dao;

import com.homik.model.Rent;
import com.homik.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Pawel on 2017-04-09.
 */

@Repository
public interface RentDao extends JpaRepository<Rent, Long>{

    List<Rent> findByUserOrderByCreateDateDesc(User user);
    List<Rent> findByUser(User user);


}
