package base;
import java.util.ArrayList;

public class Partition extends Area {

    private ArrayList<Area> areas = new ArrayList<Area>();

    /**
     * Create a new partition with an id.
     *
     * @param id The id of the partition
     */
    public Partition(String id) {
        super(id);
    }

    /**
     * Create a new partition with an id and the parent partitions.
     *
     * @param id The id of the partition
     * @param parents The list of parent partitions
     */
    public Partition(String id, Partition... parents) {
        super(id, parents);
    }

    /**
     * Add a partition to the partition.
     *
     * @param partition The partition to add
     * @throws IllegalArgumentException If the partition is already in the partition or if the partition is the same as this partition
     */
    public void addPartition(Partition partition) {
        // Check if the partition is already added
        if (this.getPartitions().contains(partition))
            throw new IllegalArgumentException("Partition already exists in partition");

        // Check if the partition is the same as this partition
        if (partition == this)
            throw new IllegalArgumentException("Partition cannot contain itself");

        // Add the partition to the partition
        areas.add(partition);

        // Add the partition to the partition if not already added
        if (!partition.parents.contains(this))
            partition.parents.add(this);
    }

    /**
     * Add a space to the partition.
     *
     * @param space The space to add
     * @throws IllegalArgumentException If the space is already in the partition
     */
    public void addSpace(Space space) {
        // Check if the space is already added
        if (this.getSpaces().contains(space))
            throw new IllegalArgumentException("Space already exists in partition");

        // Add the space to the partition
        areas.add(space);

        // Add the partition to the space if not already added
        if (!space.parents.contains(this))
            space.parents.add(this);
    }

    /**
     * Add a child area to the partition.
     *
     * @param area The area to add
     * @throws IllegalArgumentException If the area is not a Partition or Space
     */
    public void addChild(Area area) {
        // Check if the area is a partition and add it
        if (area instanceof Partition) {
            addPartition((Partition) area);
            return;
        }

        // Check if the area is a space and add it
        if (area instanceof Space) {
            addSpace((Space) area);
            return;
        }

        // Throw error if not a partition or space
        throw new IllegalArgumentException("Area must be a Partition or Space");
    }

    /**
     * Get the partitions in the partition.
     *
     * @return The partitions in the partition
     */
    public ArrayList<Partition> getPartitions() {
        // Init partitions
        ArrayList<Partition> partitions = new ArrayList<Partition>();

        // Loop through all the areas
        for (Area area : areas)
            if (area instanceof Partition)
                partitions.add((Partition) area);

        // Return the list of partitions
        return partitions;
    }

    /**
     * Get the spaces in the partition.
     *
     * @return The spaces in the partition
     */
    public ArrayList<Space> getSpaces() {
        // Init spaces
        ArrayList<Space> spaces = new ArrayList<Space>();

        // Loop through all the areas
        for (Area area : areas)
            if (area instanceof Space)
                spaces.add((Space) area);

        // Loop through all partitions to get the spaces
        for (Partition partition : this.getPartitions()) {

            // Get the spaces in the partition
            ArrayList<Space> partitionSpaces = partition.getSpaces();

            // Add the spaces to the list if not already added
            for (Space space : partitionSpaces)
                if (!spaces.contains(space))
                    spaces.add(space);
        }

        // Return the list of spaces
        return spaces;
    }

    /**
     * Get the doors in the partition.
     *
     * @return The doors in the partition
     */
    public ArrayList<Door> getDoors() {
        // Get the spaces in the partition
        ArrayList<Space> spaces = getSpaces();

        // Create an empty list of doors
        ArrayList<Door> doors = new ArrayList<Door>();

        // Loop through all spaces to get the doors
        for (Space space : spaces) {
            ArrayList<Door> spaceDoors = space.getDoors();

            // Add the doors to the list if not already added
            for (Door door : spaceDoors)
                if (!doors.contains(door))
                    doors.add(door);
        }

        // Return the list of doors
        return doors;
    }

}