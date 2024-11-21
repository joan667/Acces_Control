package base;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * An area in a building. The area can specify groups of different types that contain doors.
 */
public abstract class Area {
  private static final Logger logger = LoggerFactory.getLogger("base.Area");
  protected String id;
  protected ArrayList<Partition> parents = new ArrayList<>();

  /**
   * Create a new area with a name.
   *
   * @param id The name of the area
   */
  public Area(String id) {
    logger.info("Creating area with id: {}", id);
    this.id = id;
    logger.info("Area created successfully with id: {}", id);
  }

  /**
   * Create a new area with a name and the parent partitions.
   *
   * @param id The name of the area
   * @param parents The list of parent partitions
   */
  public Area(String id, Partition... parents) {
    logger.info("Creating area with id: {}", id);
    this.id = id;
    logger.info("Area created successfully with id: {}", id);
    logger.info("Adding parents to the area with id: {}", id);
    for (Partition parent : parents) {
      logger.info("Adding parent partition to area with id: {}", id);
      this.addParent(parent);
    }
    logger.info("All parents added successfully to area with id: {}", id);
  }

  /**
   * Set a parent partition for the area.
   *
   * @param parent The parent partition
   */
  public void addParent(Partition parent) {
    logger.info("Adding parent partition to area with id: {}", id);
    parent.addChild(this);
    logger.info("Parent partition added successfully to area with id: {}", id);
  }

  /**
   * Get the name of the area.
   *
   * @return The name of the area
   */
  public String getId() {
    logger.info("Accessing id of the area: {}", id);
    return id;
  }

  /**
   * Get the doors in the area.
   *
   * @return The doors in the area
   */
  public abstract ArrayList<Door> getDoors();
}
