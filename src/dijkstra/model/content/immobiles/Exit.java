package dijkstra.model.content.immobiles;

import dijkstra.model.content.Content;
import dijkstra.util.Dimension;

public class Exit extends Content {

    private final char CHAR_VALUE = 'A';
    public Exit(Dimension pos){
        super(pos);
    }

    public Exit(int x, int y){
        super(x,y);
    }
}
