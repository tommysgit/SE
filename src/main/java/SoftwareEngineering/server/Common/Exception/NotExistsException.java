package SoftwareEngineering.server.Common.Exception;

import SoftwareEngineering.server.Common.ErrorCode;

public class NotExistsException extends RuntimeException{

    public NotExistsException(ErrorCode errorCode){
        super(errorCode.getMessage());
    }
}
