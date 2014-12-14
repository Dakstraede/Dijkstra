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
import javax.swing.JTextField;
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
	private JLabel tourLabel = new JLabel("Tour=0");
	private JTextField nbSouris = new JTextField(3);
	
	private Graph graph = null;
	private ArrayList<ArrayList<Node>> background = null;
	
	private JPanel controlPanel = new JPanel();
    private GamePanel gamePanel = null;
    private int TOTAL_SOURIS = 20;
    
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
		
		nbSouris.setText(TOTAL_SOURIS + "");
		controlPanel.add(new JLabel("Souris="));
		controlPanel.add(nbSouris);
		controlPanel.add(tourLabel);
		
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
		for (Souris s : deleteSouris) {
			sourisList.remove(s);
		}
		return true;
	}
	
	public int getTotalSouris() {
		int total = TOTAL_SOURIS;
		try {
			total = Integer.parseInt(nbSouris.getText());
		} catch (NumberFormatException  e) {
			total = TOTAL_SOURIS;
		}
		return total;
	}
	
	private ActionListener startListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			start.setEnabled(false);
			initSouris(getTotalSouris());
			
			Thread gameThread = new Thread(new Runnable() {
				
				@Override
				public void run() {					
					Node door = graph.getDoor().get(0);
					Node cheese = graph.getCheese().get(0);
					ArrayList<Souris> deleteSouris = new ArrayList<Souris>();
					int tour = 1;
					
					while(sourisList.size() > 0) {
						tourLabel.setText("Tour=" + tour);
						
						for (Souris souris: sourisList) {
							// Recherche de chemin pour la souris
							if (souris.getPosition() != null) {
								graph.computePaths(souris.getPosition());
								List<Node> path = graph.getShortestPathTo(cheese).getPath();
								gamePanel.updateSouris(sourisList);
								
								try {
									souris.move(path.get(1));
								} catch (IndexOutOfBoundsException e) {
									System.out.println("Stop");
								}
								if (souris.getPosition().type.equals(Node.CHEESE)) {
									deleteSouris.add(souris);
								}
							}
							
							if (souris.getPosition() == null) {
								// Si la position est null alors la souris n'est pas encore sortie de la porte
								// Recherche de node libre pret de la porte
								for (Edge e: door.getEdges()) {
									Node maybeFreeNode = e.getOther();
									if (maybeFreeNode.isOccuped() == false) {
										souris.move(maybeFreeNode);
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
						removeSouris(deleteSouris);
					} // while

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

