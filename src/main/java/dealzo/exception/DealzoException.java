package dealzo.exception;


import dealzo.enums.ErrorCode;

public class DealzoException extends RuntimeException {

private static final long serialVersionUID = 1L;
	
	private ErrorCode errorCode;
	
	private String message;

	public DealzoException(Exception e) {
		super(e);
		errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
		message = e.getMessage();
	}
	
	public DealzoException(DealzoException e) {
		super(e);
		this.errorCode = e.getErrorCode();
		message = e.getCustomMessage();
	}
	
	public DealzoException(Exception e, ErrorCode errorCode) {
		this(e);
		this.errorCode = errorCode;
		message = e.getMessage();
	}

	public DealzoException(String message, ErrorCode errorCode) {
		this.errorCode = errorCode;
		this.message = message;
	}
	
	public DealzoException(Exception e, ErrorCode errorCode, String message) {
		this(e);
		this.errorCode = errorCode;
		this.message = message;
	}
	
	public DealzoException(ErrorCode errorCode, String message) {
		this(new Exception());
		this.errorCode = errorCode;
		this.message = message;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
	public String getCustomMessage() {
		return message;
	}
	

}
