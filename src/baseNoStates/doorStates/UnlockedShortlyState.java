package baseNoStates.doorStates;

import baseNoStates.Door;
import baseNoStates.DoorState;
import baseNoStates.States;

public final class UnlockedShortlyState extends DoorState {
    /**
     * Create a new unlocked state.
     *
     * @param door The door that the state belongs to
     */
    public UnlockedShortlyState(Door door) {
        super(door);
        name = States.UNLOCKED_SHORTLY;
    }

    /**
     * The actions that will be done in the state when the door is opened.
     */
    public void open() {
        if (door.isClosed()) {
            door.setClosed(false);
            System.out.println("The door is now open");
        } else {
            System.out.println("The door is already open");
        }
    }

    /**
     * The actions that will be done in the state when the door is closed.
     */
    public void close() {
        if (!door.isClosed()) {
            door.setClosed(true);
            System.out.println("The door is now closed");
        } else {
            System.out.println("The door is already closed");
        }
    }

    /**
     * The actions that will be done in the state when the door is locked.
     */
    public void lock() {
        // Check if the door is not closed
        if (!door.isClosed()) {
            System.out.println("The door is not closed. Close it first");
            return;
        }

        // TODO: Cancel the timer

        // Set the door state to locked
        door.setDoorState(new LockedState(door));
        System.out.println("The door is now locked");
    }

    /**
     * The actions that will be done in the state when the door is unlocked.
     */
    public void unlock() {

        // TODO: Cancel the timer

        // Set the door state to unlocked
        door.setDoorState(new UnlockedState(door));
        System.out.println("The door is now unlocked");
    }

}
