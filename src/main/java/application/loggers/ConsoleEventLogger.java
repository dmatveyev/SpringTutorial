package application.loggers;

import application.Event;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(final Event event) {
        System.out.println(event);
    }
}