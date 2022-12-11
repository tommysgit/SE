package SoftwareEngineering.server.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
@Getter
public class BoardDto {

//    @Getter
//    @Builder
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public class BoardListResDto {
//        private Long boardIdx;
//        private String title;
//        private String content;
//        private LocalDateTime createdAt;
//        private int limit;
//        private Long fieldIdx;
//        private String name;
//        private Long reservationIdx;
//        private LocalDateTime startTime;
//        private LocalDateTime endTime;
//        private Long currentPeople;
//    }

    @Getter
    public static class BoardSetReqDto{
        @NotNull(message = "예약 인덱스는 필수 입력값입니다.")
        private Long reservationIdx;
        @NotBlank(message = "제목은 필수 입력값입니다.")
        private String title;
        @NotBlank(message = "내용은 필수 입력값입니다.")
        private String content;
        @NotNull(message = "제한은 필수 입력값입니다.")
        private int limit;
    }
}
