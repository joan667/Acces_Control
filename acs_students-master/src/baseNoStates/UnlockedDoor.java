package baseNoStates;

public class UnlockedDoor extends DoorState{


  public UnlockedDoor(Door D)
  {
    super(D);
    this.name = "Unlocked";
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
    //com el estado base es unlocked se puede bloquear
    // a discutir si se tendria que mirar el estado open, closed desde aqui o desde la classe door

    DoorState New_state = new LockedDoor(this.door);
    this.door.setDoorState(New_state);

  }

  @Override
  public void unlock() {
    //en este caso no se si haria falta hacer algo,como no deberia canviar de estado...
  }




}
