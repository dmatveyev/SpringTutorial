import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {

    private static ClassPathXmlApplicationContext ctx;
    private final Map<EventType, EventLogger> loggers;
    private Client client;
    private EventLogger eventLogger;

    public App(final Client client, final EventLogger eventLogger, Map<EventType,EventLogger> loggers) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.loggers = loggers;
    }

    public static void main(String... args) {
        ctx = new ClassPathXmlApplicationContext ("spring.xml");
        App app = (App) ctx.getBean("app");

        app.logEvent(EventType.INFO, "Hello");

        ctx.close();
    }

    public void logEvent(EventType type, String msg) {
        EventLogger logger = loggers.get(type);
        String message = msg.replaceAll(client.getId(), client.getFullName());
        Event event = (Event) ctx.getBean("event");
        event.setMsg(message);
        if(logger == null)
            logger = eventLogger;
        logger.logEvent(event);
    }

}