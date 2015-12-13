package org.ernest.applications.web_application.utils;

public class RequestParserUtils {

	private static final int PARAM_NAME_IDX = 0;
	private static final int PARAM_VALUE_IDX = 1;

	private static final String AND_DELIMITER = "&";
	private static final String EQUAL_DELIMITER = "=";
	
	public static String getParameter(String query, String parameterName){
		if (query != null) {
			String[] queryParams = query.split(AND_DELIMITER);
			if (queryParams.length > 0) {
				for (String qParam : queryParams) {
					String[] param = qParam.split(EQUAL_DELIMITER);
					if (param.length > 0) {
						for (int i = 0; i < param.length; i++) {
							if (parameterName.equalsIgnoreCase(param[PARAM_NAME_IDX])) {
								return param[PARAM_VALUE_IDX];
							}
						}
					}
				}
			}
		}
		return null;
	}
}
