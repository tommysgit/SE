package SoftwareEngineering.server.Common.Exception;

import SoftwareEngineering.server.Common.ErrorCode;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(ErrorCode errorCode){
        super(errorCode.getMessage());
    }
}
