package dijkstra.main;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import dijkstra.graph.Graph;
import dijkstra.graph.Node;
import dijkstra.parser.FileParser;
import engine2D.GameWindow;

public class Main {
	
	private static ArrayList<ArrayList<Node>> background = null;
	
    public static void main(String[] args) {
        String testfile = "test.txt";

        if(FileParser.isParseable(testfile)){
            Graph graph = FileParser.parseFile(testfile);
            System.out.println("height : "+graph.getHeight());
            System.out.println("width : "+graph.getWidth());
            System.out.println("ground : "+graph.getNumberOfGrounds());
            System.out.println("grass : "+graph.getNumberOfGrass());
            System.out.println("doors : "+graph.getNumberOfDoors());
            System.out.println("exits : "+graph.getNumberOfExits());
            System.out.println("walls : "+graph.getNumberOfWalls());
            System.out.println("edges : "+graph.getNumberOfEdges());
            
            try {
            	background = FileParser.parseIn2dTable(testfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
            
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    GameWindow sk = new GameWindow(graph, background);
                    sk.setVisible(true);
                }
            });
        }
    }
}
