package com.myapp.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;

public class MyServletInitializer implements WebApplicationInitializer{

	public void onStartup(ServletContext servletContext) throws ServletException {

		//ע��Servlet
		Dynamic myServlet = servletContext.addServlet("myServlet", MyServlet.class);
		//ӳ��Servlet
		myServlet.addMapping("/custom/**");
		
	}

}
