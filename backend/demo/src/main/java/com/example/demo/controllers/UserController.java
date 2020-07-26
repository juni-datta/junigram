package com.example.demo.controllers;

import com.example.demo.security.SecurityService;
import com.example.demo.security.UserCredential;
import com.example.demo.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private UserService userService;

    @PostMapping("/create_user")
    public String create_user(@RequestBody UserCredential userCredential) throws SQLException {
        int status = userService.addUser(userCredential.getUsername(), userCredential.getPassword());
        if (status == 1){
            return "success";
        }

        if (status == -1){
            return "user_exists";
        }

        return "fail";
    }
}
