package com.Authorization.authorizationservice.annotation;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
public class LoggerAspect {
	
	private static Logger LOG = LoggerFactory.getLogger(LoggerAspect.class);
	
	@Before(value = "@within(com.Authorization.authorizationservice.annotation.Logging) || @annotation(com.Authorization.authorizationservice.annotation.Logging)")
    public void before(JoinPoint  jointPoint) throws Throwable {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
    	LOG.info("Request from : "+request.getServerName()+":"+request.getServerPort()+request.getRequestURI());
    	LOG.debug("Request from : "+request.getServerName()+":"+request.getServerPort()+request.getRequestURI());
    }
    
}
