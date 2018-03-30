package application;

import application.aspects.StatisticAspect;
import application.loggers.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private final Map<EventType, EventLogger> loggers;
    private Client client;
    private EventLogger eventLogger;
    private StatisticAspect statisticAspect;


    public App(final Client client, final EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.loggers = loggers;

    }

    public static void main(String... args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");
        Client client = ctx.getBean(Client.class);
        System.out.println("application.Client says: " + client.getGreeting());
        app.logEvents(ctx);

        app.outputLoggingCounter();

        ctx.close();
    }

    public void setStatisticAspect(final StatisticAspect statisticAspect) {
        this.statisticAspect = statisticAspect;
    }

    public StatisticAspect getStatisticAspect() {
        return statisticAspect;
    }

    public void logEvents(ApplicationContext ctx) {
        Event event = ctx.getBean(Event.class);
        logEvent(EventType.INFO, event, "Some event for 1");

        event = ctx.getBean(Event.class);
        logEvent(EventType.INFO, event, "One more event for 1");

        event = ctx.getBean(Event.class);
        logEvent(EventType.INFO, event, "And one more event for 1");

        event = ctx.getBean(Event.class);
        logEvent(EventType.ERROR, event, "Some event for 2");

        event = ctx.getBean(Event.class);
        logEvent(null, event, "Some event for 3");
    }

    private void logEvent(EventType eventType, Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);

        EventLogger logger = loggers.get(eventType);
        if (logger == null) {
            logger = eventLogger;
        }

        logger.logEvent(event);
    }

    private void outputLoggingCounter() {
        if (getStatisticAspect() != null) {
            System.out.println("Loggers statistics. Number of calls: ");
            for (Map.Entry<Class<?>, Integer> entry : getStatisticAspect().getCounter().entrySet()) {
                System.out.println("    " + entry.getKey().getSimpleName() + ": " + entry.getValue());
            }
        } else {
            System.out.println("statisticAspect is null");
        }
    }


}