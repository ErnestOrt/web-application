package org.ernest.applications.web_application.test;

import junit.framework.Assert;

import org.ernest.applications.web_application.Server;
import org.ernest.applications.web_application.entities.enums.FrontEndParameters;
import org.jboss.resteasy.client.ClientRequest;
import org.junit.Test;

public class RequestViewWithoutBeingLogged {

//	REQUIREMENT	
//	In the case of accessing any of these private pages without a logged session 
//	the application will redirect the user to the login form and once a success 
//	user login is performed the application will redirect the user to the page it 
//	was trying to reach before being redirected to the login form.
	
	@Test
	public void GivenUserWithRolPAGE_2WhenAskingForPageTwoWithoutBeingLogThenPAGE_2IsRetrievedOnceLogged() throws Exception{
		String userTest = "userTEST";
		String userPass = "332r23ewqrwqref23134refdsfdf";

		Server server = new Server(8888);
		server.start();
		ClientRequest request = new ClientRequest("http://localhost:8888/two");
		String sessionId = request.get(String.class).getEntity().split("<input name=\"session-id\" type=\"hidden\" value=\"")[1].split("\" />")[0];

		request = new ClientRequest("http://localhost:8888/login?" 
				+ FrontEndParameters.USERNAME.getCode() + "=" + userTest + "&"
				+ FrontEndParameters.PASSWORD.getCode() + "=" + userPass + "&"
				+ FrontEndParameters.SESSION_ID.getCode() + "=" + sessionId);
		Assert.assertTrue(request.get(String.class).getEntity().contains("Page two"));
		server.stop();
	}
}