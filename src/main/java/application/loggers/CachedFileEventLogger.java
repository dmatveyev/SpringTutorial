package application.loggers;

import application.Event;

import java.util.ArrayList;
import java.util.List;

public class CachedFileEventLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> cache;

    public CachedFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<Event>();
    }

    @Override
    public void logEvent(final Event event) {
        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() {
        for (Event e : cache) {
            super.logEvent(e);
        }
    }

    private void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }
}