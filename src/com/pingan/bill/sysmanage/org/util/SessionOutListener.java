package com.pingan.bill.sysmanage.org.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.pingan.bill.sysmanage.org.service.PersonService;

public class SessionOutListener implements HttpSessionListener,ServletContextListener {
	private PersonService personService;
	private static ApplicationContext ac;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		

	}
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
		
	}


	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		String sessionId = sessionEvent.getSession().getId();
		String personId = Static.loginPersonMap.get(sessionId);
		if(Static.loginPersonMap.containsKey(sessionId)){
			personService = (PersonService) ac.getBean("personService");
			//update t_s_person set s_ip='0' where personId="#personId#"
			personService.modLoginState(personId, "0");
			Static.loginPersonMap.remove(sessionId);
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContextEvent.getServletContext());
		personService = (PersonService) ac.getBean("personService");
		// update t_s_person set s_ip='0'  0--未登录 , 1--已登录
		personService.updLoginState();
	}

}
