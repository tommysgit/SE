package SoftwareEngineering.server.Repository;

import SoftwareEngineering.server.Domain.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Field, Long> {
}
