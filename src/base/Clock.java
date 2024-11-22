package base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Observable;
import java.util.Observer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents the general clock.
 */

public class Clock extends Observable {
  private static Clock instance;
  private final List<Observer> observers; // Lista privada de observadores
  private boolean running;
  private LocalDateTime dateTime;

  private static final Logger logger = LoggerFactory.getLogger("base.Clock");
  private static final Logger loggeFita2 = LoggerFactory.getLogger("base.Clock_f2");

  /**
   * Private constructor to implement the Singleton pattern.
   */
  private Clock() {
    this.observers = new ArrayList<>();
    this.dateTime = LocalDateTime.now();
    this.running = true;
    loggeFita2.info("Clock instance created with initial dateTime: {}", dateTime);
    startTimer();
  }

  /**
   * Get the single instance of ClockTimer.
   *
   * @return The instance of ClockTimer
   */
  public static synchronized Clock getInstance() {
    if (instance == null) {
      loggeFita2.info("Creating new Clock instance.");
      instance = new Clock();
    } else {
      loggeFita2.info("Returning existing Clock instance.");
    }
    return instance;
  }

  /**
   * Add an observer to the list.
   *
   * @param observer The observer to add
   */
  public synchronized void addObserver(Observer observer) {
    observers.add(observer);
    logger.info("Observer added. Total observers: {}", observers.size());
  }

  /**
   * Remove an observer from the list.
   *
   * @param observer The observer to remove
   */
  ;
  public synchronized void deleteObserver(Observer observer) {
    observers.remove(observer);
    logger.info("Observer removed. Total observers: {}", observers.size());
  }

  /**
   * Notify all observers asynchronously by calling their update method.
   *
   * @param arg The argument to pass to the observers.
   */
  public synchronized void notifyObservers(Object arg) {

    for (Observer observer : observers) {
      logger.debug("Notifying observer with arg: {}", arg);
      new Thread(() -> observer.update(this, arg)).start();
    }
  }

  /**
   * Start the timer to notify observers every millisecond.
   */
  private void startTimer() {
    Thread timerThread = new Thread(() -> {
      while (running) {
        try {
          dateTime = LocalDateTime.now();
          notifyObservers(dateTime);

          // Sleep for 1 millisecond
          Thread.sleep(1);
        } catch (InterruptedException e) {
          logger.error("Timer thread interrupted: {}", e.getMessage());
          running = false;
        }
      }
    });
    timerThread.setDaemon(true); // Ensures the thread stops when the application exits
    timerThread.start();
    logger.info("Timer thread started.");
  }

  /**
   * Stop the timer.
   */
  public void stopTimer() {
    running = false;
    logger.info("Timer stopped.");
  }
}
