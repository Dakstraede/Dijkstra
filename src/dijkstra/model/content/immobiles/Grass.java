package dijkstra.model.content.immobiles;

import dijkstra.util.Dimension;

public class Grass extends Immobile {

    private final char CHAR_VALUE = 'G';

    public Grass(Dimension pos){
        super(pos);
    }

    public Grass(int x, int y){
        super(x,y);
    }
}
