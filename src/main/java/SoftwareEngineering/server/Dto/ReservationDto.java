package SoftwareEngineering.server.Dto;

import SoftwareEngineering.server.Domain.Field;
import SoftwareEngineering.server.Domain.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ReservationDto {

    @Getter
    public static class ReservationSetReqDto{

        private Long fieldIdx;
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
