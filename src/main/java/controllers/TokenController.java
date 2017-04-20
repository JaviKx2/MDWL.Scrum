package controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.users.TokenDao;
import entities.users.Permissions;
import entities.users.Token;

@Controller
@Transactional
public class TokenController {

    private TokenDao tokenDao;

    @Autowired
    public void setTokenDao(TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }
    
    public boolean userHasPermission(String tokenValue, Permissions p) {
        Token tkn = tokenDao.findByValue(tokenValue);
        if (tkn != null && tkn.getUser().getPermissions() == p){
            return true;
        } 
        else {
            return false;
        }  
    }

}
