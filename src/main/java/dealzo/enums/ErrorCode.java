package dealzo.enums;

public enum ErrorCode {

	//COMMON ERRORS
	INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
	//Dealzo Error
	DEALZO_SERVICE_ERROR(1001, "Dealzo Service Error");


	private int statusCode;
	private String message;


	private ErrorCode(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getStatusCode() {
		return statusCode;
	}
	
	public String toString(){
		return message;
	}
	
}