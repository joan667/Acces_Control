package base;

public class Clock implements TimedEvent {
    private long endTime;
    private Observerable observerable;

    /**
     * Create a new clock.
     *
     * @param millis The delay in milliseconds
     * @param observerable The observerable to notify when the clock expires
     */
    public Clock(int millis, Observerable observerable) {
        // Set the end time and the observerable
        this.endTime = System.currentTimeMillis() + millis;
        this.observerable = observerable;

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
     * Notify the observerable that the clock has expired.
     */
    @Override
    public void notifyExpired() {
        observerable.update();
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
