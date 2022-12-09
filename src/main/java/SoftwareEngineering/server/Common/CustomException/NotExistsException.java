package SoftwareEngineering.server.Common.CustomException;

import SoftwareEngineering.server.Common.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistsException extends RuntimeException{
    private final ErrorCode errorCode;

    public NotExistsException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
