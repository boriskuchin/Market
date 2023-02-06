package ru.bvkuchin.market.Market.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Aspect
@Component
public class LogDebugAspect {

    @Before("execution (* ru.bvkuchin.market.Market.services.*.*(..))")
    public void logAllFunctionnsCalls(JoinPoint jp) {
        String className = jp.getTarget().getClass().getSimpleName();
        String methodName = jp.getSignature().getName();
        List<String> args = Arrays.stream(jp.getArgs()).map(a -> a.getClass().getSimpleName() + " " + a.toString()).collect(Collectors.toList());

        log.debug("{}.{} c аргументами: {}", className, methodName,args);
    }
}
