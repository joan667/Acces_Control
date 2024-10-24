package baseNoStates;

import baseNoStates.doorStates.LockedState;
import baseNoStates.doorStates.UnlockedState;
import baseNoStates.doorStates.UnlockedShortlyState;

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
    public void open() {
        // Check if the door is already open
        if (!this.door.isClosed()) {
            System.out.println("The door is already open.");
            return;
        }

        // Set the door to open
        this.door.setClosed(false);
        System.out.println("The door is now open.");
    }

    /**
     * The actions that will be done in the state when the door is closed.
     */
    public void close() {
        // Check if the door is already closed
        if (this.door.isClosed()) {
            System.out.println("The door is already closed.");
            return;
        }

        // Set the door to closed
        this.door.setClosed(true);
        System.out.println("The door is now closed.");
    }

    /**
     * The actions that will be done in the state when the door is locked.
     */
    public void lock() {
        // Check if the door is not closed
        if (!this.door.isClosed()) {
            System.out.println("The door is not closed. Close it first.");
            return;
        }

        // Set the door state to locked
        this.door.setDoorState(new LockedState(this.door));
        System.out.println("The door is now locked.");
    }

    /**
     * The actions that will be done in the state when the door is unlocked.
     */
    public void unlock() {
        // Set the door state to unlocked
        this.door.setDoorState(new UnlockedState(this.door));
        System.out.println("The door is now unlocked.");
    }

    /**
     * The actions that will be done in the state when the door is unlocked shortly.
     */
    public void unlockShortly() {
        // Set the door state to unlocked shortly
        this.door.setDoorState(new UnlockedShortlyState(this.door));
        System.out.println("The door is now unlocked shortly.");
    }

}
