package base.doorStates;

import base.Door;
import base.DoorState;
import base.States;

public final class UnlockedState extends DoorState {
    /**
     * Create a new unlocked state.
     *
     * @param door The door that the state belongs to
     */
    public UnlockedState(Door door) {
        super(door);
        name = States.UNLOCKED;
    }

    /**
     * The actions that will be done in the state when the door is unlocked.
     */
    public void unlock() {
        System.out.println("The door is already unlocked");
    }

}
