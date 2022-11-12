package SoftwareEngineering.server.Dto;

import SoftwareEngineering.server.Domain.Field;
import SoftwareEngineering.server.Domain.User;
import lombok.Getter;

import java.util.Date;

public class ReservationDto {

    @Getter
    public static class ReservationSetReqDto{
        private Long userIdx;

        private Long fieldIdx;

        private Date start_time;

        private Date end_time;
    }
}
