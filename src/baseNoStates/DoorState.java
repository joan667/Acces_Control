package baseNoStates;

public abstract class DoorState {
    protected String name = "unknown";
    protected final Door door;

    /**
     * Create a new door state.
     *
     * @param door The door that the state belongs to
     */
    public DoorState(Door door) {
        this.door = door;
    }

    /**
     * Get the name of the state.
     *
     * @return The name of the state
     */
    public String getStateName() {
        return name;
    }

    /**
     * The actions that will be done in the state when the door is opened.
     */
    public abstract void open();

    /**
     * The actions that will be done in the state when the door is closed.
     */
    public abstract void close();

    /**
     * The actions that will be done in the state when the door is locked.
     */
    public abstract void lock();

    /**
     * The actions that will be done in the state when the door is unlocked.
     */
    public abstract void unlock();

}
