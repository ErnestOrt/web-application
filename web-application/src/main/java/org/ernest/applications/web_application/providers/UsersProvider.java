package org.ernest.applications.web_application.providers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.ernest.applications.web_application.entities.UserInformation;
import org.ernest.applications.web_application.entities.enums.ApplicationRoles;

public class UsersProvider {
	
	private static UsersProvider instance = null;
	
	Map<String, UserInformation> users = new HashMap<String, UserInformation>();
	
	private UsersProvider(){
		UserInformation userInformation = new UserInformation();
		userInformation.setName("user1");
		userInformation.setPassword("1");
		userInformation.setRoles(new HashSet<String>(Arrays.asList(ApplicationRoles.PAGE_ONE.getCode())));
		users.put(userInformation.getName(), userInformation);
		
		userInformation = new UserInformation();
		userInformation.setName("user12");
		userInformation.setPassword("12");
		userInformation.setRoles(new HashSet<String>(Arrays.asList(ApplicationRoles.PAGE_ONE.getCode(), ApplicationRoles.PAGE_TWO.getCode())));
		users.put(userInformation.getName(), userInformation);
		
		userInformation = new UserInformation();
		userInformation.setName("user13");
		userInformation.setPassword("13");
		userInformation.setRoles(new HashSet<String>(Arrays.asList(ApplicationRoles.PAGE_ONE.getCode(), ApplicationRoles.PAGE_THREE.getCode())));
		users.put(userInformation.getName(), userInformation);
		
		userInformation = new UserInformation();
		userInformation.setName("userTEST");
		userInformation.setPassword("332r23ewqrwqref23134refdsfdf");
		userInformation.setRoles(new HashSet<String>(Arrays.asList(ApplicationRoles.PAGE_ONE.getCode(), ApplicationRoles.PAGE_TWO.getCode())));
		users.put(userInformation.getName(), userInformation);
		
		userInformation = new UserInformation();
		userInformation.setName("superuser");
		userInformation.setPassword("nower823b923rn0afha9edfh2fno2");
		userInformation.setRoles(new HashSet<String>(Arrays.asList(ApplicationRoles.PAGE_ONE.getCode(), ApplicationRoles.PAGE_TWO.getCode(), ApplicationRoles.PAGE_THREE.getCode())));
		users.put(userInformation.getName(), userInformation);
	}

	public static synchronized UsersProvider getInstance() {
		if (instance == null) {
			instance = new UsersProvider();
		}
		return instance;
	}
	
	public boolean validateUser(String username, String pass){
		if(users.containsKey(username) && users.get(username).getPassword().equals(pass)){
			return true;
		}
		return false;
	}

	public UserInformation getUserInformation(String username) {
		return users.get(username);
	}
}
