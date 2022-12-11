package SoftwareEngineering.server.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public  class BoardListResDto {
        private Long boardIdx;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private int limit;
        private Long fieldIdx;
        private String name;
        private Long reservationIdx;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private Long currentPeople;
    }