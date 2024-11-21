package base;

import base.doorstates.UnlockedState;
import base.requests.RequestReader;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class that manages a door, which connects two spaces and has different states.
 */
public class Door {

  private static final Logger logger = LoggerFactory.getLogger("base.Door");

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
    logger.info("Initializing door with id: {}", id);
    this.id = id;
    this.closed = true;
    this.doorState = new UnlockedState(this);
    logger.debug("Door initialized with state: {}", doorState.getStateName());

    this.setFromSpace(from);
    this.setToSpace(to);
    logger.info("Door with id: {} connected between spaces: {} and {}", id, from, to);
  }

  /**
   * Process a request to do an action on the door.
   * It is the Door that processes the request because the door has and knows its state,
   * and if closed or open.
   *
   * @param request The request to process
   */
  public void processRequest(RequestReader request) {
    logger.debug("Processing request for door id: {}", id);
    if (request.isAuthorized()) {
      logger.info("Request authorized for action: {} on door id: {}", request.getAction(), id);
      String action = request.getAction();
      doAction(action);
    } else {
      logger.warn("Request not authorized for door id: {}", id);
    }

    String stateName = getStateName();
    request.setDoorStateName(stateName);
    logger.debug("Door state updated in request: {}", stateName);
  }

  /**
   * Do an action on the door.
   *
   * @param action The action to do
   * @throws IllegalArgumentException If the action is invalid
   */
  private void doAction(String action) {
    logger.debug("Performing action: {} on door id: {}", action, id);
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
        logger.error("Invalid action attempted: {} on door id: {}", action, id);
        throw new IllegalArgumentException("Invalid action: \"" + action + "\"");
    }
    logger.info("Action: {} completed on door id: {}", action, id);
  }

  /**
   * Check if the door is closed.
   *
   * @return True if the door is closed, false otherwise
   */
  public boolean isClosed() {
    logger.debug("Checking if door id: {} is closed: {}", id, closed);
    return closed;
  }

  /**
   * Set the state of the door.
   *
   * @param doorState The state of the door
   */
  public void setDoorState(DoorState doorState) {
    logger.debug("Setting new state for door id: {} to {}", id, doorState.getStateName());
    this.doorState = doorState;
  }

  /**
   * Set the door's closed status.
   *
   * @param closed The closed status
   */
  public void setClosed(boolean closed) {
    logger.debug("Setting closed status for door id: {} to {}", id, closed);
    this.closed = closed;
  }

  /**
   * Set the first space the door connects.
   *
   * @param from The from space
   * @throws IllegalArgumentException If the from space is already set or same as the to space
   */
  public void setFromSpace(Space from) {
    logger.debug("Setting 'from' space for door id: {}", id);
    this.from = from;
    from.addDoor(this);
    logger.info("'From' space set successfully for door id: {}", id);
  }

  /**
   * Set the second space the door connects.
   *
   * @param to The to space
   * @throws IllegalArgumentException If the to space is already set or same as the from space
   */
  public void setToSpace(Space to) {
    logger.debug("Setting 'to' space for door id: {}", id);
    this.to = to;
    to.addDoor(this);
    logger.info("'To' space set successfully for door id: {}", id);
  }

  public String getId() {
    logger.debug("Getting id for door: {}", id);
    return id;
  }

  /**
   * Get the space the door connects from.
   *
   * @return The from space
   */
  public Space getFromSpace() {
    logger.debug("Getting 'from' space for door id: {}", id);
    return this.from;
  }

  /**
   * Get the space the door connects to.
   *
   * @return The to space
   */
  public Space getToSpace() {
    logger.debug("Getting 'to' space for door id: {}", id);
    return this.to;
  }

  /**
   * Get the name of the state of the door.
   *
   * @return The name of the state of the door
   */
  public String getStateName() {
    logger.debug("Getting state name for door id: {}", id);
    return doorState.getStateName();
  }

  /**
   * Convert the door data to a string.
   *
   * @return The JSON string of the door data
   */
  @Override
  public String toString() {
    logger.debug("Converting door id: {} to string.", id);
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
    logger.debug("Converting door id: {} to JSON.", id);
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    return json;
  }
}
