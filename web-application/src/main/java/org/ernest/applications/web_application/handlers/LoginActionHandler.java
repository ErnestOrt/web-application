package org.ernest.applications.web_application.handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.UUID;

import org.ernest.applications.web_application.entities.enums.ApplicationRoles;
import org.ernest.applications.web_application.entities.enums.FrontEndParameters;
import org.ernest.applications.web_application.entities.enums.HttpStatus;
import org.ernest.applications.web_application.exceptions.ForbiddenViewException;
import org.ernest.applications.web_application.providers.SessionsProvider;
import org.ernest.applications.web_application.providers.UsersProvider;
import org.ernest.applications.web_application.providers.ViewsProvider;
import org.ernest.applications.web_application.utils.RequestParserUtils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class LoginActionHandler implements HttpHandler {

	String defaultPage;

	public LoginActionHandler(String defaultPage) {
		this.defaultPage = defaultPage;
	}

	public void handle(HttpExchange httpExchange) throws IOException {
		String response;
		try {
			response = createResponseFromQueryParams(httpExchange.getRequestURI());
			httpExchange.sendResponseHeaders(HttpStatus.OK.getCode(), response.getBytes().length);
		} catch (ForbiddenViewException e) {
			System.out.println(e.getMessage());
			response = createResponseForForbiddenState(httpExchange.getRequestURI());
			httpExchange.sendResponseHeaders(HttpStatus.FORBIDDEN.getCode(), response.getBytes().length);
		}
	
		OutputStream os = httpExchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

	private String createResponseFromQueryParams(URI uri) throws IOException, ForbiddenViewException {
		String username = RequestParserUtils.getParameter(uri.getQuery(), FrontEndParameters.USERNAME.getCode());
		String password = RequestParserUtils.getParameter(uri.getQuery(), FrontEndParameters.PASSWORD.getCode());
		String sessionId = RequestParserUtils.getParameter(uri.getQuery(), FrontEndParameters.SESSION_ID.getCode());
	
		if(UsersProvider.getInstance().validateUser(username, password)){
			System.out.println(username + " validated");
			String pageNumber = defaultPage;
			
			if(SessionsProvider.getInstance().getSessionInformation(sessionId) != null){					
				pageNumber = SessionsProvider.getInstance().getSessionInformation(sessionId).getPageRequiredBeforeLogIn();	
				for (String roleCode : UsersProvider.getInstance().getUserInformation(username).getRoles()) {
					if(ApplicationRoles.getViewFromCode(roleCode).equals(pageNumber)){
						return ViewsProvider.getInstance().pageNumberView(pageNumber, sessionId, username);
					}
				}
				throw new ForbiddenViewException(username + " does not have required role to access " + pageNumber);
			}
			
			SessionsProvider.getInstance().setNewSession(sessionId, username, "");
			return ViewsProvider.getInstance().pageNumberView(pageNumber, sessionId, username);
		}
		return ViewsProvider.getInstance().loginView(UUID.randomUUID().toString());
	}
	
	private String createResponseForForbiddenState(URI uri) throws IOException {
		String sessionId = RequestParserUtils.getParameter(uri.getQuery(), FrontEndParameters.SESSION_ID.getCode());
		return ViewsProvider.getInstance().forbiddenView(sessionId, SessionsProvider.getInstance().getSessionInformation(sessionId).getPageRequiredBeforeLogIn());
	}
}