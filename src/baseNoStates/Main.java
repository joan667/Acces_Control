package baseNoStates;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

public class Main {
  public static void main(String[] args) {

    // Create areas
    DirectoryAreas.makeAreas();   // Or import JSON using: DirectoryAreas.importAreas("src/resources/building.json");

    // Create users
    DirectoryUserGroups.makeUserGroups();

    // Show link to the web server
    System.out.println("Simulator at: http://localhost:63342/Acces_Control/simulator/building.html");

    // Start the web server
    new WebServer();

  }
}
