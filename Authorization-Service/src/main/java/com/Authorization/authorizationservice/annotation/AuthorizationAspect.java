package com.Authorization.authorizationservice.annotation;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.Authorization.authorizationservice.exception.UnAuthorized;
import com.Authorization.authorizationservice.model.TokenUtil;

@Aspect
public class AuthorizationAspect{

	static TokenUtil tokenDetails = new TokenUtil();
	
				/* @Before will Execute before the request method execution */
	
    @Before(value = "@within(com.Authorization.authorizationservice.annotation.Authorization) || @annotation(com.Authorization.authorizationservice.annotation.Authorization)")
    public void before(JoinPoint  jointPoint) throws Throwable {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
    	String Authorization = request.getHeader("Authorization");
    	if(Authorization != null && Authorization != "") {
    		String token = Authorization.substring(7);
        	try {
    			tokenDetails.validateToken(token);
    			//jointPoint.getTarget();
    		} catch (Exception e) {
    			System.out.println(e);
    			throw new UnAuthorized(e.getMessage()) ;
    		} 
    	}else {
    		throw new UnAuthorized("Token not found");
    	}
    }
    
    
    
    		/*   @Around will execute prior to the @Before and @After */
    
//  @Around(value = "@within(com.Authorization.authorizationservice.annotation.Authorization) || @annotation(com.Authorization.authorizationservice.annotation.Authorization)")
//  public void before(ProceedingJoinPoint  jointPoint) throws Throwable {
//  	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
//  	String token = request.getHeader("Authorization");
//  	System.out.println(token);
//  	if(token != null) {
//      	try {
//  			tokenDetails.validateToken(token);
//  			jointPoint.proceed();
//  		} catch (Exception e) {
//  			System.out.println(e);
//  			throw new DetailsNotFound(e.getMessage()) ;
//  		} 
//  	}else {
//  		throw new DetailsNotFound("token not found");
//  	}
//  }
    
    
    		/*  Completion of request method execution @After will execute */
    	
//    @After(value = "@within(com.Authorization.authorizationservice.annotation.Authorization) || @annotation(com.Authorization.authorizationservice.annotation.Authorization)")
//    public void after(JoinPoint  jointPoint) throws Throwable {
//    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
//    	String Authorization = request.getHeader("Authorization");
//    	if(Authorization != null && Authorization != "") {
//    		String token = Authorization.substring(7);
//        	try {
//    			tokenDetails.validateToken(token);
//    			//jointPoint.getTarget();
//    		} catch (Exception e) {
//    			System.out.println(e);
//    			throw new UnAuthorized(e.getMessage()) ;
//    		} 
//    	}else {
//    		throw new UnAuthorized("Token not found");
//    	}
//    }
}