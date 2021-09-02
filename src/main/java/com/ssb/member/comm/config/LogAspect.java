package com.ssb.member.comm.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LogAspect {

	@Around("execution(* com.ssb.member..*(..))")
	public Object logging(ProceedingJoinPoint pjp) throws Throwable {
		log.info("Member start : " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature().getName());
		Object result = pjp.proceed();
		log.info("Member end : " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature().getName());
		return result;
	}
}
