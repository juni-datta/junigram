package com.example.demo.security;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Service
public class SecurityService {

    private UserService userService;

    public void setUserService(UserService userService){
        this.userService = userService;
    }

    public String getCurrentUsername(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object usernameObject = session.getAttribute("username");
        return (String) usernameObject;
    }

    public boolean isAuthorized(HttpServletRequest request) throws SQLException {
        String username = getCurrentUsername(request);
        return userService.checkIfUserExists(username);


    }

    public boolean login(HttpServletRequest request) throws SQLException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (userService.isValidCredentials(username, password)){
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            return true;
        }
        else{
            return false;
        }

    }


    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.invalidate();
    }
}
