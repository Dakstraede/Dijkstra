package dijkstra.main;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import dijkstra.graph.Node;
import dijkstra.parser.FileParser;
import engine2D.GameWindow;

public class Main {
	
	private static ArrayList<ArrayList<Node>> background = null;
	
    public static void main(String[] args) {
        String testfile = "resources/map/map2.txt";
        final FileParser parser = new FileParser();
        
        if(parser.isParseable(testfile)){  
            try {
            	background = parser.parseIn2dTable(testfile);
            	
			} catch (IOException e) {
				e.printStackTrace();
			}
            
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    GameWindow sk = new GameWindow(parser.getGraph(), background);
                    sk.setVisible(true);
                }
            });
        } else {
        	System.out.println("Invalide Map");
        }
    }
}
