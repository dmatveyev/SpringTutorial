import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private Client client;
    private EventLogger eventLogger;

    public App(final Client client, final EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String... args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext ("spring.xml");
        App app = (App) ctx.getBean("app");
        Event e1 = (Event) ctx.getBean("event");
        Event e2 = (Event) ctx.getBean("event");
        e1.setMsg("Some event for 1");
        app.logEvent(e1);
        e2.setMsg("Some event for 2");
        app.logEvent(e2);
        ctx.close();
    }

    public void logEvent(Event event) {
        String message = event.getMsg().replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }

}