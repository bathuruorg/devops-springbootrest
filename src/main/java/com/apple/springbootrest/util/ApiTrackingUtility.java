package com.apple.springbootrest.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ApiTrackingUtility {

	private Logger logger = LogManager.getLogger(this.getClass());

    @Before("execution(* com.apple.springbootrest.controller.EmployeeController.*.*(..))")
    public void before(JoinPoint joinPoint) {
        //Advice
        logger.info(" Check for user access ");
        logger.info(" Allowed execution for {}", joinPoint);
    }

}
