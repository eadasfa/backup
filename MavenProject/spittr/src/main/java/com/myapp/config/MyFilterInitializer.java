package com.myapp.config;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;

public class MyFilterInitializer implements WebApplicationInitializer{

	public void onStartup(ServletContext servletContext) throws ServletException {
		
		//×¢²áFilter
		Dynamic filter = servletContext.addFilter("myFilter", MyFilter.class);
		//Ìí¼ÓFilterµÄÓ³ÉäÂ·¾¶
		filter.addMappingForUrlPatterns(null, false, "/custom/*");
		
	
	}

}
