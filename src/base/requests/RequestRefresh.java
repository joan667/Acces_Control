package base.requests;

import base.DirectoryAreas;
import base.Door;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * A class that represents a request to refresh the simulator.
 */
public class RequestRefresh implements Request {

  private static final Logger logger = LoggerFactory.getLogger("base.requests.RequestRefresh");

  private final ArrayList<JSONObject> jsonsDoors = new ArrayList<>();

  @Override
  public JSONObject answerToJson() {
    logger.debug("Converting RequestRefresh to JSON.");
    JSONObject json = new JSONObject();
    json.put("doors", new JSONArray(jsonsDoors));
    logger.info("JSON response created for RequestRefresh.");
    return json;
  }

  @Override
  public String toString() {
    logger.debug("Converting RequestRefresh to String.");
    return "RequestRefresh{" + jsonsDoors + "}";
  }

  /**
   * Process the refresh request.
   */
  @Override
  public void process() {
    logger.info("Processing RequestRefresh to update simulator state.");
    for (Door door : DirectoryAreas.getAllDoors()) {
      logger.debug("Adding door id: {} to JSON response.", door.getId());
      jsonsDoors.add(door.toJson());
    }
    logger.info("RequestRefresh processed successfully with {} doors updated.", jsonsDoors.size());
  }
}
