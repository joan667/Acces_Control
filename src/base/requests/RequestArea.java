package base.requests;

import base.Actions;
import base.Area;
import base.DirectoryAreas;
import base.Door;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * A class that represents a request for an area.
 */
public class RequestArea implements Request {

  private static final Logger logger = LoggerFactory.getLogger("base.requests.RequestArea");

  private final String credential;
  private final String action;
  private final String areaId;
  private final LocalDateTime now;
  private final ArrayList<RequestReader> requests = new ArrayList<>();

  /**
   * Create a new request for an area.
   *
   * @param credential The credential of the request
   * @param action     The action of the request
   * @param now        The current time
   * @param areaId     The id of the area
   */
  public RequestArea(String credential, String action, LocalDateTime now, String areaId) {
    logger.info("Creating RequestArea with credential: {}, action: {}, areaId: {}, at: {}",
        credential, action, areaId, now);
    assert action.equals(Actions.LOCK) || action.equals(Actions.UNLOCK)
        : "Invalid action " + action + " for an area request";
    this.credential = credential;
    this.action = action;
    this.now = now;
    this.areaId = areaId;
    logger.debug("RequestArea created successfully with credential: {}, action: {}, "
        + "areaId: {}, at: {}", credential, action, areaId, now);
  }

  /**
   * Get the credential of the request.
   *
   * @return The credential of the request
   */
  @SuppressWarnings("unused")   // it is used in the simulator
  public String getAction() {
    logger.debug("Fetching action for RequestArea with areaId: {}", areaId);
    return action;
  }

  @Override
  public JSONObject answerToJson() {
    logger.debug("Converting RequestArea with areaId: {} to JSON response.", areaId);
    JSONObject json = new JSONObject();
    json.put("action", action);
    json.put("areaId", areaId);
    JSONArray jsonRequests = new JSONArray();
    for (RequestReader rd : requests) {
      jsonRequests.put(rd.answerToJson());
    }
    json.put("requestsDoors", jsonRequests);
    json.put("todo", "request areas not yet implemented");
    logger.info("JSON response created for RequestArea with areaId: {}", areaId);
    return json;
  }

  @Override
  public String toString() {
    logger.debug("Converting RequestArea with areaId: {} to String.", areaId);
    String requestsDoorsStr = requests.isEmpty() ? "" : requests.toString();
    return "Request{"
        + "credential=" + credential
        + ", action=" + action
        + ", now=" + now
        + ", areaId=" + areaId
        + ", requestsDoors=" + requestsDoorsStr
        + "}";
  }

  /**
   * Process the request.
   */
  public void process() {
    logger.info("Processing RequestArea with areaId: {} and action: {}", areaId, action);
    Area area = DirectoryAreas.findAreaById(areaId);
    if (area != null) {
      logger.debug("Found area with areaId: {}. Creating door requests.", areaId);
      for (Door door : area.getDoors()) {
        RequestReader requestReader = new RequestReader(credential, action, now, door.getId());
        requestReader.process();
        requests.add(requestReader);
        logger.debug("Processed request for doorId: {} in areaId: {}", door.getId(), areaId);
      }
      logger.info("RequestArea with areaId: {} processed successfully.", areaId);
    } else {
      logger.warn("Area with areaId: {} not found. Request cannot be processed.", areaId);
    }
  }
}
