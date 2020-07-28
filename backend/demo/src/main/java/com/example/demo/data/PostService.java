package com.example.demo.data;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    Connection conn;
    Statement statement;

    public PostService() throws SQLException, ClassNotFoundException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/junigram", "nessie", "howareyou1");
        statement = conn.createStatement();
    }

    public boolean createPost(PostDTO postDTO) throws SQLException {
        System.out.println(postDTO.getUser() + "\n" + postDTO.getText() + "\n" + postDTO.getSrc());
        return createPost(postDTO.getUser(), postDTO.getText(), postDTO.getSrc());
    }

    public boolean createPost(String username, String text, String URL) throws SQLException {
        String sql = String.format("INSERT INTO posts VALUES (\'%s\', \'%s\', \'%s\', \'%s\')", generateID(), username, text, URL);
        int res = statement.executeUpdate(sql);
        if (res > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean likePost(String postID, String username) throws SQLException {
        String sql = String.format("SELECT * FROM likes WHERE post_ID = '%s' AND user = '%s'", postID, username);
        ResultSet result = statement.executeQuery(sql);
        if (result.next()){
            return true;
        }

        result.close();

        sql = String.format("INSERT INTO likes VALUES (\'%s\', \'%s\')", postID, username);
        int res = statement.executeUpdate(sql);
        if (res > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean commentPost(String postID, String username, String text) throws SQLException {
        String id = generateID();
        String sql = String.format("INSERT INTO comments VALUES (\'%s\', \'%s\', \'%s\', \'%s\')", id, postID, username, text);
        int res = statement.executeUpdate(sql);
        if (res > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean deletePost(String postID, String currentUser) throws SQLException {
        String sql = String.format("SELECT user FROM posts WHERE ID = '%s'", postID);
        ResultSet result = statement.executeQuery(sql);
        if (!result.next()){
            return false;
        }
        String postUsername = result.getString("user");
        result.close();

        if (postUsername.equals(currentUser)){
            sql = String.format("DELETE FROM posts WHERE ID = \'%s\'", postID);
            int res = statement.executeUpdate(sql);
            return res > 0;
        }
        else{
            return false;
        }
    }

    public int editPost(String postID, String text, String URL){
        return 1;
    }

    public List<Post> getAllPosts() throws SQLException {
        List<Post> posts = new ArrayList<>();
        String sql = String.format("SELECT * FROM posts");
        ResultSet result = statement.executeQuery(sql);
        while (result.next()){
            String ID = result.getString("ID");
            String username = result.getString("user");
            String text = result.getString("text");
            String URL = result.getString("URL");
            List<Comment> comments = new ArrayList<>();
            List<String> likes = new ArrayList<>();
            posts.add(new Post(ID, username, text, URL, comments, likes));
        }

        for (Post post: posts){
            sql = String.format("SELECT * FROM comments WHERE post_ID = '%s'", post.getId());
            result = statement.executeQuery(sql);
            while(result.next()){
                String ID = result.getString("ID");
                String postID = result.getString("post_ID");
                String commentUsername = result.getString("user");
                String commentText = result.getString("text");
                post.addComment(new Comment(ID, postID, commentUsername, commentText));
            }
        }

        for (Post post: posts){
            sql = String.format("SELECT user FROM likes WHERE post_ID = '%s'", post.getId());
            result = statement.executeQuery(sql);
            while(result.next()){
                String likeUsername = result.getString("user");
                post.addLike(likeUsername);
            }
        }

        return posts;
    }

    public boolean likedPost(String username, String post_ID) throws SQLException {
        String sql = String.format("SELECT user FROM likes WHERE post_ID = '%s'", post_ID);
        ResultSet result = statement.executeQuery(sql);
        while(result.next()){
            String likeUsername = result.getString("user");
            if (username.equals(likeUsername)){
                return true;
            }
        }
        return false;
    }

    public String generateID(){
        return new RandomStringGenerator.Builder().withinRange('a', 'z').build().generate(128);
    }

    public boolean deleteComment(String commentID, String currentUser) throws SQLException {
        String sql = String.format("SELECT user FROM comments WHERE ID = '%s'", commentID);
        System.out.println("GOT HERE FIRST");
        ResultSet result = statement.executeQuery(sql);
        if (!result.next()){
            return false;
        }
        String commentUsername = result.getString("user");
        result.close();

        if (commentUsername.equals(currentUser)){
            sql = String.format("DELETE FROM comments WHERE ID = \'%s\'", commentID);
            int res = statement.executeUpdate(sql);
            return res > 0;
        }
        else{
            return false;
        }



    }








}
