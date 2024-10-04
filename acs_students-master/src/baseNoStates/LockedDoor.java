package baseNoStates;

public class LockedDoor extends  DoorState{

  public LockedDoor(Door D)
  {
    super(D);
    this.name = "Locked";
  }

  @Override
  public void open() {
    //TODO
  }

  @Override
  public void close() {
    //TODO
  }

  @Override
  public void lock() {
    //no se si habria que poner algo aqui
  }

  @Override
  public void unlock() {
    //como esta locked podemos hacer un unlock
    // a discutir si se tendria que mirar el estado open, closed desde aqui o desde la classe door
    DoorState New_state = new UnlockedDoor(this.door);
    this.door.setDoorState(New_state);
  }
}
