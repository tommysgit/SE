package SoftwareEngineering.server.Common.Exception;

import SoftwareEngineering.server.Common.ErrorCode;

public class ExistsException extends RuntimeException{
    public ExistsException(ErrorCode errorCode){
        super(errorCode.getMessage());
    }
}
