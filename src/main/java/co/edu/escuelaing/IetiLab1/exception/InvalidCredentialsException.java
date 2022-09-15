package co.edu.escuelaing.IetiLab1.exception;

import javax.ws.rs.InternalServerErrorException;

import org.springframework.http.HttpStatus;

import co.edu.escuelaing.IetiLab1.error.ErrorCodeEnum;
import co.edu.escuelaing.IetiLab1.error.ServerErrorResponseDto;

public class InvalidCredentialsException extends InternalServerErrorException {
    public InvalidCredentialsException() {

        super(new ServerErrorResponseDto("User not found", ErrorCodeEnum.USER_NOT_FOUND, HttpStatus.NOT_FOUND)
                .getMessage());

    }
}
