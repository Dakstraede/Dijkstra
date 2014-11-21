package dijkstra.model.content;

/**
 * Created by Mathieu on 17/11/2014.
 */

import dijkstra.util.Dimension;

public abstract class Content {
    private final char CHAR_VALUE = ' ';
    private Dimension position = new Dimension(); //@TODO à ton bon jugement Mathieu, mais de mon point de vue la classe Dimension est inutile

    public int x, y; //coordonnées sur le graph (représentation tableau deux dimensions, x indice de colonne et y indice de ligne)
    public Content(Dimension pos){
        this.position = pos;
    }

    public Content(int x, int y){
        this.x = x;
        this.y = y;
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
