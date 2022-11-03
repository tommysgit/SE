package SoftwareEngineering.server.Common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

public class BaseResponse<T> {
    private String message;
    private  Integer code;
    private T data;

    @Builder
    public BaseResponse(String message, Integer code, T data){
        this.message = message;
        this.code = code;
        this.data = data;
    }
}
