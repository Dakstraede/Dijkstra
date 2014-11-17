package dijkstra.util;

/**
 * Created by Mathieu on 17/11/2014.
 */
public class Dimension {

    public int x, y;


    public Dimension(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Dimension(){
        this.x = 0;
        this.y = 0;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
