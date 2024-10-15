package baseNoStates;

import java.util.ArrayList;

public abstract class Area {
  protected String areaId;
  abstract public void addChild(Object child);

  public Area(String id, Partition parent) {
    this.areaId = id;
    if (parent != null)
      parent.addChild(this);
  }

  abstract public ArrayList<Door> getDoors();
}
