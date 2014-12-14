package dijkstra.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
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
		String testfile = "resources" + File.separator + "map" + File.separator + "map1.txt";
		
		final JFileChooser fc = new JFileChooser(new File("resources" + File.separator + "map"));
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == 1) {
			System.exit(0);
		}
		System.out.println(returnVal);
		testfile = fc.getSelectedFile().getPath();
		
		System.out.println(fc.getSelectedFile().getPath());
		
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
