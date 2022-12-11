package SoftwareEngineering.server.Repository;

import SoftwareEngineering.server.Domain.Field;
import SoftwareEngineering.server.Domain.Reservation;
import SoftwareEngineering.server.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<List<Reservation>> findByStartTimeBetweenAndField(LocalDateTime start, LocalDateTime end, Field field);

    Optional<List<Reservation>> findByUserAndStartTimeAfterOrderByStartTime(User user, LocalDateTime startTime);
}
