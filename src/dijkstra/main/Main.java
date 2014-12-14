package dijkstra.main;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import dijkstra.graph.Node;
import dijkstra.parser.FileParser;
import engine2D.GameWindow;

public class Main {

	private static ArrayList<ArrayList<Node>> background = null;
	public static boolean DEBUG_GRAPH = false;
	public static boolean DEBUG_SOURIS = false;

	public static void main(String[] args) {
		String testfile = "resources/map/map1.txt";
		FileParser parser = new FileParser();
		try {
			if (parser.isParseable(testfile)) {
				background = parser.parseIn2dTable(testfile);
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						GameWindow sk = new GameWindow(parser.getGraph(),
								background);
						sk.setVisible(true);
					}
				});
			} else {
				JOptionPane.showMessageDialog(null, "Invalide Map", "File Parser", 0);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "File Parser", 0);
		}
	}
}
