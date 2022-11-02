package SoftwareEngineering.server.Repository;

import SoftwareEngineering.server.Domain.Major;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Major, Long> {

}
