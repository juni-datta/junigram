package com.example.demo.data;

import java.util.ArrayList;
import java.util.List;

public class Post {

    public Post(String id, String user, String caption, String src, List<Comment> comments, List<String> likes) {
        this.user = user;
        this.caption = caption;
        this.src = src;
        this.comments = comments;
        this.likes = likes;
        this.id = id;

        updateLikeString();
    }

    public Post(){
        comments = new ArrayList<>();
        likes = new ArrayList<>();
    }

    //64 bit unique ID
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //user who created this post
    private String user;
    //post text
    private String caption;
    //image URL
    private String src;

    private List<Comment> comments;

    private List<String> likes;

    public String getLike_string() {
        return like_string;
    }

    public void setLike_string(String like_string) {
        this.like_string = like_string;
    }

    private String like_string;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public void addLike(String username){
        this.likes.add(username);
        updateLikeString();

    }

    private void updateLikeString(){
        if (this.likes.size() == 0){
            this.like_string = "";
        }
        else if (this.likes.size() == 1){
            this.like_string = likes.get(0) + " liked this";
        }
        else if (this.likes.size() == 2){
            this.like_string = likes.get(0) + " and " + likes.get(1) + " liked this";
        }
        else {
            this.like_string = likes.get(0) + ", " + likes.get(1) + " and " + (likes.size()-2) + " others liked this";
        }
    }


}
