package org.ernest.applications.web_application.handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.UUID;

import org.ernest.applications.web_application.entities.enums.FrontEndParameters;
import org.ernest.applications.web_application.entities.enums.HttpStatus;
import org.ernest.applications.web_application.providers.SessionsProvider;
import org.ernest.applications.web_application.providers.ViewsProvider;
import org.ernest.applications.web_application.utils.RequestParserUtils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class LogoutActionHandler implements HttpHandler {
	
	public void handle(HttpExchange httpExchange) throws IOException {
		String response = createResponseFromQueryParams(httpExchange.getRequestURI());
		
		httpExchange.sendResponseHeaders(HttpStatus.OK.getCode(), response.getBytes().length);
		
		OutputStream os = httpExchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

	private String createResponseFromQueryParams(URI uri) throws IOException {
		String sessionId = RequestParserUtils.getParameter(uri.getQuery(), FrontEndParameters.SESSION_ID.getCode());
		SessionsProvider.getInstance().removeSession(sessionId);
		return ViewsProvider.getInstance().loginView(UUID.randomUUID().toString());
	}
}