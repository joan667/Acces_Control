package base.doorstates;

import base.Door;
import base.DoorState;
import base.States;

/**
 * State that indicates that the door is propped and the actions to do.
 */
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
   * The actions that will be done in the state when the door is closed.
   */
  public void close() {
    // Close the door
    door.setClosed(true);

    // Set the door to locked
    door.setDoorState(new LockedState(door));
    System.out.println("The door is now closed and has been locked");
  }

  /**
   * The actions that will be done in the state when the door is locked.
   */
  public void lock() {
    System.out.println("Close the door and it will be locked automatically");
  }

}
