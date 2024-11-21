package base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A class that represents a clock that can be used to notify an observable when the clock expires.
 */
public class Clock implements TimedEvent {
  private static final Logger logger = LoggerFactory.getLogger("base.Clock");
  private long endTime;
  private final Observable observable;

  /**
   * Create a new clock.
   *
   * @param millis The delay in milliseconds
   * @param observable The observable to notify when the clock expires
   */
  public Clock(int millis, Observable observable) {
    logger.info("Creating a new clock with delay: {} ms", millis);
    this.endTime = System.currentTimeMillis() + millis;
    this.observable = observable;
    logger.debug("Clock end time set to: {}", endTime);

    // Add the clock to the observer
    Observer.getInstance().addTimedEvent(this);
    logger.info("Clock added to the observer.");
  }

  /**
   * Check if the clock is expired.
   *
   * @param currentTime The current time
   * @return True if the clock is expired, false otherwise
   */
  @Override
  public boolean isExpired(long currentTime) {
    boolean expired = currentTime >= endTime;
    logger.debug("Checking if clock is expired. Current time: {}, End time: {}, Expired: {}",
        currentTime, endTime, expired);
    return expired;
  }

  /**
   * Notify the observable that the clock has expired.
   */
  @Override
  public void notifyExpired() {
    logger.info("Clock expired. Notifying observable.");
    observable.update();
  }

  /**
   * Reset the clock.
   *
   * @param millis The delay in milliseconds
   */
  public void reset(int millis) {
    logger.info("Resetting clock with new delay: {} ms", millis);
    this.endTime = System.currentTimeMillis() + millis;
    logger.debug("Clock end time reset to: {}", endTime);
  }

  /**
   * Stop the clock.
   */
  public void stop() {
    logger.info("Stopping clock.");
    this.endTime = System.currentTimeMillis();
    logger.debug("Clock end time set to current time: {}", endTime);
  }

  /**
   * Cancel the clock.
   */
  public void cancel() {
    logger.info("Cancelling clock.");
    stop();
    Observer.getInstance().removeTimedEvent(this);
    logger.info("Clock removed from observer.");
  }
}
