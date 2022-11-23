package SoftwareEngineering.server.Common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@Getter
public class BaseResponse<T> {
    @Schema(required = true, example = "성공")
    private String message;
    @Schema(required = true, example = "200")
    private  Integer code;
    @Schema
    private T data;

    @Builder
    public BaseResponse(String message, Integer code, T data){
        this.message = message;
        this.code = code;
        this.data = data;
    }
}
