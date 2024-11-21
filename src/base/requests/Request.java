package base.requests;

import org.json.JSONObject;

/**
 * An interface for a request.
 */
public interface Request {

  /**
   * Get the request type.
   *
   * @return The request type
   */
  JSONObject answerToJson();

  /**
   * Get the request type.
   *
   * @return The request type
   */
  String toString();

  /**
   * Process the request.
   */
  void process();
}
