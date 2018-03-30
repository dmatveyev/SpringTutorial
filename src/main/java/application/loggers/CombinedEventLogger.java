package application.loggers;

import application.Event;

import java.util.List;

public class CombinedEventLogger implements EventLogger {
    private List<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> logger) {
        this.loggers = logger;
    }

    public void logEvent(final Event event) {
        for (EventLogger logger : loggers) {
            logger.logEvent(event);
        }
    }
}