package base.doorstates;

import base.Door;
import base.DoorState;
import base.States;

/**
 * State that indicates that the door is unlocked and the actions to do.
 */
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

  /**
   * The actions that will be done in the state when the door is unlocked shortly.
   */
  public void unlockShortly() {
    System.out.println("The door needs to be locked first");
  }

}
