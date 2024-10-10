package baseNoStates;

public abstract class DoorState {
protected Door door;
protected String name;

public String GetName()
{
  return name;
}

abstract public void open();
abstract public void close();
abstract public void lock();
abstract public void unlock();



public DoorState(Door D)
{
  this.door = D;
}
}
