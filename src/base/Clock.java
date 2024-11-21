package base;

/**
 * A class that represents a clock that can be used to notify an observable when the clock expires.
 */
public class Clock implements TimedEvent {
  private long endTime;
  private final Observable observable;

  /**
   * Create a new clock.
   *
   * @param millis The delay in milliseconds
   * @param observable The observable to notify when the clock expires
   */
  public Clock(int millis, Observable observable) {
    // Set the end time and the observable
    this.endTime = System.currentTimeMillis() + millis;
    this.observable = observable;

    // Add the clock to the observer
    Observer.getInstance().addTimedEvent(this);
  }

  /**
   * Check if the clock is expired.
   *
   * @param currentTime The current time
   * @return True if the clock is expired, false otherwise
   */
  @Override
  public boolean isExpired(long currentTime) {
    return currentTime >= endTime;
  }

  /**
   * Notify the observable that the clock has expired.
   */
  @Override
  public void notifyExpired() {
    observable.update();
  }

  /**
   * Reset the clock.
   *
   * @param millis The delay in milliseconds
   */
  public void reset(int millis) {
    this.endTime = System.currentTimeMillis() + millis;
  }

  /**
   * Stop the clock.
   */
  public void stop() {
    this.endTime = System.currentTimeMillis();
  }

  /**
   * Cancel the clock.
   */
  public void cancel() {
    stop();
    Observer.getInstance().removeTimedEvent(this);
  }
}
