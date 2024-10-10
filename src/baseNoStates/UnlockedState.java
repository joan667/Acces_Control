package baseNoStates;

public class UnlockedState extends DoorState{


  public UnlockedState(Door D)
  {
    super(D);
  }

  @Override
  public void open() {
    if (door.isClosed()) {
      door.setClosed(false);
      System.out.println("The door is now open");
    } else {
      System.out.println("The door is already open");
    }
  }

  @Override
  public void close() {
    if (!door.isClosed()) {
      door.setClosed(true);
      System.out.println("The door is now closed");
    } else {
      System.out.println("The door is already closed");
    }
  }

  @Override
  public void lock() {
    //com el estado base es unlocked se puede bloquear
    // a discutir si se tendria que mirar el estado open, closed desde aqui o desde la classe door

    //DoorState New_state = new LockedDoor(this.door);
    //this.door.setDoorState(New_state);

    door.setDoorState(new LockedState(door));
    System.out.println("The door is now locked");

  }

  @Override
  public void unlock() {
    //en este caso no se si haria falta hacer algo,como no deberia canviar de estado...
    System.out.println("The door is already unlocked");
  }

  public String getStateName() {
    return States.UNLOCKED;
  }


}
