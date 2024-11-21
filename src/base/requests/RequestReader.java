package base.requests;

import base.DirectoryAreas;
import base.DirectoryUserGroups;
import base.Door;
import base.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A class that represents a request.
 */
public class RequestReader implements Request {

  private final String credential; // who
  private final String action;     // what
  private final LocalDateTime now; // when
  private final String doorId;     // where
  private String userName;
  private boolean authorized;
  private final ArrayList<String> reasons; // why not authorized
  private String doorStateName;
  private boolean doorClosed;

  /**
   * Create a new request.
   *
   * @param credential The credential of the request
   * @param action The action of the request
   * @param now The current time
   * @param doorId The id of the door
   */
  public RequestReader(String credential, String action, LocalDateTime now, String doorId) {
    this.credential = credential;
    this.action = action;
    this.doorId = doorId;
    reasons = new ArrayList<>();
    this.now = now;
  }

  /**
   * Set the door state name in the request.
   */
  public void setDoorStateName(String name) {
    doorStateName = name;
  }

  /**
   * Get the action of the request.
   *
   * @return The action of the request
   */
  public String getAction() {
    return action;
  }

  /**
   * Check if the request is authorized.
   *
   * @return True if the request is authorized, false otherwise
   */
  public boolean isAuthorized() {
    return authorized;
  }

  /**
   * Add a reason why the request is not authorized.
   *
   * @param reason The reason why the request is not authorized
   */
  public void addReason(String reason) {
    reasons.add(reason);
  }


  /**
   * Convert the request to a string.
   */
  @Override
  public String toString() {
    if (userName == null) {
      userName = "unknown";
    }
    return "Request{"
            + "credential=" + credential
            + ", userName=" + userName
            + ", action=" + action
            + ", now=" + now
            + ", doorID=" + doorId
            + ", closed=" + doorClosed
            + ", authorized=" + authorized
            + ", reasons=" + reasons
            + "}";
  }

  /**
   * Convert the answer to a JSON object.
   *
   * @return The JSON object
   */
  public JSONObject answerToJson() {
    JSONObject json = new JSONObject();
    json.put("authorized", authorized);
    json.put("action", action);
    json.put("doorId", doorId);
    json.put("closed", doorClosed);
    json.put("state", doorStateName);
    json.put("reasons", new JSONArray(reasons));
    return json;
  }

  // see if the request is authorized and put this into the request, then send it to the door.
  // if authorized, perform the action.
  /**
   * Process the request.
   */
  public void process() {
    User user = DirectoryUserGroups.findUserByCredential(credential);
    Door door = DirectoryAreas.findDoorById(doorId);
    //noinspection ConstantConditions, because in the simulator maybe the user or the door is not
    assert door != null : "door " + doorId + " not found";
    authorize(user, door);
    // this sets the boolean authorize attribute of the request
    door.processRequest(this);
    // even if not authorized we process the request, so that if desired we could log all
    // the requests made to the server as part of processing the request
    doorClosed = door.isClosed();
  }

  // the result is put into the request object plus, if not authorized, why not,
  // only for testing
  private void authorize(User user, Door door) {
    if (user == null) {
      authorized = false;
      addReason("user doesn't exists");
    } else {
      authorized =
              user.canPerform(action, now)
              && user.hasAccess(door.getFromSpace())
              && user.hasAccess(door.getToSpace());
    }
  }
}

