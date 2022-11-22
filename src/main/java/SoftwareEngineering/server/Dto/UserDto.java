package SoftwareEngineering.server.Dto;

import SoftwareEngineering.server.Domain.Major;
import SoftwareEngineering.server.Domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Date;

// 이너 클래스를 이용하여 코드를 간결하게하고 같은 도메인의 Dto를 캡슐화를 한다. 내부 클래스에서 외부 클래스로의 접근을 막기위해 정적 클래스로 생성
public class UserDto {

    @Getter
    public static class UserSetReqDto{
        private String name;

        private String email;

        private String password;

        private int studentId;

        private Long majorIdx;

    }
    @Getter
    public static class UserLoginReqDto{

        private String email;

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
        private Date startTime;
        private Date endTime;
        private Timestamp createdAt;
        private Long fieldIdx;
        private String name;
    }
}
