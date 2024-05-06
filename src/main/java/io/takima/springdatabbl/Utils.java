package io.takima.springdatabbl;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class Utils {

    @Around("@annotation(Monitored)")
    public Object monitor(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object retVal = pjp.proceed();

        long executionTime = System.currentTimeMillis() - startTime;
        log.info("{} execution time: {} ms", pjp.getSignature().toShortString(), executionTime);

        return retVal;
    }
}

