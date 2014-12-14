package engine2D;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dijkstra.graph.Edge;
import dijkstra.graph.Graph;
import dijkstra.graph.Node;
import dijkstra.graph.Souris;

public class GameWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	
	private JButton start = new JButton("Start");
	private JLabel speedLabel = new JLabel("Speed=" + R.fps);
	private JSlider speedSlider = null;
	
	private Graph graph = null;
	private ArrayList<ArrayList<Node>> background = null;
	
	private JPanel controlPanel = new JPanel();
    private GamePanel gamePanel = null;
    private int TOTAL_SOURIS = 4;
    
    private ArrayList<Souris> sourisList = new ArrayList<Souris>();
	
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
	
	private void initSouris(int nb) {
		for (int i=1; i<=nb; i++) {
			sourisList.add(new Souris(i));
		}
	}
	
	private boolean removeSouris(ArrayList<Souris> deleteSouris) {
		System.out.println(sourisList.size());
		for (Souris s : deleteSouris) {
			sourisList.remove(s);
		}
		return true;
	}
	
	private ActionListener startListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			start.setEnabled(false);
			initSouris(TOTAL_SOURIS);
			
			Thread gameThread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					int sourisArrived = 0;
					
					Node door = graph.getDoor();
					Node cheese = graph.getCheese().get(0);
					
					ArrayList<Souris> deleteSouris = new ArrayList<Souris>();
					
					int tour = 1;
					// Tant qu'il reste des souris
					//while (TOTAL_SOURIS != sourisArrived) {
					//while (tour <= 2) {
					while(sourisList.size() > 0) {
						System.out.println("TOUR " + tour);
						removeSouris(deleteSouris);
						for (Souris souris: sourisList) {
							System.out.println(souris);
							// Recherche de chemin pour la souris
							if (souris.getPosition() != null) {
								//System.out.println(souris);
								graph.computePaths(souris.getPosition());
								List<Node> path = graph.getShortestPathTo(cheese);
								//System.out.println(path);
								gamePanel.updateSouris(sourisList);
								
								try {
									//System.out.println(path);
									souris.move(path.get(1));
								} catch (IndexOutOfBoundsException e) {
									//System.out.println("Stop");
								}
								if (souris.getPosition().type.equals(Node.CHEESE)) {
									sourisArrived++;
									deleteSouris.add(souris);
								}
							}
							
							if (souris.getPosition() == null) {
								// Si la position est null alors la souris n'est pas encore sortie de la porte
								// Recherche de node libre pret de la porte
								for (Edge e: door.getEdges()) {
									Node maybeFreeNode = e.getOther();
									if (maybeFreeNode.isOccuped() == false) {
										//System.out.println(souris);
										souris.move(maybeFreeNode);
										//System.out.println(souris);
									}
								}
							}
							
							
						}

						// Ouf, on fait une pause ?
						try {
							Thread.sleep(speedSlider.getValue()); 
							} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						tour++;
					} // while

					// DEbug
					/*for (Souris souris: sourisList) {
						System.out.println(souris);
					}
					*/
					
					
					/*
					List<Node> path = graph.getShortestPathTo(cheese);
					start.setEnabled(false);
					for (Node n: graph.getShortestPathTo(shortestCheese)) {
						try {
							Thread.sleep(speedSlider.getValue());
							gamePanel.update(n);
						} catch (InterruptedException e) {
							
						}
					}
					*/
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

