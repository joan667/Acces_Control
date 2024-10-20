package baseNoStates;
import java.util.ArrayList;

public class Space extends Area {
    private final ArrayList<Door> doors = new ArrayList<Door>();

    /**
     * Create a new space with a name.
     *
     * @param name The name of the space
     */
    public Space(String name) {
        super(name);
    }

    /**
     * Create a new space with a name and the parent partitions.
     *
     * @param name The name of the space
     * @param parents The list of parent partitions
     */
    public Space(String name, Partition... parents) {
        super(name, parents);
    }

    /**
     * Add a door to the space.
     *
     * @param door The door to add
     * @throws IllegalArgumentException If the door is already in the space or if the door is not connected to the space
     */
    public void addDoor(Door door) {
        // Check if the door is already added
        if (doors.contains(door))
            throw new IllegalArgumentException("Door already exists in space");

        // Check if the door is connected to the space
        if (door.getFromSpace() != this && door.getToSpace() != this)
            throw new IllegalArgumentException("Door not connected to space");

        // Add the door to the space
        doors.add(door);
    }

    /**
     * Get the doors in the space.
     *
     * @return The doors in the space
     */
    public ArrayList<Door> getDoors() {
        return doors;
    }

}
