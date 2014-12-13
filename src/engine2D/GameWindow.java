package engine2D;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dijkstra.graph.Graph;
import dijkstra.graph.Node;

public class GameWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JButton start = new JButton("Start");
	
	private Graph graph = null;
	private ArrayList<ArrayList<Node>> background = null;
	
	public GameWindow(Graph graph, ArrayList<ArrayList<Node>> background) {
		this.graph = graph;
		this.background = background;
        setTitle("Simple Java 2D example");
        
        JPanel controlPanel = new JPanel();
        controlPanel.add(start);
        
        try {
			add(new GamePanel(this.graph, this.background), BorderLayout.NORTH);
			add(controlPanel, BorderLayout.SOUTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				graph.computePaths(graph.getDoor());
				System.out.println(graph);
			}
		});
        
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

