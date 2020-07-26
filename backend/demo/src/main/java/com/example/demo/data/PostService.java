package com.example.demo.data;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

@Service
public class PostService {

    Connection conn;
    Statement statement;

    public PostService() throws SQLException, ClassNotFoundException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/junigram", "nessie", "howareyou1");
        statement = conn.createStatement();
    }

    public boolean createPost(PostDTO postDTO) throws SQLException {
        return createPost(postDTO.getUser(), postDTO.getText(), postDTO.getURL());
    }

    public boolean createPost(String username, String text, String URL) throws SQLException {
        long ID = UUID.randomUUID().getLeastSignificantBits();
        String sql = String.format("INSERT INTO posts VALUES (\'%s\', \'%s\', \'%s\', \'%s\')", ID, username, text, URL);
        int res = statement.executeUpdate(sql);
        if (res > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean likePost(long postID, String username) throws SQLException {
        String sql = String.format("INSERT INTO likes VALUES (\'%s\', \'%s\')", postID, username);
        int res = statement.executeUpdate(sql);
        if (res > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean commentPost(long postID, String username, String text) throws SQLException {
        String sql = String.format("INSERT INTO likes VALUES (\'%s\', \'%s\', \'%s\')", postID, username, text);
        int res = statement.executeUpdate(sql);
        if (res > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public int editPost(long postID, String text, String URL){
        return 1;
    }




}
