package SoftwareEngineering.server.Repository;

import SoftwareEngineering.server.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndIsDelete(String email, char isDelete);
}
