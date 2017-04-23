package daos.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import entities.users.Token;
import entities.users.User;

@Transactional
public interface TokenDao extends JpaRepository<Token, Integer> {

    Token findByUser(User user);
    Token findByValue(String value);
    
}
