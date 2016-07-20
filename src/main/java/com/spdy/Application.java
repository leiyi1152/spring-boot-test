package com.spdy;


import org.jboss.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.spdy.servlet.SampleServletApplication;


/**
 * 应用
 * 
 * @author djk
 *
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer  {

	/**
	 * 调试日志
	 */
	private static final Logger DEBUG = Logger.getLogger(Application.class);
	
	@Bean 
    public ServletRegistrationBean uploadServletRegistrationBean() { 
		ServletRegistrationBean a = new ServletRegistrationBean(new SampleServletApplication(), "/a");
		a.setLoadOnStartup(1);
        return a; 
    } 


	/**
	 * 启动服务
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		DEBUG.debug("Application start success....");
	}


	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(8080);
		
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
}
