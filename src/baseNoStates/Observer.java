package baseNoStates;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Observer implements Runnable {

    private static Observer instance = null;
    private final List<TimedEvent> timedEvents;
    private boolean running;

    /**
     * Create and start the observer.
     */
    private Observer() {
        timedEvents = new ArrayList<TimedEvent>();
        running = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * Return the main instance of the observer.
     *
     * @return The instance of the observer
     */
    public static synchronized Observer getInstance() {
        // Check if the instance is already created
        if (instance != null)
            return instance;

        // Create a new instance
        instance = new Observer();
        return instance;
    }

    /**
     * Add a timed event to the observer.
     *
     * @param timedEvent The timed event to add
     */
    public void addTimedEvent(TimedEvent timedEvent) {
        timedEvents.add(timedEvent);
    }

    /**
     * Remove a timed event from the observer.
     *
     * @param timedEvent The timed event to remove
     */
    public void removeTimedEvent(TimedEvent timedEvent) {
        timedEvents.remove(timedEvent);
    }

    /**
     * Run the observer.
     */
    @Override
    public void run() {
        // Loop while the observer is running
        while (running) {

            // Get the current time
            long currentTime = System.currentTimeMillis();

            synchronized (this) {
                // Iterate over the timed events
                Iterator<TimedEvent> iterator = timedEvents.iterator();
                while (iterator.hasNext()) {
                    TimedEvent event = iterator.next();

                    // Check if the timed event is expired
                    if (event.isExpired(currentTime)) {

                        // Notify and remove the timed event
                        event.notifyExpired();
                        iterator.remove();
                    }
                }
            }

            // Sleep for a while
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Observer interrupted");
            }
        }
    }

    /**
     * Stop the observer.
     */
    public void stop() {
        running = false;
    }

}
