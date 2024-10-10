package baseNoStates;

public abstract class DoorState {
protected Door door;
//protected String name;

abstract public void open();
abstract public void close();
abstract public void lock();
abstract public void unlock();
//abstract public void setDoor(Door D);
abstract public String getStateName();


  public DoorState(Door D)
{
  this.door = D;
}

}
