import java.util.Collection;

public class CombinedEventLogger implements EventLogger {
    private Collection<EventLogger> loggers;

    public CombinedEventLogger(final Collection logger) {
        this.loggers = logger;
    }

    public void logEvent(final Event event) {
        for (EventLogger logger: loggers) {
            
        }
    }
}