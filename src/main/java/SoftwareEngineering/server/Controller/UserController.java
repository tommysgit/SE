package SoftwareEngineering.server.Controller;

import SoftwareEngineering.server.Common.BaseResponse;
import SoftwareEngineering.server.Config.Jwt.JwtTokenProvider;
import SoftwareEngineering.server.Domain.User;
import SoftwareEngineering.server.Dto.UserDto;
import SoftwareEngineering.server.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    @Operation(description = "회원가입")
    @PostMapping("/signUp")
    public BaseResponse<User> UserSetReq(@RequestBody UserDto.UserSetReqDto userSetReqDto){
        User savedUser = userService.setUser(userSetReqDto);
        return BaseResponse.<User>builder().message("회원가입이 완료되었습니다.").code(200).data(savedUser).build();
    }

    @Operation(description = "로그인")
    @PostMapping("/login")
    public BaseResponse<User> UserLoginReq(@RequestBody UserDto.UserLoginReqDto userLoginReqDto){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userLoginReqDto.getEmail(),
                userLoginReqDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

       // String jwtToken = jwtTokenProvider.createToken(userLoginReqDto.getEmail(), usernamePasswordAut);
        User loginUser = userService.login(userLoginReqDto);
        return BaseResponse.<User>builder().message("로그인에 성공하였습니다.").code(200).data(loginUser).build();
    }

    @Operation(description = "이메일 중복확인")
    @PostMapping("/check/email/{email}")
    public BaseResponse checkEmail(@PathVariable String email){
        userService.findUserByEmail(email);

        return BaseResponse.builder().message("사용 가능한 이메일입니다.").code(251).build();
    }

}
