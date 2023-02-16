package ru.bvkuchin.market.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class TimerAspect {

    @Pointcut("@annotation(Timer)")
    private void annotatedMethods() {    }

    @Pointcut("@within(ru.bvkuchin.market.aspects.Timer)")
    public void annotatedClass() {    }

    @Pointcut("annotatedMethods() || annotatedClass()")
    public void targetPointcut() {    }


    @Around("targetPointcut()")
    public Object calculateExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        log.info("Время работы метода {}:{}ms", proceedingJoinPoint.getSignature(), endTime-startTime);
        return proceed;
    }
}
