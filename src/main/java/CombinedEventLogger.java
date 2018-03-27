import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CombinedEventLogger implements EventLogger {
    private List<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> logger) {
        this.loggers = logger;
    }

    public void logEvent(final Event event) {
        for (EventLogger logger: loggers) {
             logger.logEvent(event);
        }
    }
}