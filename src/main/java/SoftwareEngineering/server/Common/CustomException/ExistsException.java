package SoftwareEngineering.server.Common.CustomException;

import SoftwareEngineering.server.Common.ErrorCode;
import lombok.Getter;

@Getter
public class ExistsException extends RuntimeException{
    private final ErrorCode errorCode;
    public ExistsException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
