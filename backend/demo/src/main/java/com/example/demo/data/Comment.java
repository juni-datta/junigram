package com.example.demo.data;

public class Comment {
    public Comment(String id, String post_id, String user, String comment) {
        this.user = user;
        this.comment = comment;
        this.id = id;
        this.post_id = post_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String comment;

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    private String user;
    private String id;
    private String post_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
