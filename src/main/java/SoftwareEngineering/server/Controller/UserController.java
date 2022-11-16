package SoftwareEngineering.server.Controller;

import SoftwareEngineering.server.Common.BaseResponse;
import SoftwareEngineering.server.Domain.User;
import SoftwareEngineering.server.Dto.UserDto;
import SoftwareEngineering.server.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @Operation(description = "회원가입")
    @PostMapping("/signUp")
    public BaseResponse<User> UserSetReq(@RequestBody UserDto.UserSetReqDto userSetReqDto){
        User savedUser = userService.setUser(userSetReqDto);
        return BaseResponse.<User>builder().message("회원가입이 완료되었습니다.").code(200).data(savedUser).build();
    }

    @Operation(description = "로그인")
    @PostMapping("/login")
    public BaseResponse<User> UserLoginReq(@RequestBody UserDto.UserLoginReqDto userLoginReqDto){
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
