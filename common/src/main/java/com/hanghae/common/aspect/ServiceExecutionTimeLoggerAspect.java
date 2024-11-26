package com.hanghae.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ServiceExecutionTimeLoggerAspect {

    @Around("@annotation(com.hanghae.common.annotation.ServiceLogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis(); // 실행 시간 기록
        Object result = joinPoint.proceed(); // 실제 메서드 실행
        long elapsedTime = System.currentTimeMillis() - startTime;

        if (elapsedTime > 100) { // 100ms 초과 시 로그
            log.warn("[Service] Method {} took {} ms", joinPoint.getSignature(), elapsedTime);
        }

        return result; // 결과 반환
    }
}
