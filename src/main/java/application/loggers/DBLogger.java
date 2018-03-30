package application.loggers;

import application.Event;
import org.springframework.jdbc.core.JdbcTemplate;


public class DBLogger implements EventLogger {

    private JdbcTemplate jdbcTemplate;

    public DBLogger(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void init() {
    }

    public void logEvent(final Event event) {
        jdbcTemplate.update("insert into events(id, event) values(?,?)",
                event.getId(),
                event.toString());
    }
}