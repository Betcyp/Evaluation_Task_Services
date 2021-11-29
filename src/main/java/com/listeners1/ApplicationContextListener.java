package com.listeners1;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.databases1.DbConnect;


public class ApplicationContextListener implements ServletContextListener {
	static Logger log = Logger.getLogger(ApplicationContextListener.class);
	
	public void contextInitialized(ServletContextEvent event) {
		
		ServletContext context = event.getServletContext();
		String log4jFile = context.getInitParameter("log4jFilelocation");
		String log4jFilePath = context.getRealPath("") + File.separator + log4jFile;
		PropertyConfigurator.configure(log4jFilePath);
		
		try {
			DbConnect.initDatasource();
			log.debug("Dbconnect");
		
		} 
		catch (Exception e) {
			log.error(e);
		} 
	}
	public void contextDestroyed(ServletContextEvent event) {
		log.info("context object destroyed");
	}
}
