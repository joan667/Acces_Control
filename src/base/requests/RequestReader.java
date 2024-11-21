package base.requests;

import base.DirectoryAreas;
import base.DirectoryUserGroups;
import base.Door;
import base.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A class that represents a request.
 */
public class RequestReader implements Request {

  private static final Logger logger = LoggerFactory.getLogger("base.requests.RequestReader");

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
   * @param action     The action of the request
   * @param now        The current time
   * @param doorId     The id of the door
   */
  public RequestReader(String credential, String action, LocalDateTime now, String doorId) {
    logger.info("Creating RequestReader with credential: {}, action: {}, doorId: {}, at: {}",
        credential, action, doorId, now);
    this.credential = credential;
    this.action = action;
    this.doorId = doorId;
    reasons = new ArrayList<>();
    this.now = now;
    logger.debug("RequestReader created successfully.");
  }

  /**
   * Set the door state name in the request.
   */
  public void setDoorStateName(String name) {
    logger.debug("Setting door state name to: {} for doorId: {}", name, doorId);
    doorStateName = name;
  }

  /**
   * Get the action of the request.
   *
   * @return The action of the request
   */
  public String getAction() {
    logger.debug("Fetching action for RequestReader with doorId: {}", doorId);
    return action;
  }

  /**
   * Check if the request is authorized.
   *
   * @return True if the request is authorized, false otherwise
   */
  public boolean isAuthorized() {
    logger.debug("Checking if request for doorId: {} is authorized.", doorId);
    return authorized;
  }

  /**
   * Add a reason why the request is not authorized.
   *
   * @param reason The reason why the request is not authorized
   */
  public void addReason(String reason) {
    logger.debug("Adding reason for unauthorized request: {} for doorId: {}", reason, doorId);
    reasons.add(reason);
  }

  /**
   * Convert the request to a string.
   */
  @Override
  public String toString() {
    logger.debug("Converting RequestReader to String for doorId: {}", doorId);
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
    logger.debug("Converting RequestReader to JSON for doorId: {}", doorId);
    JSONObject json = new JSONObject();
    json.put("authorized", authorized);
    json.put("action", action);
    json.put("doorId", doorId);
    json.put("closed", doorClosed);
    json.put("state", doorStateName);
    json.put("reasons", new JSONArray(reasons));
    logger.info("JSON response created for doorId: {}", doorId);
    return json;
  }

  /**
   * Process the request.
   */
  public void process() {
    logger.info("Processing RequestReader for doorId: {} with action: {}", doorId, action);
    User user = DirectoryUserGroups.findUserByCredential(credential);
    Door door = DirectoryAreas.findDoorById(doorId);

    assert door != null : "door " + doorId + " not found";

    authorize(user, door);

    logger.debug("Request authorized: {} for doorId: {}", authorized, doorId);

    door.processRequest(this);
    doorClosed = door.isClosed();
    logger.info("Request processed for doorId: {}. Door closed status: {}", doorId, doorClosed);
  }

  /**
   * Authorize the request.
   *
   * @param user The user making the request
   * @param door The door being accessed
   */
  private void authorize(User user, Door door) {
    logger.debug("Authorizing request for doorId: {} by user: {}", doorId, credential);

    if (user == null) {
      authorized = false;
      addReason("User does not exist.");
      logger.warn("Request denied: User with credential: {} does not exist.", credential);
    } else {
      authorized = user.canPerform(action, now)
          && user.hasAccess(door.getFromSpace())
          && user.hasAccess(door.getToSpace());
      if (!authorized) {
        logger.warn("Request denied for user: {} on doorId: {}", credential, doorId);
      } else {
        logger.info("Request authorized for user: {} on doorId: {}", credential, doorId);
      }
    }
  }
}
