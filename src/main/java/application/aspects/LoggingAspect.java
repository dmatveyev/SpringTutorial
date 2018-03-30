package application.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;



@Aspect
@Component()
public class LoggingAspect {

    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods() {}


    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("BEFORE: " +
                joinPoint.getTarget().getClass().getSimpleName() + " " +
                joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut="allLogEventMethods()",
            returning="retVal")
    public void logAfter(JoinPoint joinPoint, Object retVal) {
        System.out.println("AFTER_RET: " +
                joinPoint.getTarget().getClass().getSimpleName() + " " +
                joinPoint.getSignature().getName()+
                " value: " + retVal);
    }

    @AfterThrowing(pointcut="allLogEventMethods()",
            throwing="ex")
    public void logAfterThrow(Throwable ex) {
        System.out.println("AFTER_THR: " + ex);
    }

}