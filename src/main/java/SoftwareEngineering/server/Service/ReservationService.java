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
    public Reservation setReservation(ReservationDto.ReservationSetReqDto reservationSetReqDto, User userInfo) {
        SoftwareEngineering.server.Domain.User user = userRepository.findByEmailAndIsDelete(userInfo.getUsername(), 'N').get();
        Field field = fieldRepository.findById(reservationSetReqDto.getFieldIdx()).get();

        Date start = Date.from(reservationSetReqDto.getStart_time().atZone(ZoneId.systemDefault()).toInstant());
        reservationSetReqDto.getStart_time().plusHours(1);
        Date end = Date.from(reservationSetReqDto.getStart_time().atZone(ZoneId.systemDefault()).toInstant());

        Reservation reservation = reservationRepository.save(Reservation.builder().user(user).field(field).startTime(start).endTime(end).build());

        return reservation;
    }
    @Transactional(readOnly = true)
    // 예약 조회
    public ReservationDto.ReservationGetResDto getReservation(LocalDate localDate, Long fieldIdx){
        Field field = fieldRepository.findById(fieldIdx).get();
        LocalDateTime startDateTime = LocalDateTime.of(localDate, LocalTime.of(10, 0));
        LocalDateTime endDateTime = LocalDateTime.of(localDate, LocalTime.of(20, 59));

        Date start = Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(endDateTime.atZone(ZoneId.systemDefault()).toInstant());
        List<Reservation> reservationList = reservationRepository.findByStartTimeBetweenAndField(start, end, field).get();
        List<Date> reservationDateList = reservationList.stream().map(Reservation::getStartTime).collect(Collectors.toList());
        //dateList.stream().map(Date::getTime).filter()
        List<Date> dateList = new ArrayList<>();
        // 10 ~ 20 사용가능
        for (int i = 10; i < 21; i++) {
            Date date = Date.from(LocalDateTime.of(localDate, LocalTime.of(i, 0)).atZone(ZoneId.systemDefault()).toInstant());
            if(!reservationDateList.contains(date)) {
                dateList.add(date);
            }
        }
        System.out.println(dateList);
        return ReservationDto.ReservationGetResDto.builder().fieldIdx(field.getFieldIdx()).name(field.getName()).dateList(dateList).build();
    }


}
