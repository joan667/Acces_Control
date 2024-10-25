package baseNoStates;
import java.util.ArrayList;

public abstract class Partition extends Area {

  private ArrayList<Area> subPartitions = new ArrayList<Area>();


  public Partition(String name, Object parent )
  {
    super(name,parent);
  }

  @Override
  abstract public void addChild(Object child)
  {
    if(child instanceof Partition)
    {
      subPartitions.add((Partition) child)
    }

    //si no es del tipo de instancia que toca no hacemos nada

  }


}