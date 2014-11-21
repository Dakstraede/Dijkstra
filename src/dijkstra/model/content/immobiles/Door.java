package dijkstra.model.content.immobiles;

import dijkstra.util.Dimension;

public class Door extends Immobile {

    private final char CHAR_VALUE = 'D';
    public Door(Dimension pos)
    {
        super(pos);
    }

    public Door(int x, int y){
        super(x,y);
    }
}
