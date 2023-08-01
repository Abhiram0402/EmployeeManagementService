package com.employeeMangementServices;


import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Aspect
@Component
public class loggingAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(loggingAspect.class);
	
	
	@Before("execution(public * com.employeeMangementServices.controller.EmployeeManagementController.getAllEmployees()) ")
	public void logBefore() {
		LOGGER.info("getting all the employees");
	}
	@After("execution(public * com.employeeMangementServices.controller.EmployeeManagementController.getAllEmployees()) ")
	public void logAfter() {
		LOGGER.info("getting all the employees executed");
	}
	
}
