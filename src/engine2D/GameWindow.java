package engine2D;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dijkstra.graph.Graph;

public class GameWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JButton start = new JButton("Start");
	
	public GameWindow(Graph graph) {
        setTitle("Simple Java 2D example");
        
        JPanel controlPanel = new JPanel();
        controlPanel.add(start);
        
        try {
			add(new GamePanel(graph), BorderLayout.NORTH);
			add(controlPanel, BorderLayout.SOUTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

}

