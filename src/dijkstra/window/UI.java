package dijkstra.window;

import javax.swing.*;

/**
 * Created by Alex on 09/11/2014.
 */
public class UI extends JFrame{
    JPanel field;

    /*Constructeur de l'interface, elle doit laisser un jpanel vide avant qu'on construise le field*/
    public UI(){

        //window properties
        this.setSize(800, 400);
        this.setTitle("Les souris belges");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void addGraphPanel(int length){
        field = new JPanel();
        this.setContentPane(field);

    }
}
