package SoftwareEngineering.server.Service;

import SoftwareEngineering.server.Domain.Field;
import SoftwareEngineering.server.Domain.Reservation;
import SoftwareEngineering.server.Domain.User;
import SoftwareEngineering.server.Dto.ReservationDto;
import SoftwareEngineering.server.Repository.FieldRepository;
import SoftwareEngineering.server.Repository.ReservationRepository;
import SoftwareEngineering.server.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final FieldRepository fieldRepository;

    public Reservation setReservation(ReservationDto.ReservationSetReqDto reservationSetReqDto){
        Field field = fieldRepository.findById(reservationSetReqDto.getFieldIdx()).get();
        User user = userRepository.findById(reservationSetReqDto.getUserIdx()).get();

        Reservation reservation = Reservation.builder().user(user).field(field).startTime(reservationSetReqDto.getStart_time())
                .endTime(reservationSetReqDto.getEnd_time())
                .build();
        Reservation savedReservation = reservationRepository.save(reservation);

        return savedReservation;
    }
}
