package com.apple.springbootrest.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Aspect
@Configuration
public class ApiTrackingUtility {

	private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private JavaMailSender sender;
    
    @Before("execution(* com.apple.springbootrest.controller.EmployeeController.*(..)))")
    public void beforeAdvise(JoinPoint joinPoint) {
        //Advice
        logger.info(" Before Advise ");
        logger.info(" Allowed execution for {}", joinPoint);
        
    	logger.info("Send Email");
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo("srinivas.bathuru@gmail.com");
            helper.setSubject("Mail From Spring Boot");
            helper.setText("Some one trying to access your Springboot API Application");
            sender.send(message);
        } catch (MessagingException e) {
        	logger.info("Exception !!!!");

        }
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
