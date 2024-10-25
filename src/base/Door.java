// TODO: Faltan estados de locked, unlocked, locked shortly y propped unlocked.

package base;

import base.doorStates.UnlockedState;
import base.requests.RequestReader;
import org.json.JSONObject;

public class Door {
    private final String id;
    private boolean closed;
    private DoorState doorState;
    private Space from;
    private Space to;

    /**
     * Create a new door with a name and the two spaces it connects.
     *
     * @param id   The name of the door
     * @param from The first space the door connects
     * @param to   The second space the door connects
     */
    public Door(String id, Space from, Space to) {
        // Initialize the door
        this.id = id;
        this.closed = true;

        // Declare the door state as unlocked first
        this.doorState = new UnlockedState(this);

        // Add the door to the spaces
        this.setFromSpace(from);
        this.setToSpace(to);
    }

    /**
     * Process a request to do an action on the door.
     * It is the Door that process the request because the door has and knows its state, and if closed or open.
     *
     * @param request The request to process
     */
    public void processRequest(RequestReader request) {
        // TODO: The implementation of the processRequest method is hardcoded, it should be refactored
        // Check if the request is authorized and process it
        if (request.isAuthorized()) {
            String action = request.getAction();
            doAction(action);
        } else {
            System.out.println("not authorized");
        }

        // Set the door state name in the request
        String stateName = getStateName();
        request.setDoorStateName(stateName);
    }

    /**
     * Do an action on the door.
     *
     * @param action The action to do
     * @throws IllegalArgumentException If the action is invalid
     */
    private void doAction(String action) {
        switch (action) {
            case Actions.OPEN:
                doorState.open();
                break;
            case Actions.CLOSE:
                doorState.close();
                break;
            case Actions.LOCK:
                doorState.lock();
                break;
            case Actions.UNLOCK:
                doorState.unlock();
                break;
            case Actions.UNLOCK_SHORTLY:
                doorState.unlockShortly();
                break;
            default:
                throw new IllegalArgumentException("Invalid action: \"" + action + "\"");
        }
    }

    /**
     * Check if the door is closed.
     *
     * @return True if the door is closed, false otherwise
     */
    public boolean isClosed() {
        return closed;
    }

    /**
     * Set the state of the door.
     *
     * @param doorState The state of the door
     */
    public void setDoorState(DoorState doorState) {
        this.doorState = doorState;
    }

    /**
     * Get the state of the door.
     *
     * @return The state of the door
     */
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    /**
     * Set the first space the door connects.
     *
     * @param from The from space
     * @throws IllegalArgumentException If the from space is already set or is the same as the to space
     */
    public void setFromSpace(Space from) {
        // Check if the from space is already set
        if (this.from != null)
            throw new IllegalArgumentException("From space already set");

        // Check if the from space is the same as the to space
        if (this.to == from)
            throw new IllegalArgumentException("From space cannot be the same as to space");

        // Set the first space
        this.from = from;

        // Add the door to the space
        from.addDoor(this);
    }

    /**
     * Set the second space the door connects.
     *
     * @param to The to space
     * @throws IllegalArgumentException If the to space is already set or is the same as the from space
     */
    public void setToSpace(Space to) {
        // Check if the to space is already set
        if (this.to != null)
            throw new IllegalArgumentException("To space already set");

        // Check if the to space is the same as the from space
        if (this.from == to)
            throw new IllegalArgumentException("To space cannot be the same as from space");

        // Set the second space
        this.to = to;

        // Add the door to the space
        to.addDoor(this);
    }

    public String getId() {
        return id;
    }

    /**
     * Get the space the door connects from.
     *
     * @return The from space
     */
    public Space getFromSpace() {
        return this.from;
    }

    /**
     * Get the space the door connects to.
     *
     * @return The to space
     */
    public Space getToSpace() {
        return this.to;
    }

    /**
     * Get the name of the state of the door.
     *
     * @return The name of the state of the door
     */
    public String getStateName() {
        return doorState.getStateName();
    }

    /**
     * Convert the door data to a string.
     *
     * @return The JSON string of the door data
     */
    @Override
    public String toString() {
        return "Door{"
            + ", id='" + id + '\''
            + ", closed=" + closed
            + ", state=" + getStateName()
            + "}";
    }

    /**
     * Convert the door data to a JSON object.
     *
     * @return The JSON object of the door data
     */
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("state", getStateName());
        json.put("closed", closed);
        return json;
    }
}
