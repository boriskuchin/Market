package ru.bvkuchin.market.Market.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogDebugAspect {

    @Before("execution (* ru.bvkuchin.market.Market.services.*.*(..))")
    public void logAllFunctionnsCalls(JoinPoint jp) {
        log.debug("Method "+""+jp.getTarget().getClass().getSimpleName()+ " -> " + jp.getSignature().getName() + " стартовал");
    }
}
