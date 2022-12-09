package SoftwareEngineering.server.Common;

import lombok.Getter;

@Getter
public enum ErrorCode {
    PASSWORD_NOT_EQUAL(400, "비밀번호가 일치하지 않습니다."),
    EMAIL_EXISTS(400, "이미 존재하는 이메일입니다."),
    EMAIL_NOT_EXISTS(400, "존재하지 않는 이메일입니다."),
    RESERVATION_NOT_EXISTS(400, "존재하지 않는 예약입니다.");

    private final boolean status = false;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;

    private final String message;


}
