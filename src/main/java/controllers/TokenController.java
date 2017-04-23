package controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.users.TokenDao;
import entities.users.Permissions;
import entities.users.Token;
import entities.users.User;

@Controller
@Transactional
public class TokenController {

    private TokenDao tokenDao;

    @Autowired
    public void setTokenDao(TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }
    
    public User getUserByTokenValue(String tokenValue) {
        User user = null;
        Token tkn = tokenDao.findByValue(tokenValue);
        if (tkn != null) {
            user = tkn.getUser();
        }
        return user;
    }
    
    public boolean userHasPermission(String tokenValue, Permissions p) {
        Token tkn = tokenDao.findByValue(tokenValue);
        if (tkn != null && tkn.getUser().getPermissions().ordinal() >= p.ordinal()){
            return true;
        } 
        else {
            return false;
        }  
    }

}
