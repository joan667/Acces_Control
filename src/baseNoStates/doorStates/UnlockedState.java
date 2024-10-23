package baseNoStates.doorStates;

import baseNoStates.Door;
import baseNoStates.DoorState;
import baseNoStates.States;

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
