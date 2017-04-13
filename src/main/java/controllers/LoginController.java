package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import wrappers.LoginDataWrapper;
import daos.users.TokenDao;
import daos.users.UserDao;
import entities.users.Token;
import entities.users.User;

@Controller
public class LoginController {
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private TokenDao tokenDao;
    
    public String login(LoginDataWrapper loginDataWrapper){
        User u =  userDao.findOneByEmailAndPassword(loginDataWrapper.getEmail(), loginDataWrapper.getPassword());
        if (u != null){
            Token tkn = new Token(u);
            tokenDao.save(tkn);
            return "{\"token\": \"" + tkn.getValue() + "\"}";
        } else {
            return null;
        }
    }

}
