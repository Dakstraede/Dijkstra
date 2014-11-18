package dijkstra.model.content.mobiles;

import dijkstra.model.content.Content;
import dijkstra.util.Dimension;


public class Sandshrew extends Content {

    private boolean isOut = false;
    private double outTime;
    private Dimension exitTaken = null;

    public Sandshrew(Dimension pos){
        super(pos);
    }

    public boolean isOut(){
        return this.isOut;
    }

    public double getTimeOut(){
        return this.outTime;
    }

    public void setOut(double time, Dimension exitPosition){
        this.isOut = true;
        this.outTime = time;
        this.exitTaken = exitPosition;
    }
}
