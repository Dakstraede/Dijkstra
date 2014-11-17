package dijkstra.model.content;

/**
 * Created by Mathieu on 17/11/2014.
 */

import dijkstra.util.Dimension;

public abstract class Content {

    private Dimension position = new Dimension();

    public Content(Dimension pos){
        this.position = pos;

    }

    public Dimension getPosition(){
        return this.position;
    }

    public void setPosition(Dimension position) {
        this.position = position;
    }

    public  int getAbscisse(){
        return this.position.getX();
    }

    public int getOrdonnees(){
        return this.position.getY();
    }
}
