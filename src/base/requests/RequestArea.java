package base.requests;

import base.Actions;
import base.Area;
import base.DirectoryAreas;
import base.Door;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A class that represents a request for an area.
 */
public class RequestArea implements Request {

  private final String credential;
  private final String action;
  private final String areaId;
  private final LocalDateTime now;
  private final ArrayList<RequestReader> requests = new ArrayList<>();

  /**
   * Create a new request for an area.
   *
   * @param credential The credential of the request
   * @param action The action of the request
   * @param now The current time
   * @param areaId The id of the area
   */
  public RequestArea(String credential, String action, LocalDateTime now, String areaId) {
    this.credential = credential;
    this.areaId = areaId;
    assert action.equals(Actions.LOCK) || action.equals(Actions.UNLOCK)
            : "invalid action " + action + " for an area request";
    this.action = action;
    this.now = now;
  }

  /**
   * Get the credential of the request.
   *
   * @return The credential of the request
   */
  @SuppressWarnings("unused")   // it is used in the simulator
  public String getAction() {
    return action;
  }

  @Override
  public JSONObject answerToJson() {
    JSONObject json = new JSONObject();
    json.put("action", action);
    json.put("areaId", areaId);
    JSONArray jsonRequests = new JSONArray();
    for (RequestReader rd : requests) {
      jsonRequests.put(rd.answerToJson());
    }
    json.put("requestsDoors", jsonRequests);
    json.put("todo", "request areas not yet implemented");
    return json;
  }

  @Override
  public String toString() {
    String requestsDoorsStr;
    if (requests.isEmpty()) {
      requestsDoorsStr = "";
    } else {
      requestsDoorsStr = requests.toString();
    }
    return "Request{"
            + "credential=" + credential
            + ", action=" + action
            + ", now=" + now
            + ", areaId=" + areaId
            + ", requestsDoors=" + requestsDoorsStr
            + "}";
  }

  // processing the request of an area is creating the corresponding door requests and forwarding
  // them to all of its doors. For some it may be authorized and action will be done, for others
  // it won't be authorized and nothing will happen to them.

  /**
   * Process the request.
   */
  public void process() {
    // commented out until Area, Space and Partition are implemented

    // make the door requests and put them into the area request to be authorized later and
    // processed later
    Area area = DirectoryAreas.findAreaById(areaId);
    // an Area is a Space or a Partition
    //noinspection ConstantConditions, because in the simulator maybe the area is not selected
    if (area != null) {
      // is null when from the app we click on an action but no place is selected because
      // there (flutter) I don't control like I do in javascript that all the parameters provided

      // Make all the door requests, one for each door in the area, and process them.
      // Look for the doors in the spaces of this area that give access to them.
      for (Door door : area.getDoors()) {
        RequestReader requestReader = new RequestReader(credential, action, now, door.getId());
        requestReader.process();
        // after process() the area request contains the answer as the answer
        // to each individual door request, that is read by the simulator/Flutter app
        requests.add(requestReader);
      }
    }
  }
}
