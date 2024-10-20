package baseNoStates;

import baseNoStates.requests.RequestReader;
import org.json.JSONObject;

//faltan estados de locked,unlocked , locked shortly (de passado), proped unlocked siguiendo diagrama de estados

public class Door {
  private final String id;
  private boolean closed; // physically
  private DoorState doorState; // Estat de la porta (locked, unlocked de moment)

  public void setDoorState(DoorState ds)
  {
    this.doorState = ds;
  }

  public void setClosed(boolean closed) {
    this.closed = closed;
  }
  
  public Door(String id) {
    // Initialize the door
    this.id = id;
    this.closed = true;

    // Declare the door state as unlocked first
    this.doorState = new UnlockedState(this);
  }

  public Door(String id, Space space) {
    // Initialize the door
    this.id = id;
    this.closed = true;

    // Declare the door state as unlocked first
    this.doorState = new UnlockedState(this);

    // Add the door to the space
    space.addDoor(this);
  }

  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action);
    } else {
      System.out.println("not authorized");
    }
    request.setDoorStateName(getStateName());
  }

  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        /*if (closed) {
          closed = false;
        } else {
          System.out.println("Can't open door " + id + " because it's already open");
        }*/
        doorState.open();
        break;
      case Actions.CLOSE:
        /*if (closed) {
          System.out.println("Can't close door " + id + " because it's already closed");
        } else {
          closed = true;
        }*/
        doorState.close();
        break;
      case Actions.LOCK:
        // TODO
        doorState.lock();
        break;
        // fall through
      case Actions.UNLOCK:
        // TODO
        doorState.unlock();
        break;
        // fall through
      case Actions.UNLOCK_SHORTLY:
        // TODO
        System.out.println("Action " + action + " not implemented yet");
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

  public boolean isClosed() {
    return closed;
  }

  public String getId() {
    return id;
  }

  public String getStateName() {

    return doorState.getStateName();
  }

  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + closed
        + ", state=" + getStateName()
        + "}";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    return json;
  }
}
