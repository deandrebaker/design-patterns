package behavioral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Observer {
    public static void main(String[] args) {
        EventManager em = new EventManager();

        EventListener logger = new LoggingListener();
        em.subscribe("open", logger);

        EventListener emailAlerts = new EmailListener();
        em.subscribe("save", emailAlerts);

        em.notify("save", "file.txt");
        em.notify("open", "file.txt");
    }

}

class EventManager {
    HashMap<String, List<EventListener>> listeners;

    public EventManager() {
        this.listeners = new HashMap<>();
    }

    public void subscribe(String eventType, EventListener listener) {
        if (!this.listeners.containsKey(eventType)) {
            this.listeners.put(eventType, new ArrayList<>());
        }
        this.listeners.get(eventType).add(listener);
    }

    public void unsubscribe(String eventType, EventListener listener) {
        if (!this.listeners.containsKey(eventType)) {
            return;
        }
        this.listeners.get(eventType).remove(listener);
    }

    public void notify(String eventType, String data) {
        for (var listener : this.listeners.get(eventType)) {
            listener.update(data);
        }
    }
}

interface EventListener {
    void update(String filename);
}

class LoggingListener implements EventListener {
    @Override
    public void update(String filename) {
        System.out.printf("Logging file: %s%n", filename);
    }
}

class EmailListener implements EventListener {
    @Override
    public void update(String filename) {
        System.out.printf("Sending email with file: %s%n", filename);
    }
}