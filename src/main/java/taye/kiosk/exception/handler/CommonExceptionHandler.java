package taye.kiosk.exception.handler;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {
	
//	@ExceptionHandler(EntityNotFoundException.class)
//	protected ResponseEntity<> handleBindException(EntityNotFoundException e){
//		
//	}

}
