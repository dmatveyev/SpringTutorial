package application.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Aspect
public class StatisticAspect {
    private Map<Class<?>,Integer> counter =  new HashMap<Class<?>,Integer>();


    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods(){    }

    @AfterReturning("allLogEventMethods()")
    public void count(JoinPoint joinPoint){
        Class<?> clazz = joinPoint.getTarget().getClass();
        if(!counter.containsKey(clazz)){
            counter.put(clazz, 0);
        }
        counter.put(clazz, counter.get(clazz)+1);
    }

    public Map<Class<?>, Integer> getCounter() {
        return Collections.unmodifiableMap(counter);
    }
    
}