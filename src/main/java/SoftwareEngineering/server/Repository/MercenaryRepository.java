package SoftwareEngineering.server.Repository;

import SoftwareEngineering.server.Domain.Mercenary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MercenaryRepository extends JpaRepository<Mercenary, Long> {
}
