package dijkstra.model.content.immobiles;

import dijkstra.model.content.Content;
import dijkstra.util.Dimension;

public class Immobile extends Content {

    public final char CHAR_VALUE = ' ';
    public Immobile(Dimension pos)
    {
        super(pos);
    }

    public Immobile(int x, int y){
        super(x,y);
    }
}
