package base.doorstates;

import base.Door;
import base.DoorState;
import base.States;

/**
 * State that indicates that the door is locked and the actions to do.
 */
public final class LockedState extends DoorState {

  /**
   * Create a new locked state.
   *
   * @param door The door that the state belongs to
   */
  public LockedState(Door door) {
    super(door);
    name = States.LOCKED;
  }

  /**
   * The actions that will be done in the state when the door is opened.
   */
  public void open() {
    System.out.println("The door is locked and cannot be opened. Unlock it first");
  }

  /**
   * The actions that will be done in the state when the door is locked.
   */
  public void lock() {
    System.out.println("The door is already locked.");
  }
}
