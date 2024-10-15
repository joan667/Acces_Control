package baseNoStates;

import java.util.ArrayList;

public abstract class Area {
  protected String areaId;
  protected ArrayList<Partition> parents = new ArrayList<Partition>();

  public Area(String id) {
    this.areaId = id;
  }

  public Area(String id, Partition parent) {
    this.areaId = id;
    if (parent != null)
      parent.addChild(this);
  }

  abstract public ArrayList<Door> getDoors();

  public ArrayList<Partition> getParents() {
    return parents;
  }

  public String getId() {
    return areaId;
  }
}
