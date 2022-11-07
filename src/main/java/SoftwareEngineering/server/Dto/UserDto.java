package SoftwareEngineering.server.Dto;

import SoftwareEngineering.server.Domain.Major;
import lombok.Getter;
// 이너 클래스를 이용하여 코드를 간결하게하고 같은 도메인의 Dto를 캡슐화를 한다. 내부 클래스에서 외부 클래스로의 접근을 막기위해 정적 클래스로 생성
public class UserDto {

    @Getter
    public static class UserSetReqDto{
        private String name;

        private String email;

        private String password;

        private Major major;

    }
    @Getter
    public static class UserLoginReqDto{

        private String email;

        private String password;
    }
}
