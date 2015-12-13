package org.ernest.applications.web_application.handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.ernest.applications.web_application.entities.enums.HttpStatus;
import org.ernest.applications.web_application.providers.ViewsProvider;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class LoginRequestHandler implements HttpHandler {
	
	public void handle(HttpExchange httpExchange) throws IOException {
		System.out.println("Login View Requested");
		String response = ViewsProvider.getInstance().loginView(UUID.randomUUID().toString());
		
		httpExchange.sendResponseHeaders(HttpStatus.OK.getCode(), response.getBytes().length);
		
		OutputStream os = httpExchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}
}