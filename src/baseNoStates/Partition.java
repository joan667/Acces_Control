package baseNoStates;
import java.util.ArrayList;

public class Partition extends Area {
  private ArrayList<Partition> partitions = new ArrayList<Partition>();
  private ArrayList<Space> spaces = new ArrayList<Space>();

  /**
   * Create a new partition with a name.
   *
   * @param name The name of the partition
   */
  public Partition(String name) {
    super(name);
  }

  /**
   * Create a new partition with a name and a parent partition.
   *
   * @param name The name of the partition
   * @param parent The parent partition
   */
  public Partition(String name, Partition parent) {
    super(name,parent);
  }

  public void addSpace(Space space) {
    if (spaces.contains(space))
      throw new IllegalArgumentException("Space already exists in partition");
    spaces.add(space);
    if (!space.parents.contains(this))
      space.parents.add(this);
  }

  public void removeSpace(Space space) {
    if (!spaces.contains(space))
      throw new IllegalArgumentException("Space does not exist in partition");
    spaces.remove(space);
    space.parents.remove(this);
  }

  public void addPartition(Partition partition) {
    if (partitions.contains(partition))
      throw new IllegalArgumentException("Partition already exists in partition");
    if (partition == this)
      throw new IllegalArgumentException("Partition cannot contain itself");
    partitions.add(partition);
    if (!partition.parents.contains(this))
      partition.parents.add(this);
  }

  public void removePartition(Partition partition) {
    if (!partitions.contains(partition))
      throw new IllegalArgumentException("Partition does not exist in partition");
    partitions.remove(partition);
    partition.parents.remove(this);
  }

  public void addChild(Area area) {
    if (area instanceof Partition)
      addPartition((Partition) area);
    else if (area instanceof Space)
      addSpace((Space) area);
    else
      throw new IllegalArgumentException("Area must be a Partition or Space");
  }

  public void removeChild(Area area) {
    if (area instanceof Partition)
      removePartition((Partition) area);
    else if (area instanceof Space)
      removeSpace((Space) area);
    else
      throw new IllegalArgumentException("Area must be a Partition or Space");
  }

  public ArrayList<Partition> getPartitions() {
    return partitions;
  }

  public ArrayList<Space> getSpaces() {
    ArrayList<Space> spaces = this.spaces;
    for (Partition partition : partitions) {
      ArrayList<Space> partitionSpaces = partition.getSpaces();
      for (Space space : partitionSpaces)
        if (!spaces.contains(space))
          spaces.add(space);
    }
    return spaces;
  }

  public ArrayList<Door> getDoors() {
    ArrayList<Space> spaces = getSpaces();
    ArrayList<Door> doors = new ArrayList<Door>();
    for (Space space : spaces) {
      ArrayList<Door> spaceDoors = space.getDoors();
      for (Door door : spaceDoors)
        if (!doors.contains(door))
          doors.add(door);
    }
    return doors;
  }

}