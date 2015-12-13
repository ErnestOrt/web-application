package org.ernest.applications.web_application.test;

import java.util.UUID;

import junit.framework.Assert;

import org.ernest.applications.web_application.Server;
import org.ernest.applications.web_application.entities.enums.FrontEndParameters;
import org.jboss.resteasy.client.ClientRequest;
import org.junit.Test;

public class MandatoryViewContent {

//	REQUIRMENT
//	Each page will simply show the name of the page the user is accessing and the text “Hello {USER_NAME}”.
//	All pages will also have a link/button to close the user session.
	
	@Test
	public void GivenSuperUserLoggedWhenRequiredViewsThenRequiredContentIsPresent() throws Exception{
		String userTest = "superuser";
		String userPass = "nower823b923rn0afha9edfh2fno2";
		String sessionId = UUID.randomUUID().toString();
		
		Server server = new Server(8888);
		server.start();
		ClientRequest request = new ClientRequest("http://localhost:8888/login?" 
													+ FrontEndParameters.USERNAME.getCode() + "=" + userTest + "&"
													+ FrontEndParameters.PASSWORD.getCode() + "=" + userPass + "&"
													+ FrontEndParameters.SESSION_ID.getCode() + "=" + sessionId);
		String response = request.get(String.class).getEntity();
		Assert.assertTrue(response.contains("Page one"));
		Assert.assertTrue(response.contains("Hello superuser"));
		Assert.assertTrue(response.contains("<button type=\"submit\">Log Out</button>"));
		
		request = new ClientRequest("http://localhost:8888/two?" + FrontEndParameters.SESSION_ID.getCode() + "=" + sessionId);
	    response = request.get(String.class).getEntity();
		Assert.assertTrue(response.contains("Page two"));
		Assert.assertTrue(response.contains("Hello superuser"));
		Assert.assertTrue(response.contains("<button type=\"submit\">Log Out</button>"));

		request = new ClientRequest("http://localhost:8888/three?" + FrontEndParameters.SESSION_ID.getCode() + "=" + sessionId);
		response = request.get(String.class).getEntity();
		Assert.assertTrue(response.contains("Page three"));
		Assert.assertTrue(response.contains("Hello superuser"));
		Assert.assertTrue(response.contains("<button type=\"submit\">Log Out</button>"));
		server.stop();
	}
}