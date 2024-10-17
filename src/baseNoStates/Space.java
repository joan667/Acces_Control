package baseNoStates;
import java.util.ArrayList;

public class Space extends Area {
  private ArrayList<Door> doors = new ArrayList<Door>();

  public Space(String name) {
      super(name);
  }

  public Space(String name, Partition parent) {
    super(name, parent);
  }

  public void addDoor(Door door) {
    if (doors.contains(door))
      throw new IllegalArgumentException("Door already exists in area");
    doors.add(door);
  }

  public void removeDoor(Door door) {
    if (!doors.contains(door))
      throw new IllegalArgumentException("Door does not exist in area");
    doors.remove(door);
  }

  public ArrayList<Door> getDoors() {
    return doors;
  }

}
