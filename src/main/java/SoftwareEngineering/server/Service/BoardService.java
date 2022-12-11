package SoftwareEngineering.server.Service;

import SoftwareEngineering.server.Common.ErrorCode;
import SoftwareEngineering.server.Common.CustomException.NotExistsException;
import SoftwareEngineering.server.Domain.Board;
import SoftwareEngineering.server.Domain.Mercenary;
import SoftwareEngineering.server.Domain.Reservation;
import SoftwareEngineering.server.Dto.BoardDto;
import SoftwareEngineering.server.Dto.BoardListResDto;
import SoftwareEngineering.server.Repository.BoardRepository;
import SoftwareEngineering.server.Repository.MercenaryRepository;
import SoftwareEngineering.server.Repository.ReservationRepository;
import SoftwareEngineering.server.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final MercenaryRepository mercenaryRepository;
    @Transactional(readOnly = true)
    public List<BoardListResDto> getBoardList(){
        return boardRepository.findBoardListByCurrent(LocalDateTime.now()).get();
    }

    @Transactional
    public void setBoard(BoardDto.BoardSetReqDto boardSetReqDto, User userInfo){
        Reservation reservation = reservationRepository.findById(boardSetReqDto.getReservationIdx()).get();

        if(!reservation.getUser().getEmail().equals(userInfo.getUsername())){
            throw new NotExistsException(ErrorCode.EMAIL_NOT_EXISTS);
        }

        Board board = Board.builder().title(boardSetReqDto.getTitle()).content(boardSetReqDto.getContent())
                .limit(boardSetReqDto.getLimit()).isDelete('N').reservation(reservation).build();
        boardRepository.save(board);
    }

    @Transactional
    public void setMercenary(Long boardIdx, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userInfo){
        SoftwareEngineering.server.Domain.User user = userRepository.findByEmailAndIsDelete(userInfo.getUsername(), 'N').get();
        Board board = boardRepository.findById(boardIdx).get();
        Mercenary mercenary = Mercenary.builder().board(board).user(user).build();
        mercenaryRepository.save(mercenary);
    }
}
