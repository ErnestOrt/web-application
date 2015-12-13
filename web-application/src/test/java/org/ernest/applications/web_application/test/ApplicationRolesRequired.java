package org.ernest.applications.web_application.test;

import java.util.ArrayList;

import junit.framework.Assert;

import org.ernest.applications.web_application.entities.enums.ApplicationRoles;
import org.junit.Test;

public class ApplicationRolesRequired {

//  REQUIRMENT
//	The necessary roles to access each page are the next ones:
//	Page 1: In order to be able to access this page the logged user needs to have the role PAGE_1
//	Page 2: In order to be able to access this page the logged user needs to have the role PAGE_2
//	Page 3: In order to be able to access this page the logged user needs to have the role PAGE_3
	
	
	@Test
	public void GivenRequiredRolesWhenComparingWithExistingOnesThenMatchBothSets(){
		ArrayList<String> requiredRoles = new ArrayList<String>();
		requiredRoles.add("PAGE_1");
		requiredRoles.add("PAGE_2");
		requiredRoles.add("PAGE_3");
		
		Assert.assertEquals(requiredRoles.size(), ApplicationRoles.values().length);
		
		Assert.assertEquals(requiredRoles.get(0), ApplicationRoles.PAGE_ONE.getCode());
		Assert.assertEquals(requiredRoles.get(1), ApplicationRoles.PAGE_TWO.getCode());
		Assert.assertEquals(requiredRoles.get(2), ApplicationRoles.PAGE_THREE.getCode());
	}
}