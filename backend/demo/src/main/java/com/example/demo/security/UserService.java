package com.example.demo.security;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    Connection conn;
    Statement statement;
    public UserService() throws SQLException, ClassNotFoundException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/junigram", "nessie", "howareyou1");
        statement = conn.createStatement();
    }


    public boolean checkIfUserExists(String username) throws SQLException {

        String sql = String.format("SELECT username FROM users WHERE username = '%s'", username);
        ResultSet result = statement.executeQuery(sql);
        return result.next();
    }

    public int addUser(String username, String password) throws SQLException {
        if (checkIfUserExists(username)){
            return -1;
        }

        try {
            String salt = BCrypt.gensalt();
            String hashed_password = BCrypt.hashpw(password, salt);
            String sql = String.format("INSERT INTO users VALUES (\'%s\', \'%s\')", username, hashed_password);
            statement.executeUpdate(sql);
            return 1;
        }

        catch (Exception e){
            return 0;
        }


    }

    public int removeUser(String username) throws SQLException {
        String sql = String.format("DELETE FROM users WHERE username = '%s'", username);
        return statement.executeUpdate(sql);
    }

    public int changeUsername(String username, String newUsername) throws SQLException {
        String sql = String.format("UPDATE users SET username = '%s' WHERE username = '%s'", newUsername, username);
        return statement.executeUpdate(sql);
    }

    public int changePassword(String username, String newPassword) throws SQLException {
        String salt = BCrypt.gensalt();
        String hashed_password = BCrypt.hashpw(newPassword, salt);
        String sql = String.format("UPDATE users SET password = '%s' WHERE username = '%s'", hashed_password, username);
        return statement.executeUpdate(sql);
    }

    public String getHashedPassword(String username) throws SQLException {
        String sql = String.format("SELECT * FROM users WHERE username = '%s'", username);
        ResultSet result = statement.executeQuery(sql);
        if (result.next()){
            return result.getString("password");
        }
        else{
            return null;
        }
    }

    public List<String> getAllUsers() throws SQLException {
        List<String> usernames = new ArrayList<>();
        String sql = String.format("SELECT username FROM users");
        ResultSet result = statement.executeQuery(sql);
        while (result.next()){
            String username = result.getString("username");
            usernames.add(username);
        }
        return usernames;
    }


    public boolean isValidCredentials(String username, String password) throws SQLException {
        String sql = String.format("SELECT * FROM users WHERE username = '%s'", username);
        ResultSet result = statement.executeQuery(sql);
        if(result.next()){
            System.out.println(sql);
            String dbUsername = result.getString("username");
            String dbHashedPassword = result.getString("password");
            System.out.println(dbUsername + " " + username);
            System.out.println(dbHashedPassword + " " + password);
            return username.equals(dbUsername) && BCrypt.checkpw(password, dbHashedPassword);
        }
        else{
            return false;
        }



    }

}
