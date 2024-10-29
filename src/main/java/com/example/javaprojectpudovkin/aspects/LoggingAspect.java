package com.example.javaprojectpudovkin.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.example.javaprojectpudovkin.service.impl.*.*(..))") // Замените на ваш пакет
    public void anyMethodInService() {}

    @Before("anyMethodInService()")
    public void logMethodEntry(JoinPoint joinPoint) {
        logger.info("Вход в метод: {} с аргументами: {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "anyMethodInService()", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        logger.debug("Выход из метода: {}. Результат: {}", joinPoint.getSignature().getName(), result);
    }

    @AfterThrowing(pointcut = "anyMethodInService()", throwing = "exception")
    public void logMethodException(JoinPoint joinPoint, Throwable exception) {
        logger.error("Исключение в методе: {}. Сообщение: {}", joinPoint.getSignature().getName(), exception.getMessage(), exception);
    }
}
