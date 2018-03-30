package application.loggers;

import application.Event;

public interface EventLogger {
    void logEvent(Event event);
}