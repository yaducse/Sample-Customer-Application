package com.infy.customer.utility;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Around("execution(* com.infy.customer..*..*(..))")
	public Object logAroundAllMethods(ProceedingJoinPoint joinPoint) throws Throwable {
		
		String className = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = joinPoint.getSignature().getName();
		
		String methodEntryLog = className + "----" + methodName + "---" + "Entering into " 
				+ methodName + " with param " + Arrays.toString(joinPoint.getArgs());
		
		logger.info(methodEntryLog);
		
		long startTime = System.currentTimeMillis();
		
		Object result = joinPoint.proceed();
		
		long endTime = System.currentTimeMillis();
		
		String methodExitLog = className + "----" + methodName + "---" + "Exiting " + methodName 
				+ " with result " + result + "--- method execution completed in " 
				+ (endTime - startTime) + " ms.";
		
		logger.info(methodExitLog);
		
		return result;
	}

}
