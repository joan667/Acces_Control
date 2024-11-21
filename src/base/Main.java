package base;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

/**
 * The main class that starts the simulation.
 */
public class Main {

  /**
   * The main method that starts the simulation.
   *
   * @param args The arguments that are passed to the simulation
   */
  public static void main(String[] args) {

    // Create areas
    DirectoryAreas.makeAreas();

    // Create users
    DirectoryUserGroups.makeUserGroups();

    // Show link to the web server
    System.out.println("Simulator at: http://localhost:63342/Acces_Control/simulator/building.html");

    // Start the web server
    new WebServer();

  }
}
