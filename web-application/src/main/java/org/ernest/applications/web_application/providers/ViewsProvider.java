package org.ernest.applications.web_application.providers;


public class ViewsProvider {
	
	private static ViewsProvider instance = null;
	
	private ViewsProvider(){}

	public static synchronized ViewsProvider getInstance() {
		if (instance == null) {
			instance = new ViewsProvider();
		}
		return instance;
	}

	public String loginView(String sessionId) {
		StringBuilder stringBuilder = new StringBuilder("");

		stringBuilder.append("<html>");
		stringBuilder.append("<body>");
		stringBuilder.append("<H1>Log In form</H1>");
		stringBuilder.append("<form action=\"./loginrequest\">");
		stringBuilder.append("<p>user: <input name=\"input-user\" type=\"text\" /></p>");
		stringBuilder.append("<p>password: <input name=\"input-password\" type=\"password\" /></p>");
		stringBuilder.append("<input name=\"session-id\" type=\"hidden\" value=\""+sessionId+"\" />");
		stringBuilder.append("<button type=\"submit\">Log In</button>");
		stringBuilder.append("</form>");
		stringBuilder.append("</body>");
		stringBuilder.append("</html>");

		return stringBuilder.toString();
	}
	
	public String pageNumberView(String pageNumber, String sessionId, String userName) {
		StringBuilder stringBuilder = new StringBuilder("");

		stringBuilder.append("<html>");
		stringBuilder.append("<body>");
		stringBuilder.append("<div class=\"container\">");
		stringBuilder.append("<H1>Page "+pageNumber+"</H1>");
		stringBuilder.append("<H3>Hello "+userName+"</H3>");
		stringBuilder.append("<ul class=\"list-inline\">");
		stringBuilder.append("<li><a href=\"./one?session-id=" + sessionId + "\">Page 1</a></li>");
	    stringBuilder.append("<li><a href=\"./two?session-id=" + sessionId + "\">Page 2</a></li>");
	    stringBuilder.append("<li><a href=\"./three?session-id=" + sessionId + "\">Page 3</a></li>");
	    stringBuilder.append("</ul>");
	    stringBuilder.append("</div>");
		stringBuilder.append("<form action=\"./logout\">");
		stringBuilder.append("<input name=\"session-id\" type=\"hidden\" value=\""+sessionId+"\" />");
		stringBuilder.append("<button type=\"submit\">Log Out</button>");
		stringBuilder.append("</form>");
		stringBuilder.append("</body>");
		stringBuilder.append("</html>");

		return stringBuilder.toString();
	}
	
	public String forbiddenView(String sessionId, String pageNumber) {
		StringBuilder stringBuilder = new StringBuilder("");

		stringBuilder.append("<html>");
		stringBuilder.append("<body>");
		stringBuilder.append("<div class=\"container\">");
		stringBuilder.append("<H1>ERROR 403: Access was denied</H1>");
		stringBuilder.append("<H3>You don't have access to Page "+pageNumber+"</H3>");
		stringBuilder.append("<ul class=\"list-inline\">");
		stringBuilder.append("<li><a href=\"./one?session-id=" + sessionId + "\">Page 1</a></li>");
	    stringBuilder.append("<li><a href=\"./two?session-id=" + sessionId + "\">Page 2</a></li>");
	    stringBuilder.append("<li><a href=\"./three?session-id=" + sessionId + "\">Page 3</a></li>");
	    stringBuilder.append("</ul>");
	    stringBuilder.append("</div>");
		stringBuilder.append("<form action=\"./logout\">");
		stringBuilder.append("<input name=\"session-id\" type=\"hidden\" value=\""+sessionId+"\" />");
		stringBuilder.append("<button type=\"submit\">Log Out</button>");
		stringBuilder.append("</form>");
		stringBuilder.append("</body>");
		stringBuilder.append("</html>");

		return stringBuilder.toString();
	}
}
