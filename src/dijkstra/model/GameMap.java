package dijkstra.model;
import dijkstra.util.Dimension;

import java.util.ArrayList;
import java.util.List;
//@TODO : Faire les adaptations nécessaires par rapport aux modifications vues à la réunion -> Node est le standard
public class GameMap {

    private Dimension dimension;

    /*private List<Content> contents = new ArrayList<Content>();
    
    public GameMap(Dimension dimensions){
        this.dimension = dimensions;
    }

    public void addContent(Content cont){
        if (cont.getAbscisse() > this.dimension.getX()
                || cont.getAbscisse() < 0
                || cont.getOrdonnees() > this.dimension.getY()
                || cont.getOrdonnees() < 0)
        {
            throw new IllegalArgumentException("Invalid coordinates");
        }
    }

    public List<Sandshrew> getSandshrews()
    {
        List<Sandshrew> sList = new ArrayList<Sandshrew>();
        for(Content c : this.contents)
        {
            if(c instanceof Sandshrew)
            {
                sList.add((Sandshrew)c);
            }
        }
        return sList;
    }*/
}
