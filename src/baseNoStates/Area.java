package baseNoStates;

public abstract class Area {

  protected String areaName
  //le passamos Object para que luego cada implementacion se ocupe de hacer el casting i comprovaciones pertinentes
  abstract public void addChild(Object child);

  public Area(String name, Object parent )
  {
    this.areaName = name;
    parent.addChild(this);
  }
}
