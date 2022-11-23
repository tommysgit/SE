package SoftwareEngineering.server.Repository;

import SoftwareEngineering.server.Domain.Board;
import SoftwareEngineering.server.Dto.BoardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query(value = "select b.boardIdx, b.title, b.content, b.createdAt, b.limit, f.fieldIdx, f.name, r.reservationIdx, " +
            "r.startTime, r.endTime, count(m) as currentPeople " +
            "from Board b " +
            "left join b.reservation r " +
            "left join r.field f " +
            "left join b.mercenaries m " +
            "where r.startTime >= :dateTime " +
            "group by m.mercenaryIdx")
    Optional<List<BoardDto.BoardListResDto>> findBoardListByCurrent(@Param("dateTime")LocalDateTime dateTime);
}
