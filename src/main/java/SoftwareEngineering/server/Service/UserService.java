package SoftwareEngineering.server.Service;

import SoftwareEngineering.server.Domain.User;
import SoftwareEngineering.server.Dto.UserDto;
import SoftwareEngineering.server.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional(readOnly = true)
    // 회원가입
    public User setUser(UserDto.UserSetReqDto userSetReqDto){
        String hashPassword = passwordEncoder.encode(userSetReqDto.getPassword());
        User user = User.builder().name(userSetReqDto.getName()).email(userSetReqDto.getEmail())
                .major(userSetReqDto.getMajor()).password(hashPassword).build();
        User savedUser = userRepository.save(user);
        return savedUser;
    }
    @Transactional(readOnly = true)
    // 로그인
    public User login(UserDto.UserLoginReqDto userLoginReqDto){
        User loginUser = userRepository.findByEmailAndIsDelete(userLoginReqDto.getEmail(), 'N');
        loginUser.checkPassword(passwordEncoder, passwordEncoder.encode(loginUser.getPassword()));
        return loginUser;
    }

}
