package baseNoStates;

public class LockedState extends DoorState {

  public LockedState(Door D)
  {
    super(D);
    //this.name = "Locked";
  }

  @Override
  public void open() {
    System.out.println("The door is locked and cannot be opened. Unlock it first");
  }

  @Override
  public void close() {
    if (!door.isClosed()) {
      door.setClosed(true);
      System.out.println("The door is now closed.");
    } else {
      System.out.println("The door is already closed.");
    }
  }

  @Override
  public void lock() {
    //no se si habria que poner algo aqui
    System.out.println("The door is already locked.");
  }

  @Override
  public void unlock() {
    //como esta locked podemos hacer un unlock
    // a discutir si se tendria que mirar el estado open, closed desde aqui o desde la classe door
    //DoorState New_state = new UnlockedDoor(this.door);
    //this.door.setDoorState(New_state);
    door.setDoorState(new UnlockedState(door));
    System.out.println("The door is now unlocked");
  }

  public String getStateName() {
    return States.LOCKED;
  }
}
