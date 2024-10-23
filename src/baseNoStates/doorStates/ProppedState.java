package baseNoStates.doorStates;

import baseNoStates.Door;
import baseNoStates.DoorState;
import baseNoStates.States;

public final class ProppedState extends DoorState {
    /**
     * Create a new unlocked state.
     *
     * @param door The door that the state belongs to
     */
    public ProppedState(Door door) {
        super(door);
        name = States.PROPPED;
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
            door.setDoorState(new LockedState(door));
            System.out.println("The door is now closed and has been locked");
        } else {
            System.out.println("The door is already closed");
        }
    }

    /**
     * The actions that will be done in the state when the door is locked.
     */
    public void lock() {
        System.out.println("Close the door and it will be locked automatically");
    }

    /**
     * The actions that will be done in the state when the door is unlocked.
     */
    public void unlock() {
        door.setDoorState(new UnlockedState(door));
        System.out.println("The door is now unlocked");
    }

}
