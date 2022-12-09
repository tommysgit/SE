package SoftwareEngineering.server.Common;

import SoftwareEngineering.server.Common.CustomException.ExistsException;
import SoftwareEngineering.server.Common.CustomException.InvalidRequestException;
import SoftwareEngineering.server.Common.CustomException.NotExistsException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;

@RestControllerAdvice
public class GlobalException {

//    public BaseResponse handleJwtExpireException(ExpiredJwtException e){
//        return
//    }
    @ExceptionHandler(ExistsException.class)
    public BaseResponse handleExistsException(ExistsException e) {

        return BaseResponse.builder().code(e.getErrorCode().getCode()).message(e.getErrorCode().getMessage()).build();
    }
    @ExceptionHandler(InvalidRequestException.class)
    public BaseResponse handleInvalidRequestException(InvalidRequestException e){
        return BaseResponse.builder().code(e.getErrorCode().getCode()).message(e.getErrorCode().getMessage()).build();
    }
    @ExceptionHandler(NotExistsException.class)
    public BaseResponse handleNotExistsException(NotExistsException e){
        return BaseResponse.builder().message(e.getErrorCode().getMessage()).code(e.getErrorCode().getCode()).build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        return BaseResponse.builder().code(400).message(e.getBindingResult().getAllErrors().get(0).getDefaultMessage()).build();
    }
}
