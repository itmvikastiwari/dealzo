package dealzo.response;

import dealzo.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DealzoResponseEntity extends ResponseEntity<ResponseWrapper> {

	private DealzoResponseEntity(ResponseWrapper body, HttpStatus status) {
		super(body, status);
	}
	
	public static DealzoResponseEntity buildSuccessResponse(Object payload) {
		ResponseWrapper responseWrapper = new ResponseWrapper();
		responseWrapper.setHttpCode(200);
		responseWrapper.setPayload(payload);
		responseWrapper.setStatus("Success");
		return new DealzoResponseEntity(responseWrapper, HttpStatus.OK);
	}
	
	public static DealzoResponseEntity buildErrorResponse(ErrorCode errorCode, String message) {
		ResponseWrapper responseWrapper = new ResponseWrapper();
		responseWrapper.setHttpCode(errorCode.getStatusCode());
		responseWrapper.setMessage(message);
		responseWrapper.setStatus(errorCode.getMessage());
		return new DealzoResponseEntity(responseWrapper, HttpStatus.BAD_REQUEST);
	}

}
