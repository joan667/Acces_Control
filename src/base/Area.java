package base;

import java.util.ArrayList;

/**
 * An area in a building. The area can specify groups of different types that contain doors.
 */
public abstract class Area {
  protected String id;
  protected ArrayList<Partition> parents = new ArrayList<>();

  /**
   * Create a new area with a name.
   *
   * @param id The name of the area
   */
  public Area(String id) {
    // Set the name of the area
    this.id = id;
  }

  /**
   * Create a new area with a name and the parent partitions.
   *
   * @param id The name of the area
   * @param parents The list of parent partitions
   */
  public Area(String id, Partition... parents) {
    // Set the name of the area
    this.id = id;
    // Add the area to the parent partitions if specified
    for (Partition parent : parents) {
      this.addParent(parent);
    }
  }

  /**
   * Set a parent partition for the area.
   *
   * @param parent The parent partition
   */
  public void addParent(Partition parent) {
    // Add the area to the parent partition
    parent.addChild(this);
  }

  /**
   * Get the name of the area.
   *
   * @return The name of the area
   */
  public String getId() {
    return id;
  }

  /**
   * Get the doors in the area.
   *
   * @return The doors in the area
   */
  public abstract ArrayList<Door> getDoors();
}
