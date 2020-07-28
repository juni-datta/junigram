package com.example.demo.controllers;

import com.example.demo.data.Post;
import com.example.demo.data.PostDTO;
import com.example.demo.data.PostService;
import com.example.demo.data.UserPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

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

    @GetMapping("/get_posts")
    public List<Post> getAllPosts() throws SQLException {
        return postService.getAllPosts();
    }

    @PostMapping("/liked_post")
    public String likedPost(@RequestBody UserPostDTO userPostDTO) throws SQLException {
        if (postService.likedPost(userPostDTO.getUsername(), userPostDTO.getId())){
            return "yes";
        }
        else{
            return "no";
        }
    }

    @PostMapping("/like_post")
    public String likePost(@RequestBody UserPostDTO userPostDTO) throws SQLException {
        if(postService.likePost(userPostDTO.getId(), userPostDTO.getUsername())){
            return "success";
        }
        else{
            return "fail";
        }
    }

    @PostMapping("/comment_post")
    public String commentPost(@RequestBody UserPostDTO userPostDTO) throws SQLException {
        if (postService.commentPost(userPostDTO.getId(), userPostDTO.getUsername(), userPostDTO.getComment())){
            return "success";
        }
        else{
            return "fail";
        }

    }

    @PostMapping("/delete_post")
    public String deletePost(@RequestBody UserPostDTO userPostDTO) throws SQLException {
        if (postService.deletePost(userPostDTO.getId(), userPostDTO.getUsername())){
            return "success";
        }
        else{
            return "fail";
        }
    }

    @PostMapping("/delete_comment")
    public String deleteComment(@RequestBody UserPostDTO userPostDTO) throws SQLException {

        if (postService.deleteComment(userPostDTO.getId(), userPostDTO.getUsername())){
            return "success";
        }
        else{
            return "fail";
        }
    }
}
