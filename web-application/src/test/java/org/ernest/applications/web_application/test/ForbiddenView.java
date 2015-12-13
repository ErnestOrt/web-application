package org.ernest.applications.web_application.test;

import java.util.UUID;

import junit.framework.Assert;

import org.ernest.applications.web_application.Server;
import org.ernest.applications.web_application.entities.enums.FrontEndParameters;
import org.jboss.resteasy.client.ClientRequest;
import org.junit.Test;

public class ForbiddenView {

//	REQUIRMENT
//	In the case of accessing any of these private pages with a logged session but
//	without the necessary role to access the page the application will not allow 
//	the user to see the page returning an appropriate status code indicating that 
//	access was denied.
	
	@Test
	public void GivenUserWithRolPAGE_1AndPAGE_2WhenAskingForPageThreeThenForbiddenMessageIsRetrieved() throws Exception{
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

		request = new ClientRequest("http://localhost:8888/three?" + FrontEndParameters.SESSION_ID.getCode() + "=" + sessionId);		
		Assert.assertTrue(request.get(String.class).getEntity().contains("<H1>ERROR 403: Access was denied</H1>"));
		server.stop();
	}	
}
