package engine2D;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

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
import dijkstra.graph.GraphPath;
import dijkstra.graph.Mouse;
import dijkstra.graph.Node;

public class GameWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JButton start = new JButton("Start");
	private JLabel speedLabel = new JLabel("Speed=" + R.fps);
	private JSlider speedSlider = null;
	private JLabel tourLabel = new JLabel("Turn=0");
	private JTextField nbMouseDoor1 = new JTextField(3);
	private JTextField nbMouseDoor2 = new JTextField(3);
	private JLabel movementMouseLabel = new JLabel("Movement mouse=" + 0);
	private JLabel mousesNotHungryLabel = new JLabel("Mouses not hungry=" + 0);
	
	private int totalMouvements = 0;
	private JLabel totalMouvementsLabel = new JLabel("Total Mouvements=" + totalMouvements);
	
	
	private Graph graph = null;
	private ArrayList<ArrayList<Node>> background = null;
	
	private JPanel infoPanel = new JPanel(new GridLayout(0, 4));
	private JPanel controlPanel = new JPanel();
    private GamePanel gamePanel = null;
    private final static int TOTAL_MOUSE = 20;
    
    private int nbNotHungryLabelMouses = 0;
    
    /**
     * Liste de souris pour la porte 1
     */
    private ArrayList<Mouse> mouseListDoor1 = new ArrayList<Mouse>();
    
    /**
     * Liste de souris pour la porte 2
     */
    private ArrayList<Mouse> mouseListDoor2 = new ArrayList<Mouse>();
	
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
		
		nbMouseDoor1.setText(TOTAL_MOUSE + "");
		nbMouseDoor2.setText(TOTAL_MOUSE + "");
		controlPanel.add(new JLabel("Mouse Door1="));
		controlPanel.add(nbMouseDoor1);
		controlPanel.add(new JLabel("Mouse Door2="));
		controlPanel.add(nbMouseDoor2);
		infoPanel.add(tourLabel);
		
		controlPanel.add(start);
		infoPanel.add(movementMouseLabel);
		infoPanel.add(mousesNotHungryLabel);
		infoPanel.add(totalMouvementsLabel);
        
        try {
        	gamePanel = new GamePanel(this.graph, this.background);
        	add(infoPanel, BorderLayout.NORTH);
			add(gamePanel, BorderLayout.CENTER);
			add(controlPanel, BorderLayout.SOUTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        start.addActionListener(startListener);
        speedSlider.addChangeListener(speedChangeListener);
	}
	
	private void initMouse(int nb, ArrayList<Mouse> mouseListDoor) {
		for (int i=1; i<=nb; i++) {
			mouseListDoor.add(new Mouse(i));
		}
	}
	
	/**
	 * On supprime tous les souris qui on atteint le fromage
	 * @param deleteSouris
	 * @return
	 */
	private void removeSouris(ArrayList<Mouse> deleteSouris) {
		for (Mouse s : deleteSouris) {
			mouseListDoor1.remove(s);
			mouseListDoor2.remove(s);
		}
	}
	
	/**
	 * On récupère le nombre de souris à partir du champ de saisie
	 * @param nb
	 * @return Si le format n'est pas bon, on retourne le nombre par défaut
	 */
	public int getTotalDoorMouse(JTextField nb) {
		int total = TOTAL_MOUSE;
		try {
			total = Integer.parseInt(nb.getText());
		} catch (NumberFormatException  e) {
			total = TOTAL_MOUSE;
		}
		return total;
	}
	
	private ActionListener startListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			start.setEnabled(false);
			initMouse(getTotalDoorMouse(nbMouseDoor1), mouseListDoor1);
			initMouse(getTotalDoorMouse(nbMouseDoor2), mouseListDoor2);
			
			Thread gameThread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					ArrayList<Node> doors = graph.getDoor();
					ArrayList<Mouse> deleteSouris = new ArrayList<Mouse>();
					int tour = 1;
					totalMouvements = 0;
					
					while( (mouseListDoor1.size()>0) || (mouseListDoor2.size()>0) ) {
						updateInfo(tour);
						if (doors.size() >= 1) {
							for (Mouse souris: mouseListDoor1) {
								searchPath(souris, deleteSouris);
								mouseOutTheDoor(souris, doors.get(0));
								ArrayList<Mouse> updateMouse = new ArrayList<Mouse>(mouseListDoor1);
								updateMouse.addAll(mouseListDoor2);
								gamePanel.updateSouris(updateMouse);
							}
						}
						
						if (doors.size() >= 2) {
							for (Mouse souris: mouseListDoor2) {
								searchPath(souris, deleteSouris);
								mouseOutTheDoor(souris, doors.get(1));
								ArrayList<Mouse> updateMouse = new ArrayList<Mouse>(mouseListDoor1);
								updateMouse.addAll(mouseListDoor2);
								gamePanel.updateSouris(updateMouse);
							}
						} else {
							mouseListDoor2 = new ArrayList<Mouse>();
						}
						// Ouf, on fait une pause ?
						pause();
						tour++;
						removeSouris(deleteSouris);
					} // while
					
					updateInfo(tour);
					gamePanel.updateSouris(mouseListDoor1);
					start.setEnabled(true);
				}



			});
			gameThread.start();
		}
	};
	
	/**
	 * On met a jour les info
	 * @param tour
	 */
	private void updateInfo(int tour) {
		movementMouseLabel.setText("Movement mouse=" + getNbMovementMouse());
		mousesNotHungryLabel.setText("Mouses not hungry=" + nbNotHungryLabelMouses);
		totalMouvementsLabel.setText("Total Mouvements=" + totalMouvements);
		tourLabel.setText("Turn=" + tour);
	}
	
	/**
	 * Pour une souris donnée, on recherche la position suivant et on déplace la souris
	 * Si la souris a atteint le fromage, on l'ajoute a la liste des souris a supprimer
	 * @param souris
	 * @param deleteSouris
	 */
	private void searchPath(Mouse souris, ArrayList<Mouse> deleteSouris) {
		// Recherche de chemin pour la souris
		if (souris.getPosition() != null) {
			try {
				souris.move(getGraphPath(souris.getPosition()).getPath().get(1));
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Stop");
			}
			if (souris.getPosition().type.equals(Node.CHEESE)) {
				deleteSouris.add(souris);
				nbNotHungryLabelMouses++;
				totalMouvements += souris.getSteps();
			}
		}
	}
	
	
	private void pause() {
		try {
			Thread.sleep(speedSlider.getValue()); 
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	private ChangeListener speedChangeListener = new ChangeListener() {
		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
			speedLabel.setText("Speed=" + source.getValue());
		}
	};
	
	/**
	 * Gère la sortie des souris par la porte
	 * @param souris La souris qui veut sortir
	 * @param door La porte, par lequel elle veut sortir
	 */
	private void mouseOutTheDoor(Mouse souris, Node door) {
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
	
	/**
	 * Calcule le chemin vers chaque fromage et choisie le chemin le plus court
	 * @param source
	 * @return le meilleur chemin
	 */
	private GraphPath getGraphPath(Node source) {
		PriorityQueue<GraphPath> queueGraphPath = new PriorityQueue<GraphPath>();
		GraphPath graphPath = null;
		for (Node cheese : graph.getCheese()) {
			graph.computePaths(source);
			graphPath = graph.getShortestPathTo(cheese);
			queueGraphPath.add(graphPath);
		}
		return queueGraphPath.poll();
	}
	
	private int getNbMovementMouse() {
		int res = 0;
		for (Mouse s : mouseListDoor1) {
			if (s.getPosition() != null) {
				res++;
			}
		}
		for (Mouse s : mouseListDoor2) {
			if (s.getPosition() != null) {
				res++;
			}
		}
		return res;
	}
}
