package application.aspects;

import application.Event;
import application.loggers.EventLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;



@Aspect
public class MaxCountAspect {

    private int count = 0;
    private int MAX_ALLOWED = 1;
    private EventLogger eventLogger;
    
    @Around("execution(* application.loggers.ConsoleEventLogger.logEvent(..)) && args(evt)")
    public void aroundLogEvent(ProceedingJoinPoint jp, Event evt) throws Throwable {
        // getting count

        if(count < MAX_ALLOWED) {
            //do write in consoleLogger
            jp.proceed(new Object[] {evt});
            count++;
        } else {
            eventLogger.logEvent(evt);
        }

    }

    public void setEventLogger(final EventLogger eventLogger) {
        this.eventLogger = eventLogger;
    }
}