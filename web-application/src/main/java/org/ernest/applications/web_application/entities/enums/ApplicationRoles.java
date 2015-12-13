package org.ernest.applications.web_application.entities.enums;

public enum ApplicationRoles {

	PAGE_ONE("PAGE_1", "one"),
	PAGE_TWO("PAGE_2", "two"),
	PAGE_THREE("PAGE_3", "three");
	
	private String code;
	private String view;
	
	ApplicationRoles(String code, String view) {
		this.code = code;
		this.view = view;
	}

	public String getCode() {
		return code;
	}
	
	public String getView(){
		return view;
	}
	
	public static String getViewFromCode(String code){
		for (ApplicationRoles role : ApplicationRoles.values()) {
			if(role.getCode().equals(code)){
				return role.getView();
			}
		}
		return null;
	}
}
