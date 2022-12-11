package SoftwareEngineering.server.Dto;

import SoftwareEngineering.server.Domain.Field;
import SoftwareEngineering.server.Domain.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ReservationDto {

    @Getter
    public static class ReservationSetReqDto{
        @NotNull(message = "필드 인덱스는 필수 입력값입니다.")
        private Long fieldIdx;
        @NotBlank(message = "시작 시간은 필수 입력값입니다.")
        private LocalDateTime start_time;

    }

    @Getter
    @Builder
    public static class ReservationGetResDto{
        private Long fieldIdx;
        private String name;
        private List<LocalDateTime> dateList;
    }


}
