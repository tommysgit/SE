package SoftwareEngineering.server.Dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class BoardDto {

    @Getter
    @Builder
    public static class BoardListResDto {
        private Long boardIdx;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private int limit;
        private Long filedIdx;
        private String name;
        private Long reservationIdx;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private int currentPeople;
    }

    @Getter
    @Builder
    public static class BoardSetReqDto{
        private Long reservationIdx;
        private String title;
        private String content;
        private int limit;
    }
}
