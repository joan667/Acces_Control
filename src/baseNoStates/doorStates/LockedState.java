package baseNoStates.doorStates;

import baseNoStates.Door;
import baseNoStates.DoorState;
import baseNoStates.States;

public final class LockedState extends DoorState {
    private final String name = States.LOCKED;

    /**
     * Create a new locked state.
     *
     * @param door The door that the state belongs to
     */
    public LockedState(Door door) {
        super(door);
    }

    /**
     * The actions that will be done in the state when the door is opened.
     */
    public void open() {
        System.out.println("The door is locked and cannot be opened. Unlock it first");
    }

    /**
     * The actions that will be done in the state when the door is closed.
     */
    public void close() {
        if (!this.door.isClosed()) {
            this.door.setClosed(true);
            System.out.println("The door is now closed.");
        } else {
            System.out.println("The door is already closed.");
        }
    }

    /**
     * The actions that will be done in the state when the door is locked.
     */
    public void lock() {
        //no se si habria que poner algo aqui
        System.out.println("The door is already locked.");
    }

    /**
     * The actions that will be done in the state when the door is unlocked.
     */
    public void unlock() {
        door.setDoorState(new UnlockedState(door));
        System.out.println("The door is now unlocked");
    }
}