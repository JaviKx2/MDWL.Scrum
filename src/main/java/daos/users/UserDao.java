package daos.users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.users.Permissions;
import entities.users.User;

public interface UserDao extends JpaRepository<User, Long> {

    List<User> findByPermissions(Permissions permissions);

}
