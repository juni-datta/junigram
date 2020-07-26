package com.example.demo.controllers;

import com.example.demo.security.SecurityService;
import com.example.demo.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private UserService userService;

    @GetMapping("/verify")
    public String verifyUser() throws SQLException {

        String username = (String) httpSession.getAttribute("username");

        if (username != null){
            return username;
        }
        else{
            return "";
        }
    }




}

