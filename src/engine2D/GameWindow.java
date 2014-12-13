package engine2D;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dijkstra.graph.Graph;
import dijkstra.graph.Node;

public class GameWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	
	private JButton start = new JButton("Start");
	private JLabel speedLabel = new JLabel("Speed=" + R.fps);
	private JSlider speedSlider = null;
	
	private Graph graph = null;
	private ArrayList<ArrayList<Node>> background = null;
	
	private JPanel controlPanel = new JPanel();
    private GamePanel gamePanel = null;
	
	public GameWindow(Graph graph, ArrayList<ArrayList<Node>> background) {
		this.graph = graph;
		this.background = background;
        setTitle("Simple Java 2D example");
        this.init();
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
	
	private void init() {
		controlPanel.add(speedLabel);
		speedSlider = new JSlider(JSlider.VERTICAL, 100, 2000, R.fps);
		speedSlider.setOrientation(JSlider.HORIZONTAL);
		controlPanel.add(speedSlider);
		controlPanel.add(start);
        
        try {
        	gamePanel = new GamePanel(this.graph, this.background);
			add(gamePanel, BorderLayout.NORTH);
			add(controlPanel, BorderLayout.SOUTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        start.addActionListener(startListener);
        speedSlider.addChangeListener(speedChangeListener);
	}
	
	private ActionListener startListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Thread gameThread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					graph.computePaths(graph.getDoor());
					List<Node> path = graph.getShortestPathTo(graph.getCheese());
					start.setEnabled(false);
					for (Node n: path) {
						try {
							Thread.sleep(speedSlider.getValue());
							gamePanel.update(n);
						} catch (InterruptedException e) {
							
						}
					}
					start.setEnabled(true);
				}
			});
			gameThread.start();
		}
	};
	
	private ChangeListener speedChangeListener = new ChangeListener() {
		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
			speedLabel.setText("Speed=" + source.getValue());
		}
	};
}

