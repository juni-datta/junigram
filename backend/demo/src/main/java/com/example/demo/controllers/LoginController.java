package com.example.demo.controllers;


import com.example.demo.security.SecurityService;
import com.example.demo.security.UserCredential;
import com.example.demo.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody UserCredential userCredential) throws SQLException {

        System.out.println(userCredential.getUsername() + " " + userCredential.getPassword());

        if (securityService.login(userCredential.getUsername(), userCredential.getPassword())){
            httpSession.setAttribute("username", userCredential.getUsername());
            System.out.println("USER PASS");

            return "success";
        }
        else{
            System.out.println("USER FAIL");

            return "fail";

        }
    }



}

