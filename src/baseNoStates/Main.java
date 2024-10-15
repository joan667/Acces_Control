package baseNoStates;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

public class Main {
  public static void main(String[] args) {

    //DirectoryDoors.makeDoors();
    //DirectoryAreas.makeAreas();
    DirectoryAreas.importAreas("src/resources/building.json");


    DirectoryUsers.makeUsers();
    new WebServer();
  }
}
