package com.apple.springbootrest.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ApiTrackingUtility {

	private Logger logger = LogManager.getLogger(this.getClass());

    @Before("execution(* com.apple.springbootrest.controller.EmployeeController.*(..)))")
    public void beforeAdvise(JoinPoint joinPoint) {
        //Advice
        logger.info(" Before Advise ");
        logger.info(" Allowed execution for {}", joinPoint);
    }

    @After("execution(* com.apple.springbootrest.controller.EmployeeController.*(..)))")
    public void afterAdvise(JoinPoint joinPoint) {
        //Advice
        logger.info(" After Advise ");
        logger.info(" Allowed execution for {}", joinPoint);
    }

//    @Around("execution(* com.apple.springbootrest.controller.EmployeeController.*(..)))")
//    public void aroundAdvise(JoinPoint joinPoint) {
//        //Advice
//        logger.info(" Around Advise ");
//        logger.info(" Allowed execution for {}", joinPoint);
//    }
    
}
