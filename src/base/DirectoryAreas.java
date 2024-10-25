package base;

import java.util.Arrays;
import java.util.ArrayList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public final class DirectoryAreas  {
  private static Area rootArea;
  private static ArrayList<Partition> partitions = new ArrayList<Partition>();
  private static ArrayList<Space> spaces = new ArrayList<Space>();
  private static ArrayList<Door> doors = new ArrayList<Door>();

  /**
   * Import areas and doors from a JSON file.
   * The JSON file should have the following format:
   * {
   *   "partitions": [
   *     { "id": "P1" },
   *     { "id": "P2", "partitions": ["P1"] }
   *   ],
   *   "spaces": [
   *     { "id": "S1", "partitions": ["P2"] },
   *     { "id": "S2", "partitions": ["P2"] }
   *   ],
   *   "doors": [
   *     { "id": "D1", "spaces": ["S1"] },
   *     { "id": "D2", "spaces": ["S1", "S2"] }
   *   ],
   *   "root": "P1"
   * }
   *
   * @param filename The name of the file to import
   */
  public static void importAreas(String filename) {
    // Read the file content
    String content;
    try {
      content = new String(Files.readAllBytes(Paths.get(filename)));
    } catch (IOException e) {
      throw new IllegalArgumentException("File not found");
    }
    // Load the JSON from the file content
    JSONObject json;
    try {
      json = new JSONObject(content);
    } catch (Exception e) {
        throw new IllegalArgumentException("Invalid JSON");
    }
    // Check if partitions are defined
    if (json.has("partitions")) {
      JSONArray jsonPartitions = json.getJSONArray("partitions");
      // Loop through all the partitions to instance the class
      for (int i = 0; i < jsonPartitions.length(); i++) {
        JSONObject jsonPartition = jsonPartitions.getJSONObject(i);
        String id = jsonPartition.getString("id");
        Partition partition = new Partition(id);
        partitions.add(partition);
      }
      // Loop through all the partitions to add the parent partitions
      for (int i = 0; i < jsonPartitions.length(); i++) {
        JSONObject jsonPartition = jsonPartitions.getJSONObject(i);
        Partition partition = partitions.get(i);
        if (jsonPartition.has("partitions")) {
          JSONArray jsonParents = jsonPartition.getJSONArray("partitions");
          for (int j = 0; j < jsonParents.length(); j++) {
            String parentId = jsonParents.getString(j);
            Partition parent = null;
            for (Partition p : partitions)
              if (p.getId().equals(parentId)) {
                parent = p;
                break;
              }
            if (parent == null)
              throw new IllegalArgumentException("Parent partition not found");
            parent.addPartition(partition);
          }
        }
      }
    }
    // Check if spaces are defined
    if (json.has("spaces")) {
      JSONArray jsonSpaces = json.getJSONArray("spaces");
      // Loop through all the partitions to instance the class and add the partitions
      for (int i = 0; i < jsonSpaces.length(); i++) {
        JSONObject jsonSpace = jsonSpaces.getJSONObject(i);
        String id = jsonSpace.getString("id");
        Space space = new Space(id);
        spaces.add(space);
        if (jsonSpace.has("partitions")) {
          JSONArray jsonParents = jsonSpace.getJSONArray("partitions");
          for (int j = 0; j < jsonParents.length(); j++) {
            String parentId = jsonParents.getString(j);
            Partition parent = null;
            for (Partition p : partitions)
              if (p.getId().equals(parentId)) {
                parent = p;
                break;
              }
            if (parent == null)
              throw new IllegalArgumentException("Parent partition not found");
            parent.addSpace(space);
          }
        }
      }
    }
    // Check if doors are defined
    if (json.has("doors")) {
      JSONArray jsonDoors = json.getJSONArray("doors");
      // Loop through all the doors to instance the class and add the spaces
      for (int i = 0; i < jsonDoors.length(); i++) {
        JSONObject jsonDoor = jsonDoors.getJSONObject(i);
        String id = jsonDoor.getString("id");
        String fromId = jsonDoor.getString("from");
        String toId = jsonDoor.getString("to");
        Space from = null;
        Space to = null;
        for (Space s : spaces) {
          if (s.getId().equals(fromId))
            from = s;
          if (s.getId().equals(toId))
            to = s;
          if (from != null && to != null) {
            if (from == to)
              throw new IllegalArgumentException("Door cannot connect the same space");
            break;
          }
        }
        if (from == null || to == null)
          throw new IllegalArgumentException("Door spaces not found");
        Door door = new Door(id, from, to);
        doors.add(door);
      }
    }
    // Check if root area is not defined
    if (!json.has("root"))
      throw new IllegalArgumentException("Root area not found");
    // Set the root area
    String rootId = json.getString("root");
    for (Partition p : partitions)
      if (p.getId().equals(rootId)) {
        rootArea = p;
        return;
      }
    for (Space s : spaces)
      if (s.getId().equals(rootId)) {
        rootArea = s;
        return;
      }
    throw new IllegalArgumentException("Root area not found");
  }

  /**
   * Initialize manually the areas and doors.
   */
  public static void makeAreas() {
    // Initialize the areas and doors
    Partition building    = new Partition("building");
    Partition basement    = new Partition("basement",     building);
    Partition groundFloor = new Partition("ground_floor", building);
    Partition floor1      = new Partition("floor1",      building);
    partitions = new ArrayList<Partition>(Arrays.asList(building, basement, groundFloor, floor1));

    // Initialize the spaces
    Space parking  = new Space("parking",  basement);
    Space exterior = new Space("exterior", building);
    Space stairs   = new Space("stairs",   building);
    Space hall     = new Space("hall",     groundFloor);
    Space room1    = new Space("room_1",   groundFloor);
    Space room2    = new Space("room_2",   groundFloor);
    Space room3    = new Space("room_3",   floor1);
    Space corridor = new Space("corridor", floor1);
    Space it       = new Space("IT",       floor1);
    spaces = new ArrayList<Space>(Arrays.asList(parking, exterior, stairs, hall, room1, room2, room3, corridor, it));

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
    doors = new ArrayList<Door>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));

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
    // Loop through all partitions to find it
    for (Partition partition : partitions)
      if (partition.getId().equals(id))
        return partition;

    // Loop through all spaces to find it
    for (Space space : spaces)
      if (space.getId().equals(id))
        return space;

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
    for (Door door : doors)
      if (door.getId().equals(id))
        return door;

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
   * Get all the partitions.
   *
   * @return The list of all partitions
   */
  public static ArrayList<Partition> getAllPartitions() {
    return partitions;
  }

  /**
   * Get all the spaces.
   *
   * @return The list of all spaces
   */
  public static ArrayList<Space> getAllSpaces() {
    return spaces;
  }

  /**
   * Get all the doors.
   *
   * @return The list of all doors
   */
  public static ArrayList<Door> getAllDoors() {
    // Check if the root area is set and return its doors
    if (rootArea != null)
      return rootArea.getDoors();

    // Return all the doors
    return doors;
  }

}


