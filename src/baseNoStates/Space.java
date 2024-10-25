package baseNoStates;
import java.util.ArrayList;

public abstract class Space extends Area {

  //lista de puertasd del espacio
  private ArrayList<Door> doorlist = new ArrayList<Door>();

  public Space(String name, Object parent )
  {
    super(name,parent);
  }

  @Override
  abstract public void addChild(Object child)
  {
    if(child instanceof Door)
    {
      subPartitions.add((Door) child)
    }

    //si no es del tipo de instancia que toca no hacemos nada

  }

}