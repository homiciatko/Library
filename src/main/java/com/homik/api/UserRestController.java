package com.homik.api;

import com.homik.dao.UserDao;
import com.homik.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Pawel on 2017-03-29.
 */

@RestController
public class UserRestController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/api/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getUsers() {
        List<User> users = userDao.findAll();

        List<UserDto> userDtos = new ArrayList<>();

//        for (User user : users) {
//            userDtos.add(new UserDto(user));
//        }
//     return users;
       return users.stream().map(UserDto::new).collect(Collectors.toList());
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {

        User user = new User(userDto);
        userDao.save(user);

        return ResponseEntity.ok("User created");
    }

}
