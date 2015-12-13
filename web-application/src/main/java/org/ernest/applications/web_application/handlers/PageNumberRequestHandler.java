package org.ernest.applications.web_application.handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.UUID;

import org.ernest.applications.web_application.entities.enums.FrontEndParameters;
import org.ernest.applications.web_application.entities.enums.HttpStatus;
import org.ernest.applications.web_application.exceptions.ForbiddenViewException;
import org.ernest.applications.web_application.providers.SessionsProvider;
import org.ernest.applications.web_application.providers.UsersProvider;
import org.ernest.applications.web_application.providers.ViewsProvider;
import org.ernest.applications.web_application.utils.RequestParserUtils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class PageNumberRequestHandler implements HttpHandler{
	
	private String pageNumber;
	private String pageRole;
	
	public PageNumberRequestHandler(String pageNumber, String pageRole){
		this.pageNumber = pageNumber;
		this.pageRole = pageRole;
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

	private String createResponseFromQueryParams(URI uri) throws ForbiddenViewException, IOException {
		String sessionId = RequestParserUtils.getParameter(uri.getQuery(), FrontEndParameters.SESSION_ID.getCode());
		System.out.println("Page " + pageNumber + " requested by session " + sessionId);
		if(sessionId != null && !SessionsProvider.getInstance().hasSessionExpired(sessionId)){
			String username = SessionsProvider.getInstance().getSessionInformation(sessionId).getUserName();
	
			if(!UsersProvider.getInstance().getUserInformation(username).getRoles().contains(pageRole)){
				throw new ForbiddenViewException(username + " does not have required role to access " + pageNumber);
			}			
			return ViewsProvider.getInstance().pageNumberView(pageNumber,sessionId, username);
		}
		String newSessionId = UUID.randomUUID().toString();
		SessionsProvider.getInstance().setNewSession(newSessionId, null, uri.getPath().replaceAll("/", ""));
		return ViewsProvider.getInstance().loginView(newSessionId);
	}
	
	private String createResponseForForbiddenState(URI uri) {
		String sessionId = RequestParserUtils.getParameter(uri.getQuery(), FrontEndParameters.SESSION_ID.getCode());
		return ViewsProvider.getInstance().forbiddenView(sessionId, pageNumber);
	}
}
