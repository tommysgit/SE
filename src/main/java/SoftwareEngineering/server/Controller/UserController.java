package SoftwareEngineering.server.Controller;

import SoftwareEngineering.server.Common.BaseResponse;
import SoftwareEngineering.server.Common.ErrorCode;
import SoftwareEngineering.server.Common.CustomException.ExistsException;
import SoftwareEngineering.server.Config.Jwt.JwtTokenProvider;
import SoftwareEngineering.server.Domain.User;
import SoftwareEngineering.server.Dto.UserDto;
import SoftwareEngineering.server.Repository.UserRepository;
import SoftwareEngineering.server.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "User", description = "유저 API")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    @Operation(description = "회원가입", summary = "회원가입")
    @PostMapping("/signUp")
    public BaseResponse UserSetReq(@Valid @RequestBody UserDto.UserSetReqDto userSetReqDto){
        if(userRepository.findByEmailAndIsDelete(userSetReqDto.getEmail(), 'N').isPresent()){
            throw new ExistsException(ErrorCode.EMAIL_EXISTS);
        }
        userService.setUser(userSetReqDto);
        return BaseResponse.builder().message("회원가입이 완료되었습니다.").code(200).build();
    }

    @Operation(description = "로그인", summary = "로그인")
    @PostMapping("/login")
    //@ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(schema = @Schema(implementation = ApiCallResponse.ApiCallResponseLogin.class)))
    public BaseResponse<UserDto.UserLoginResDto> UserLoginReq(@Valid @RequestBody UserDto.UserLoginReqDto userLoginReqDto){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userLoginReqDto.getEmail(),
                userLoginReqDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtTokenProvider.createToken(authentication);
        User loginUser = userService.login(userLoginReqDto);
        System.out.println(loginUser.getName());
        return BaseResponse.<UserDto.UserLoginResDto>builder().message("로그인에 성공하였습니다.").code(200).data(UserDto.UserLoginResDto.builder()
                .name(loginUser.getName()).email(loginUser.getEmail()).studentId(loginUser.getStudentId()).token(jwtToken).build()).build();
    }

    @Operation(description = "이메일 중복확인", summary = "이메일 중복확인")
    @PostMapping("/check/email/{email}")
    public BaseResponse checkEmail(@PathVariable String email){
        userService.findUserByEmail(email);
        return BaseResponse.builder().message("사용 가능한 이메일입니다.").code(251).build();
    }
    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @Operation(description = "유저 예약내역 조회", summary = "유저 예약내역 조회")
    @GetMapping("/reservation")
    public BaseResponse<List<UserDto.UserReservationListResDto>> UserReservationListReq(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userInfo){
        return BaseResponse.<List<UserDto.UserReservationListResDto>>builder().code(200).message("성공").data(userService.findUserReservation(userInfo)).build();
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @Operation(description = "유저 예약내역 삭제", summary = "유저 예약내역 삭제")
    @DeleteMapping("/reservation/{id}")
    public BaseResponse deleteUserReservationReq(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userInfo, @PathVariable Long id ){
        userService.deleteUserReservation(id);
        return BaseResponse.builder().code(200).message("성공").build();
    }
}
