package dijkstra.model.content.immobiles;

import dijkstra.model.content.Content;
import dijkstra.util.Dimension;

public class Wall extends Content {

    private final char CHAR_VALUE = '*';

    public Wall(Dimension pos){
        super(pos);
    }

    public Wall(int x, int y){
        super(x,y);
    }
}
