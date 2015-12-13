package org.ernest.applications.web_application.entities.enums;

public enum FrontEndParameters {
	
	USERNAME("input-user"),
	PASSWORD("input-password"),
	SESSION_ID("session-id");
	
	private String code;
	
	FrontEndParameters(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
