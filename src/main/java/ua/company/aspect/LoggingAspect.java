package ua.company.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = Logger.getLogger(LoggingAspect.class);

    @Pointcut("execution(* ua.company.controller.*.*(..))")
    private void forControllerPackage(){}

    @Before("forControllerPackage()")
    public void before(JoinPoint joinPoint){
        logAdviceInfo(joinPoint);
    }

    private void logAdviceInfo(JoinPoint joinPoint) {
        String prefix = "Advice info: ";
        String methodName = joinPoint.getSignature().toShortString();
        Object[] methodArgs = joinPoint.getArgs();
        logger.info(prefix+" controller and method was called -> " + methodName);
        if (methodArgs.length==0){
            logger.info(prefix + "with no argument method.");
        } else {
            logger.info(prefix+ "With arguments:");
            for (Object arg: methodArgs){
                logger.info(arg);
            }
        }
    }
}
