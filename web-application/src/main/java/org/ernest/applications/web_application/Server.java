package org.ernest.applications.web_application;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.util.Properties;

import org.ernest.applications.web_application.entities.enums.ApplicationRoles;
import org.ernest.applications.web_application.handlers.LoginActionHandler;
import org.ernest.applications.web_application.handlers.LoginRequestHandler;
import org.ernest.applications.web_application.handlers.LogoutActionHandler;
import org.ernest.applications.web_application.handlers.PageNumberRequestHandler;

import com.sun.net.httpserver.HttpServer;

public class Server {

	private static final String VIEW_LOGIN_CONTEXT = "/";
	private static final String VIEW_PAGE_ONE_CONTEXT = "/one";
	private static final String VIEW_PAGE_TWO_CONTEXT = "/two";
	private static final String VIEW_PAGE_THREE_CONTEXT = "/three";
	
	private static final String ACTION_LOGIN_CONTEXT = "/login";
	private static final String ACTION_LOGOUT_CONTEXT = "/logout";
	
	private HttpServer httpServer;

	public Server(int port) throws IOException, URISyntaxException {
		
		httpServer = HttpServer.create(new InetSocketAddress(port), 0);
		httpServer.createContext(VIEW_LOGIN_CONTEXT, new LoginRequestHandler());
		httpServer.createContext(VIEW_PAGE_ONE_CONTEXT, new PageNumberRequestHandler(ApplicationRoles.PAGE_ONE.getView(), ApplicationRoles.PAGE_ONE.getCode()));
		httpServer.createContext(VIEW_PAGE_TWO_CONTEXT, new PageNumberRequestHandler(ApplicationRoles.PAGE_TWO.getView(), ApplicationRoles.PAGE_TWO.getCode()));
		httpServer.createContext(VIEW_PAGE_THREE_CONTEXT, new PageNumberRequestHandler(ApplicationRoles.PAGE_THREE.getView(), ApplicationRoles.PAGE_THREE.getCode()));
		
		httpServer.createContext(ACTION_LOGIN_CONTEXT, new LoginActionHandler(ApplicationRoles.PAGE_ONE.getView()));
		httpServer.createContext(ACTION_LOGOUT_CONTEXT, new LogoutActionHandler());
		httpServer.setExecutor(null);
	}

	public void start() {
		this.httpServer.start();
	}
	
	public void stop() {
		this.httpServer.stop(0);
	}

	public static void main(String[] args) throws Exception {
		Properties prop = new Properties();
		prop.load(Server.class.getClassLoader().getResourceAsStream("application.properties"));
		int port = Integer.valueOf(prop.getProperty("server.port"));
		
		new Server(port).start();
		System.out.println("Server is started and listening on port: " + port);
	}
}
