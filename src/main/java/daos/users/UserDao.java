package daos.users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.users.Permissions;
import entities.users.User;

public interface UserDao extends JpaRepository<User, Integer> {

    List<User> findByPermissions(Permissions permissions);
    
    User findOneByEmailAndPassword(String email, String password);
    
    User findOneByEmail(String email);
    
    @Query("select token.user from Token token where token.value = ?1")
    public User findByTokenValue(String tokenValue);

}
