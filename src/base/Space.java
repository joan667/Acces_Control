package base;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * A class that represents a space in the building.
 */
public class Space extends Area {

  private final ArrayList<Door> doors = new ArrayList<>();
  private static final Logger logger = LoggerFactory.getLogger("base.Space");

  /**
   * Create a new space with an id.
   *
   * @param id The id of the space
   */
  public Space(String id) {
    super(id);
    logger.info("Space created with id: {}", id);
  }

  /**
   * Create a new space with an id and the parent partitions.
   *
   * @param id      The id of the space
   * @param parents The list of parent partitions
   */
  public Space(String id, Partition... parents) {
    super(id, parents);
    logger.info("Space created with id: {} and parents.", id);
  }

  /**
   * Add a door to the space.
   *
   * @param door The door to add
   * @throws IllegalArgumentException If the door is already in the space
   * @throws IllegalArgumentException If the door is not connected to the space
   */
  public void addDoor(Door door) {
    logger.debug("Attempting to add door with id: {} to space with id: {}", door.getId(), this.id);

    if (doors.contains(door)) {
      logger.warn("Door with id: {} already exists in space with id: {}", door.getId(), this.id);
      throw new IllegalArgumentException("Door already exists in space");
    }

    if (door.getFromSpace() != this && door.getToSpace() != this) {
      logger.error("Door with id: {} is not connected to space with id: {}", door.getId(), this.id);
      throw new IllegalArgumentException("Door not connected to space");
    }

    doors.add(door);
    logger.info("Door with id: {} added successfully to space with id: {}", door.getId(), this.id);
  }

  /**
   * Get the doors in the space.
   *
   * @return The doors in the space
   */
  public ArrayList<Door> getDoors() {
    logger.debug("Fetching doors in space with id: {}", this.id);
    logger.info("Found {} doors in space with id: {}", doors.size(), this.id);
    return doors;
  }
}
