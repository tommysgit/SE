package SoftwareEngineering.server.Service;

import SoftwareEngineering.server.Common.ErrorCode;
import SoftwareEngineering.server.Common.CustomException.ExistsException;
import SoftwareEngineering.server.Common.CustomException.NotExistsException;
import SoftwareEngineering.server.Domain.Major;
import SoftwareEngineering.server.Domain.Reservation;
import SoftwareEngineering.server.Domain.User;
import SoftwareEngineering.server.Domain.enums.Role;
import SoftwareEngineering.server.Dto.UserDto;
import SoftwareEngineering.server.Repository.FieldRepository;
import SoftwareEngineering.server.Repository.MajorRepository;
import SoftwareEngineering.server.Repository.ReservationRepository;
import SoftwareEngineering.server.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final FieldRepository fieldRepository;
    private final ReservationRepository reservationRepository;
    private final MajorRepository majorRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public void findUserByEmail(String email){
        User user = userRepository.findByEmailAndIsDelete(email, 'N')
                .get();

        if(user != null){
            throw new ExistsException(ErrorCode.EMAIL_EXISTS);
        }
    }

    @Transactional(readOnly = true)
    // 존재하는 계정조회
    public Optional<User> findUserByEmailAndIsDelete(String email){
        return userRepository.findByEmailAndIsDelete(email, 'N');
    }
    @Transactional
    // 회원가입
    public User setUser(UserDto.UserSetReqDto userSetReqDto){
        String hashPassword = passwordEncoder.encode(userSetReqDto.getPassword());
        Major userMajor = majorRepository.findById(userSetReqDto.getMajorIdx()).get();
        User user = User.builder().name(userSetReqDto.getName()).email(userSetReqDto.getEmail()).studentId(userSetReqDto.getStudentId())
                .major(userMajor).password(hashPassword).phoneNum(userSetReqDto.getPhoneNum()).role(Role.ROLE_MEMBER).isDelete('N').build();
        User savedUser = userRepository.save(user);
        return savedUser;
    }
    // readonly는 flush가 발생하지 않아 엔티티의 등록 수정 삭제가 일어나지 않고, 변경감지를 위한 스냅샷을 보관하지 않아 성능 향상
    @Transactional(readOnly = true)
    // 로그인
    public User login(UserDto.UserLoginReqDto userLoginReqDto){
        User loginUser = userRepository.findByEmailAndIsDelete(userLoginReqDto.getEmail(), 'N')
                        .get();
        loginUser.checkPassword(passwordEncoder, passwordEncoder.encode(loginUser.getPassword()));
        return loginUser;
    }

    @Transactional(readOnly = true)
    // 회원 예약내역 조회
    public List<UserDto.UserReservationListResDto> findUserReservation(org.springframework.security.core.userdetails.User user){
        User findUser = userRepository.findByEmailAndIsDelete(user.getUsername(), 'N').get();
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        List<Reservation> reservationList = reservationRepository.findByUserAndStartTimeAfterOrderByStartTime(findUser, LocalDateTime.now()).get();

        List<UserDto.UserReservationListResDto> userReservationListResDtoList = new ArrayList<>();
        for (Reservation reservation: reservationList) {
            userReservationListResDtoList.add(UserDto.UserReservationListResDto.builder().reservationIdx(reservation.getReservationIdx()).fieldIdx(reservation.getField().getFieldIdx())
                    .name(reservation.getField().getName()).createdAt(reservation.getCreatedAt()).startTime(reservation.getStartTime()).endTime(reservation.getEndTime()).build());
        }
        return userReservationListResDtoList;
    }

    @Transactional
    // 회원 예약내역 삭제
    public void deleteUserReservation(Long reservationIdx){

        Reservation reservation = reservationRepository.findById(reservationIdx).orElseThrow(()-> new NotExistsException(ErrorCode.RESERVATION_NOT_EXISTS));

        reservationRepository.delete(reservation);
    }

}
