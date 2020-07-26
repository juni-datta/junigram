package com.example.demo.controllers;

import com.example.demo.data.PostDTO;
import com.example.demo.data.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/create_post")
    public String post(@RequestBody PostDTO postDTO) throws SQLException {
        if (postService.createPost(postDTO)){
            return "success";
        }
        else{
            return "fail";
        }
    }
}
