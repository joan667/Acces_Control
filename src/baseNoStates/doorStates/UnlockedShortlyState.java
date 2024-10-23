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
}
