package base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

/**
 * The main class that starts the simulation.
 */
public class Main {

  private static final Logger logger = LoggerFactory.getLogger("base.Main");

  /**
   * The main method that starts the simulation.
   *
   * @param args The arguments that are passed to the simulation
   */
  public static void main(String[] args) {
    logger.info("Starting the simulation.");

    // Create areas
    logger.info("Initializing areas.");
    DirectoryAreas.makeAreas();
    logger.info("Areas initialized successfully.");

    // Create users
    logger.info("Initializing user groups and users.");
    DirectoryUserGroups.makeUserGroups();
    logger.info("User groups and users initialized successfully.");

    // Show link to the web server
    logger.info("Simulator available at: http://localhost:63342/Acces_Control/simulator/building.html");

    // Start the web server
    logger.info("Starting the web server.");
    new WebServer();
    logger.info("Web server started successfully.");

    logger.info("Simulation started successfully.");
  }
}
