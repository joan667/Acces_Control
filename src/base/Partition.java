package base;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A class that represents a partition in the building.
 */
public class Partition extends Area {

  private static final Logger logger = LoggerFactory.getLogger("base.Partition");
  private final ArrayList<Area> areas = new ArrayList<>();

  /**
   * Create a new partition with an id.
   *
   * @param id The id of the partition
   */
  public Partition(String id) {
    super(id);
    logger.info("Partition created with id: {}", id);
  }

  /**
   * Create a new partition with an id and the parent partitions.
   *
   * @param id The id of the partition
   * @param parents The list of parent partitions
   */
  public Partition(String id, Partition... parents) {
    super(id, parents);
    logger.info("Partition created with id: {} and parents.", id);
  }

  /**
   * Add a partition to the partition.
   *
   * @param partition The partition to add
   * @throws IllegalArgumentException If the partition is already in the partition
   * @throws IllegalArgumentException If the partition is the same as this partition
   */
  public void addPartition(Partition partition) {
    logger.debug("Attempting to add partition with id: {} to partition with id: {}",
        partition.getId(), this.id);
    if (this.getPartitions().contains(partition)) {
      logger.warn("Partition with id: {} already exists in partition with id: {}",
          partition.getId(), this.id);
      throw new IllegalArgumentException("Partition already exists in partition");
    }
    if (partition == this) {
      logger.error("Partition cannot contain itself: {}", this.id);
      throw new IllegalArgumentException("Partition cannot contain itself");
    }
    areas.add(partition);
    if (!partition.parents.contains(this)) {
      partition.parents.add(this);
    }
    logger.info("Partition with id: {} added successfully to partition with id: {}",
        partition.getId(), this.id);
  }

  /**
   * Add a space to the partition.
   *
   * @param space The space to add
   * @throws IllegalArgumentException If the space is already in the partition
   */
  public void addSpace(Space space) {
    logger.debug("Attempting to add space with id: {} to partition with id: {}",
        space.getId(), this.id);
    if (this.getSpaces().contains(space)) {
      logger.warn("Space with id: {} already exists in partition with id: {}",
          space.getId(), this.id);
      throw new IllegalArgumentException("Space already exists in partition");
    }
    areas.add(space);
    if (!space.parents.contains(this)) {
      space.parents.add(this);
    }
    logger.info("Space with id: {} added successfully to partition with id: {}",
        space.getId(), this.id);
  }

  /**
   * Add a child area to the partition.
   *
   * @param area The area to add
   * @throws IllegalArgumentException If the area is not a Partition or Space
   */
  public void addChild(Area area) {
    logger.debug("Attempting to add child area to partition with id: {}", this.id);
    if (area instanceof Partition) {
      logger.debug("Child area is a partition with id: {}", area.getId());
      addPartition((Partition) area);
      return;
    }
    if (area instanceof Space) {
      logger.debug("Child area is a space with id: {}", area.getId());
      addSpace((Space) area);
      return;
    }
    logger.error("Area must be a Partition or Space to add to partition with id: {}", this.id);
    throw new IllegalArgumentException("Area must be a Partition or Space");
  }

  /**
   * Get the partitions in the partition.
   *
   * @return The partitions in the partition
   */
  public ArrayList<Partition> getPartitions() {
    logger.debug("Fetching partitions in partition with id: {}", this.id);
    ArrayList<Partition> partitions = new ArrayList<>();
    for (Area area : areas) {
      if (area instanceof Partition) {
        partitions.add((Partition) area);
      }
    }
    logger.info("Found {} partitions in partition with id: {}", partitions.size(), this.id);
    return partitions;
  }

  /**
   * Get the spaces in the partition.
   *
   * @return The spaces in the partition
   */
  public ArrayList<Space> getSpaces() {
    logger.debug("Fetching spaces in partition with id: {}", this.id);
    ArrayList<Space> spaces = new ArrayList<>();
    for (Area area : areas) {
      if (area instanceof Space) {
        spaces.add((Space) area);
      }
    }
    for (Partition partition : this.getPartitions()) {
      ArrayList<Space> partitionSpaces = partition.getSpaces();
      for (Space space : partitionSpaces) {
        if (!spaces.contains(space)) {
          spaces.add(space);
        }
      }
    }
    logger.info("Found {} spaces in partition with id: {}", spaces.size(), this.id);
    return spaces;
  }

  /**
   * Get the doors in the partition.
   *
   * @return The doors in the partition
   */
  public ArrayList<Door> getDoors() {
    logger.debug("Fetching doors in partition with id: {}", this.id);
    ArrayList<Space> spaces = getSpaces();
    ArrayList<Door> doors = new ArrayList<>();
    for (Space space : spaces) {
      ArrayList<Door> spaceDoors = space.getDoors();
      for (Door door : spaceDoors) {
        if (!doors.contains(door)) {
          doors.add(door);
        }
      }
    }
    logger.info("Found {} doors in partition with id: {}", doors.size(), this.id);
    return doors;
  }
}
