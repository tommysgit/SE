package SoftwareEngineering.server.Dto;

import lombok.Builder;
import lombok.Getter;

public class FieldDto {
    @Builder
    @Getter
    public static class FiledListResDto{
        private Long fieldIdx;
        private String name;
    }
}
