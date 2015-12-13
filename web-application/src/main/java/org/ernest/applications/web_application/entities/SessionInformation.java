package org.ernest.applications.web_application.entities;

import java.util.Date;

public class SessionInformation {

	private String id;
	private String userName;
	private Date lastConnection;
	private String pageRequiredBeforeLogIn;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getLastConnection() {
		return lastConnection;
	}
	public void setLastConnection(Date lastConnection) {
		this.lastConnection = lastConnection;
	}
	public String getPageRequiredBeforeLogIn() {
		return pageRequiredBeforeLogIn;
	}
	public void setPageRequiredBeforeLogIn(String pageRequiredBeforeLogIn) {
		this.pageRequiredBeforeLogIn = pageRequiredBeforeLogIn;
	}
}
