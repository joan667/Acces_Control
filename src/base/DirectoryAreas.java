package base;

import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class that contains the areas and doors in a building.
 */
public final class DirectoryAreas {

  private static final Logger logger = LoggerFactory.getLogger("base.DirectoryAreas");
  private static Area rootArea;
  private static final ArrayList<Area> areas = new ArrayList<>();
  private static ArrayList<Door> doors = new ArrayList<>();

  /**
   * Initialize manually the areas and doors.
   */
  public static void makeAreas() {
    logger.info("Initializing areas and doors...");

    try {
      // Initialize the areas and doors
      logger.debug("Creating partitions...");
      Partition building = new Partition("building");
      Partition basement = new Partition("basement", building);
      Partition groundFloor = new Partition("ground_floor", building);
      Partition floor1 = new Partition("floor1", building);
      ArrayList<Partition> partitions = new ArrayList<>(Arrays.asList(
          building, basement, groundFloor, floor1
      ));
      logger.debug("Partitions created: {}", partitions);

      // Initialize the spaces
      logger.debug("Creating spaces...");
      Space parking = new Space("parking", basement);
      Space exterior = new Space("exterior", building);
      Space stairs = new Space("stairs", building);
      Space hall = new Space("hall", groundFloor);
      Space room1 = new Space("room1", groundFloor);
      Space room2 = new Space("room2", groundFloor);
      Space room3 = new Space("room3", floor1);
      Space corridor = new Space("corridor", floor1);
      Space it = new Space("IT", floor1);
      ArrayList<Space> spaces = new ArrayList<>(Arrays.asList(
          parking, exterior, stairs, hall, room1,
          room2, room3, corridor, it
      ));
      logger.debug("Spaces created: {}", spaces);

      // Initialize the areas
      logger.debug("Adding partitions and spaces to areas...");
      areas.addAll(partitions);
      areas.addAll(spaces);
      logger.info("Total areas initialized: {}", areas.size());

      // Initialize the doors
      logger.debug("Creating doors...");
      Door d1 = new Door("D1", parking, exterior);
      Door d2 = new Door("D2", parking, stairs);
      Door d3 = new Door("D3", hall, exterior);
      Door d4 = new Door("D4", hall, stairs);
      Door d5 = new Door("D5", room1, hall);
      Door d6 = new Door("D6", room2, hall);
      Door d7 = new Door("D7", corridor, stairs);
      Door d8 = new Door("D8", room3, corridor);
      Door d9 = new Door("D9", it, corridor);
      doors = new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));
      logger.info("Total doors initialized: {}", doors.size());

      // Set the root area
      rootArea = building;
      logger.info("Root area set to: {}", rootArea.getId());
    } catch (Exception e) {
      logger.error("Error occurred during area initialization: ", e);
    }
  }

  /**
   * Find an area by its id.
   *
   * @param id The id of the area to find
   * @return The area with the given id
   * @throws IllegalArgumentException If the area is not found
   */
  public static Area findAreaById(String id) {
    logger.debug("Searching for area with id: {}", id);
    for (Area area : areas) {
      if (area.getId().equals(id)) {
        logger.info("Area found: {}", area);
        return area;
      }
    }
    logger.warn("Area with id \"{}\" not found", id);
    throw new IllegalArgumentException("Area with id \"" + id + "\" not found");
  }

  /**
   * Find a door by its id.
   *
   * @param id The id of the door to find
   * @return The door with the given id
   * @throws IllegalArgumentException If the door is not found
   */
  public static Door findDoorById(String id) {
    logger.debug("Searching for door with id: {}", id);
    for (Door door : doors) {
      if (door.getId().equals(id)) {
        logger.info("Door found: {}", door);
        return door;
      }
    }
    logger.warn("Door with id \"{}\" not found", id);
    throw new IllegalArgumentException("Door with id \"" + id + "\" not found");
  }

  /**
   * Get the root area.
   *
   * @return The root area
   */
  public static Area getRootArea() {
    logger.debug("Getting root area...");
    return rootArea;
  }

  /**
   * Get all the doors.
   *
   * @return The list of all doors
   */
  public static ArrayList<Door> getAllDoors() {
    logger.debug("Getting all doors...");
    if (rootArea != null) {
      logger.info("Returning doors from root area: {}", rootArea.getId());
      return rootArea.getDoors();
    }
    logger.info("Returning all doors (no root area set)");
    return doors;
  }
}
