package com.example.Student.Info.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.Student.Info.customAnnotation.LogRequest;
import com.fasterxml.jackson.databind.ObjectMapper;



@Aspect
@Component
public class LoggingRequestAspect {

	private static final Logger log = LoggerFactory.getLogger(LoggingRequestAspect.class);

	@Around("@annotation(com.example.Student.Info.customAnnotation.LogRequest)")
	public Object checkRequest(ProceedingJoinPoint pj) throws Throwable {
			LogRequest logging = ((MethodSignature) pj.getSignature()).getMethod().getAnnotation(LogRequest.class);     // to retrieve the Custom annotation Object
			System.out.println("Log Enabled: "+String.valueOf((logging.enabled())).toUpperCase());  
			if(logging.enabled()) {
			ObjectMapper om = new ObjectMapper();
    		log.info("In Method " + pj.getSignature().getName() + " with request as  " + om.writeValueAsString(pj.getArgs()));
			}
    		return pj.proceed();        
       }       
}
