package SoftwareEngineering.server.Dto;

import lombok.Builder;
import lombok.Getter;

public class MajorDto {

    @Getter
    @Builder
    public static class MajorListResDto{
        private Long majorIdx;
        private String name;
    }
}
