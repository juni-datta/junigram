package com.example.demo.data;

public class PostDTO {

    //user who created this post
    private String user;
    //post text
    private String text;
    //image URL
    private String src;

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

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }



}