import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;



@Aspect
public class MaxCountAspect {

    private int count = 0;
    private int MAX_ALLOWED = 1;
    private EventLogger eventLogger;
    
    @Around("execution(* ConsoleEventLogger.logEvent(..)) && args(evt)")
    public void aroundLogEvent(ProceedingJoinPoint jp, Object evt) throws Throwable {
        // getting count

        if(count < MAX_ALLOWED) {
            //do write in consoleLogger
            jp.proceed(new Object[] {evt});
        } else {
            eventLogger.logEvent((Event) evt);
        }
        count++;
    }

    public void setEventLogger(final EventLogger eventLogger) {
        this.eventLogger = eventLogger;
    }
}