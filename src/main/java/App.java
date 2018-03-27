import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {


    private final Map<EventType, EventLogger> loggers;
    private Client client;
    private EventLogger eventLogger;

    public App(final Client client, final EventLogger eventLogger, Map<EventType,EventLogger> loggers) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.loggers = loggers;

    }

    public static void main(String... args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");


        App app = (App) ctx.getBean("app");
        app.logEvent(EventType.INFO, "Some event for user 1");
        app.logEvent(EventType.ERROR, "Some event for user 2");

        ctx.close();
    }

    public void logEvent(EventType type, String msg) {
      ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        EventLogger logger = loggers.get(type);
        String message = msg.replaceAll(client.getId(), client.getFullName());
        Event event = (Event) ctx.getBean("event");
        event.setMsg(message);
        if(logger == null)
            logger = eventLogger;
        logger.logEvent(event);
    }

}