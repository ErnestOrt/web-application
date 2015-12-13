package org.ernest.applications.web_application.test;

import java.util.UUID;

import junit.framework.Assert;

import org.ernest.applications.web_application.Server;
import org.ernest.applications.web_application.entities.enums.FrontEndParameters;
import org.jboss.resteasy.client.ClientRequest;
import org.junit.Test;

public class SessionExpired {

//	REQUIRMENT
//	The user session will expire in 5 minutes from the last user action.
	
	@Test
	public void GivenUserLoggedWhenRequiredTimeHasPassThenLogInViewIsRetrieved() throws Exception{
		int sessionDurationInMinutes = 5;
		
		String userTest = "userTEST";
		String userPass = "332r23ewqrwqref23134refdsfdf";
		String sessionId = UUID.randomUUID().toString();
		
		Server server = new Server(8888);
		server.start();
		ClientRequest request = new ClientRequest("http://localhost:8888/login?" 
													+ FrontEndParameters.USERNAME.getCode() + "=" + userTest + "&"
													+ FrontEndParameters.PASSWORD.getCode() + "=" + userPass + "&"
													+ FrontEndParameters.SESSION_ID.getCode() + "=" + sessionId);
		Assert.assertTrue(request.get(String.class).getEntity().contains("<H1>Page one</H1>"));
		
		Thread.sleep(1000 * 60 * sessionDurationInMinutes);
		request = new ClientRequest("http://localhost:8888/one?" + FrontEndParameters.SESSION_ID.getCode() + "=" + sessionId);
		Assert.assertTrue(request.get(String.class).getEntity().contains("<H1>Log In form</H1>"));
		server.stop();
	}
}