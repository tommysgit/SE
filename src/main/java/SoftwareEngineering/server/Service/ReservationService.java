package SoftwareEngineering.server.Service;

import SoftwareEngineering.server.Domain.Field;
import SoftwareEngineering.server.Domain.Reservation;
import SoftwareEngineering.server.Dto.ReservationDto;
import SoftwareEngineering.server.Repository.FieldRepository;
import SoftwareEngineering.server.Repository.ReservationRepository;
import SoftwareEngineering.server.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final FieldRepository fieldRepository;

    @Transactional
    // 구장 예약 -> 해당 시간에 예약이 존재할 시 예외처리
    public void setReservation(ReservationDto.ReservationSetReqDto reservationSetReqDto, User userInfo) {
        SoftwareEngineering.server.Domain.User user = userRepository.findByEmailAndIsDelete(userInfo.getUsername(), 'N').get();


        Field field = fieldRepository.findById(reservationSetReqDto.getFieldIdx()).get();


        reservationRepository.save(Reservation.builder().user(user).field(field).startTime(reservationSetReqDto.getStart_time()).build());


    }
    @Transactional(readOnly = true)
    // 예약 조회
    public ReservationDto.ReservationGetResDto getReservation(LocalDate localDate, Long fieldIdx){
        Field field = fieldRepository.findById(fieldIdx).get();
        LocalDateTime startDateTime = LocalDateTime.of(localDate, LocalTime.of(10, 0));
        LocalDateTime endDateTime = LocalDateTime.of(localDate, LocalTime.of(20, 59));


        List<Reservation> reservationList = reservationRepository.findByStartTimeBetweenAndField(startDateTime, endDateTime, field).get();
        //dateList.stream().map(Date::getTime).filter()
        List<LocalDateTime> dateList = new ArrayList<>();
        List<LocalDateTime> reservationDateList = reservationList.stream().map(Reservation::getStartTime).collect(Collectors.toList());
        // 10 ~ 20 사용가능
        for (int i = 10; i < 21; i++) {
            LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.of(i, 0));
            if(!reservationDateList.contains(localDateTime)) {
                dateList.add(localDateTime);
            }
        }
        System.out.println(dateList);
        return ReservationDto.ReservationGetResDto.builder().fieldIdx(field.getFieldIdx()).name(field.getName()).dateList(dateList).build();
    }


}
