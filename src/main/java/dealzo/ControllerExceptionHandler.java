package dealzo;

import dealzo.enums.ErrorCode;
import dealzo.exception.DealzoException;
import dealzo.response.DealzoResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
	
	static Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);
	
	@ExceptionHandler(value = DealzoException.class)
    public DealzoResponseEntity exceptionHandler(DealzoException e) {
		log.error("Dealzo Exception Readable Message {}, StackTrace {}", e.getCustomMessage(), e.getStackTrace());
		return DealzoResponseEntity.buildErrorResponse(e.getErrorCode(), e.getCustomMessage());
	}
	
	@ExceptionHandler(value = Exception.class)
    public DealzoResponseEntity exceptionHandler(Exception e) {
		log.error("Exception Readable Message {}, StackTrace {}", e.getMessage(), e.getStackTrace());
		return DealzoResponseEntity.buildErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
	}
	
}
