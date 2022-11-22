package SoftwareEngineering.server.Repository;

import SoftwareEngineering.server.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndIsDelete(String email, char isDelete);

}
