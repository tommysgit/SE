package SoftwareEngineering.server.Dto;

import SoftwareEngineering.server.Domain.Major;
import SoftwareEngineering.server.Domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

// 이너 클래스를 이용하여 코드를 간결하게하고 같은 도메인의 Dto를 캡슐화를 한다. 내부 클래스에서 외부 클래스로의 접근을 막기위해 정적 클래스로 생성
public class UserDto {

    @Getter
    public static class UserSetReqDto{
        @NotBlank(message = "이름은 필수 입력값입니다.")
        private String name;
        @NotBlank(message = "이메일은 필수 입력값입니다.")
        @Pattern(regexp = "^[a-zA-z0-9]+@ajou.ac.kr", message = "이메일 형식을 준수해주세요")
        private String email;
        @NotBlank(message = "비밀번호는 필수 입력값입니다.")
        private String password;
        @NotNull(message = "학번은 필수 입력값입니다.")
        private int studentId;
        @NotNull(message = "학과 인덱스는 필수 입력값입니다.")
        private Long majorIdx;
        @NotBlank(message = "핸드폰 번호는 필수 입력값입니다.")
        private String phoneNum;
    }
    @Getter
    public static class UserLoginReqDto{
        @NotBlank(message = "이메일은 필수 입력값입니다.")
        private String email;
        @NotBlank(message = "비밀번호는 필수 입력값입니다.")
        private String password;
    }
    @Getter
    @Builder
    public static class UserLoginResDto{
        private String name;
        private int studentId;
        private String email;
        private String token;
    }

    @Getter
    @Builder
    public static class UserReservationListResDto{
        private Long reservationIdx;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private LocalDateTime createdAt;
        private Long fieldIdx;
        private String name;
    }
}
