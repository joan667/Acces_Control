package base;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class that contains the areas and doors in a building.
 */
public final class DirectoryAreas  {
  private static Area rootArea;
  private static final ArrayList<Area> areas = new ArrayList<>();
  private static ArrayList<Door> doors = new ArrayList<>();

  /**
   * Initialize manually the areas and doors.
   */
  public static void makeAreas() {
    // Initialize the areas and doors
    Partition building    = new Partition("building");
    Partition basement    = new Partition("basement",     building);
    Partition groundFloor = new Partition("ground_floor", building);
    Partition floor1      = new Partition("floor1",      building);
    ArrayList<Partition> partitions = new ArrayList<>(Arrays.asList(
            building, basement, groundFloor, floor1
    ));

    // Initialize the spaces
    Space parking  = new Space("parking",  basement);
    Space exterior = new Space("exterior", building);
    Space stairs   = new Space("stairs",   building);
    Space hall     = new Space("hall",     groundFloor);
    Space room1    = new Space("room1",   groundFloor);
    Space room2    = new Space("room2",   groundFloor);
    Space room3    = new Space("room3",   floor1);
    Space corridor = new Space("corridor", floor1);
    Space it       = new Space("IT",       floor1);
    ArrayList<Space> spaces = new ArrayList<>(Arrays.asList(
            parking, exterior, stairs, hall, room1,
            room2, room3, corridor, it
    ));

    // Initialize the areas
    areas.addAll(partitions);
    areas.addAll(spaces);

    // Initialize the doors
    Door d1 = new Door("D1", parking,  exterior);
    Door d2 = new Door("D2", parking,  stairs);
    Door d3 = new Door("D3", hall,     exterior);
    Door d4 = new Door("D4", hall,     stairs);
    Door d5 = new Door("D5", room1,    hall);
    Door d6 = new Door("D6", room2,    hall);
    Door d7 = new Door("D7", corridor, stairs);
    Door d8 = new Door("D8", room3,    corridor);
    Door d9 = new Door("D9", it,       corridor);
    doors = new ArrayList<>(Arrays.asList(
            d1, d2, d3, d4, d5, d6, d7, d8, d9
    ));

    // Set the root area
    rootArea = building;
  }

  /**
   * Find an area by its id.
   *
   * @param id The id of the area to find
   * @return The area with the given id
   * @throws IllegalArgumentException If the area is not found
   */
  public static Area findAreaById(String id) {
    // Loop through all areas to find it
    for (Area area : areas) {
      if (area.getId().equals(id)) {
        return area;
      }
    }

    // Throw error if not found
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
    // Loop through all doors to find it
    for (Door door : doors) {
      if (door.getId().equals(id)) {
        return door;
      }
    }

    // Throw error if not found
    throw new IllegalArgumentException("Door with id \"" + id + "\" not found");
  }

  /**
   * Get the root area.
   *
   * @return The root area
   */
  public static Area getRootArea() {
    return rootArea;
  }

  /**
   * Get all the doors.
   *
   * @return The list of all doors
   */
  public static ArrayList<Door> getAllDoors() {
    // Check if the root area is set and return its doors
    if (rootArea != null) {
      return rootArea.getDoors();
    }

    // Return all the doors
    return doors;
  }

}


