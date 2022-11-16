package SoftwareEngineering.server.Service;

import SoftwareEngineering.server.Common.ErrorCode;
import SoftwareEngineering.server.Common.Exception.InvalidRequestException;
import SoftwareEngineering.server.Common.Exception.NotExistsException;
import SoftwareEngineering.server.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    // 스프링에의해 관리되는 객체가 아니므로 생성자 생성?
    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SoftwareEngineering.server.Domain.User user = userRepository.findByEmailAndIsDelete(email, 'N')
                .orElseThrow(()-> new NotExistsException(ErrorCode.EMAIL_NOT_EXISTS));

        return User.builder().
                username(user.getEmail())
                        .password(user.getPassword())
                                .
                build();
    }
}