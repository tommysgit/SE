package SoftwareEngineering.server.Common.CustomException;

import SoftwareEngineering.server.Common.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidRequestException extends RuntimeException{
    private final ErrorCode errorCode;
    public InvalidRequestException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
