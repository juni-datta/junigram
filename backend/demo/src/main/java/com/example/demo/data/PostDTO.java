package com.example.demo.data;

import java.util.List;

public class PostDTO {

    //user who created this post
    private String user;
    //post text
    private String text;
    //image URL
    private String URL;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }



}