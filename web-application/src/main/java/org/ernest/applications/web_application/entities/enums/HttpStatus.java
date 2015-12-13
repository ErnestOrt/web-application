package org.ernest.applications.web_application.entities.enums;

public enum HttpStatus {

	OK(200),
	FORBIDDEN(403);
	
	private int code;
	
	HttpStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
