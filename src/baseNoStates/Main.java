package baseNoStates;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

public class Main {
  public static void main(String[] args) {

    //creo que habria que canviar esto por directoryAreas
    DirectoryDoors.makeDoors();


    DirectoryUsers.makeUsers();
    new WebServer();
  }
}
