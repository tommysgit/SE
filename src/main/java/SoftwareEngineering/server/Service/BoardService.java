package SoftwareEngineering.server.Service;

import SoftwareEngineering.server.Domain.Board;
import SoftwareEngineering.server.Dto.BoardDto;
import SoftwareEngineering.server.Repository.BoardRepository;
import SoftwareEngineering.server.Repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final ReservationRepository reservationRepository;
    @Transactional(readOnly = true)
    public List<BoardDto.BoardListResDto> getBoardList(){
        return boardRepository.findBoardListByCurrent(LocalDateTime.now()).get();
    }

    @Transactional
    public void setBoard(BoardDto.BoardSetReqDto boardSetReqDto){
        Board board = Board.builder().title(boardSetReqDto.getTitle()).content(boardSetReqDto.getContent())
                .limit(boardSetReqDto.getLimit()).reservation(reservationRepository.findById(boardSetReqDto.getReservationIdx()).get()).build();
        boardRepository.save(board);
    }

}
