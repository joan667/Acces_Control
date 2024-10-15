package baseNoStates;
import java.util.ArrayList;

public abstract class Space extends Area {
  private ArrayList<Door> doors = new ArrayList<Door>();

  public Space(String name, Partition parent) {
    super(name, parent);
  }

  public void addDoor(Door door) {
    if (!doors.contains(door))
      throw new IllegalArgumentException("Door already exists in area");
    doors.add(door);
  }

  public ArrayList<Door> getDoors() {
    return doors;
  }

}
