package baseNoStates;

public interface TimedEvent {
    boolean isExpired(long currentTime);
    void notifyExpired();
}
