package SoftwareEngineering.server.Service;

import SoftwareEngineering.server.Common.ErrorCode;
import SoftwareEngineering.server.Common.CustomException.NotExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    // 스프링에의해 관리되는 객체가 아니므로 생성자 생성?
   // public UserDetailsServiceImpl(UserService userService){
//        this.userService = userService;
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SoftwareEngineering.server.Domain.User user = userService.findUserByEmailAndIsDelete(email)
                .orElseThrow(()-> new NotExistsException(ErrorCode.EMAIL_NOT_EXISTS));

        return User.builder().
                username(user.getEmail())
                        .password(user.getPassword())
                                .roles(user.getRole()).build();
    }
}
