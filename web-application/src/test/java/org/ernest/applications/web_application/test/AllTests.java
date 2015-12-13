package org.ernest.applications.web_application.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	ApplicationRolesRequired.class, 
	ForbiddenView.class, 
	MandatoryViewContent.class, 
	RequestViewWithoutBeingLogged.class, 
	SessionExpired.class})
public class AllTests {
}
