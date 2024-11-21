package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A class that observes timed events and notifies when they expire.
 */
public class Observer implements Runnable {

  private static final Logger logger = LoggerFactory.getLogger("base.Observer");

  private static Observer instance = null;
  private final List<TimedEvent> timedEvents;
  private boolean running;

  /**
   * Create and start the observer.
   */
  private Observer() {
    logger.info("Creating a new Observer instance.");
    timedEvents = new ArrayList<>();
    running = true;
    Thread thread = new Thread(this);
    thread.start();
    logger.info("Observer instance created and started successfully.");
  }

  /**
   * Return the main instance of the observer.
   *
   * @return The instance of the observer
   */
  public static synchronized Observer getInstance() {
    if (instance != null) {
      logger.debug("Returning existing Observer instance.");
      return instance;
    }

    logger.info("Creating a new Observer instance (singleton).");
    instance = new Observer();
    return instance;
  }

  /**
   * Add a timed event to the observer.
   *
   * @param timedEvent The timed event to add
   */
  public void addTimedEvent(TimedEvent timedEvent) {
    logger.debug("Adding a new timed event to the Observer.");
    timedEvents.add(timedEvent);
    logger.info("Timed event added successfully.");
  }

  /**
   * Remove a timed event from the observer.
   *
   * @param timedEvent The timed event to remove
   */
  public void removeTimedEvent(TimedEvent timedEvent) {
    logger.debug("Removing a timed event from the Observer.");
    timedEvents.remove(timedEvent);
    logger.info("Timed event removed successfully.");
  }

  /**
   * Run the observer.
   */
  @Override
  public void run() {
    logger.info("Observer thread started.");

    while (running) {
      long currentTime = System.currentTimeMillis();
      //logger.debug("Current time: {}", currentTime);

      synchronized (this) {
        Iterator<TimedEvent> iterator = timedEvents.iterator();
        while (iterator.hasNext()) {
          TimedEvent event = iterator.next();

          if (event.isExpired(currentTime)) {
            logger.info("Timed event expired. Notifying and removing it.");
            event.notifyExpired();
            iterator.remove();
            logger.debug("Timed event removed from the Observer.");
          }
        }
      }

      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        logger.warn("Observer thread interrupted.", e);
      }
    }

    logger.info("Observer thread stopped.");
  }

  /**
   * Stop the observer.
   */
  @SuppressWarnings("unused") // This method is not used yet, but is necessary to exit the loop
  public void stop() {
    logger.info("Stopping the Observer.");
    running = false;
  }
}
