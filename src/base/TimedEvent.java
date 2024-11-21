package base;

/**
 * An interface for a timed event.
 */
public interface TimedEvent {

  /**
   * Check if the timed event is expired.
   *
   * @param currentTime The current time
   * @return True if the timed event is expired, false otherwise
   */
  boolean isExpired(long currentTime);

  /**
   * Notify that the timed event is expired.
   */
  void notifyExpired();
}
