package SoftwareEngineering.server.Service;

import SoftwareEngineering.server.Common.ErrorCode;
import SoftwareEngineering.server.Common.Exception.ExistsException;
import SoftwareEngineering.server.Common.Exception.NotExistsException;
import SoftwareEngineering.server.Domain.User;
import SoftwareEngineering.server.Dto.UserDto;
import SoftwareEngineering.server.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
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
    // 회원가입
    public User setUser(UserDto.UserSetReqDto userSetReqDto){
        String hashPassword = passwordEncoder.encode(userSetReqDto.getPassword());
        User user = User.builder().name(userSetReqDto.getName()).email(userSetReqDto.getEmail())
                .major(userSetReqDto.getMajor()).password(hashPassword).build();
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

}
