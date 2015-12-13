package org.ernest.applications.web_application.providers;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.ernest.applications.web_application.Server;
import org.ernest.applications.web_application.entities.SessionInformation;

public class SessionsProvider {
	
	private static SessionsProvider instance = null;
	
	private Map<String, SessionInformation> sessions = new HashMap<String, SessionInformation>();
	private int sessionDurationMinuts;
	
	private SessionsProvider() throws IOException{
		Properties prop = new Properties();
		prop.load(Server.class.getClassLoader().getResourceAsStream("application.properties"));
		sessionDurationMinuts = Integer.valueOf(prop.getProperty("session.duration.minutes"));
	}

	public static synchronized SessionsProvider getInstance() throws IOException {
		if (instance == null) {
			instance = new SessionsProvider();
		}
		return instance;
	}
	
	public void setNewSession(String sessionId, String userName, String pageRequiredBeforeLogIn) {
		SessionInformation sessionInformation = new SessionInformation();
		sessionInformation.setId(sessionId);
		sessionInformation.setUserName(userName);
		sessionInformation.setLastConnection(new Date());
		sessionInformation.setPageRequiredBeforeLogIn(pageRequiredBeforeLogIn);
		sessions.put(sessionId, sessionInformation);
		
		System.out.println(sessionId + " has been created");
	}
	
	public SessionInformation getSessionInformation(String sessionId) {
		SessionInformation sessionInformation = sessions.get(sessionId);
		if(sessionInformation != null){
			sessionInformation.setLastConnection(new Date());
			sessions.put(sessionId, sessionInformation);
		}
		return sessionInformation;
	}
	
	public boolean hasSessionExpired(String sessionId){
		if(getMinutsSinceLastConnection(sessions.get(sessionId).getLastConnection()) >= sessionDurationMinuts){
			System.out.println(sessionId + " has expired");
			return true;
		}
		return false;
	}

	public void removeSession(String sessionId) {
		sessions.remove(sessionId);
		System.out.println(sessionId + " has been removed");
	}
	
	private long getMinutsSinceLastConnection(Date lastConnection) {
		long differenceInMillies = new Date().getTime() - lastConnection.getTime();
		return TimeUnit.MINUTES.convert(differenceInMillies,TimeUnit.MILLISECONDS);
	}
}
