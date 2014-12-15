package engine2D;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dijkstra.graph.Graph;
import dijkstra.graph.Node;

public class GameWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public JButton start = new JButton("Start");
	private JLabel speedLabel = new JLabel("Speed=" + R.fps);
	public JSlider speedSlider = null;
	private JLabel tourLabel = new JLabel("Turn=0");
	private JTextField nbMouseDoor1 = new JTextField(3);
	private JTextField nbMouseDoor2 = new JTextField(3);
	private JLabel movementMouseLabel = new JLabel("Movement mouse=0");
	private JLabel mousesNotHungryLabel = new JLabel("Mouses not hungry=0");
	private JLabel totalMouvementsLabel = new JLabel("Total Mouvements=0");
	
	
	private Graph graph = null;
	private ArrayList<ArrayList<Node>> background = null;
	
	private Game game = null;
	
	private JPanel infoPanel = new JPanel(new GridLayout(0, 4));
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
		
		nbMouseDoor1.setText(R.TOTAL_MOUSE + "");
		nbMouseDoor2.setText(R.TOTAL_MOUSE + "");
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
	

	

	
	/**
	 * On récupère le nombre de souris à partir du champ de saisie
	 * @param nb
	 * @return Si le format n'est pas bon, on retourne le nombre par défaut
	 */
	public int getTotalDoorMouse(String nb) {
		int total = R.TOTAL_MOUSE;
		try {
			total = Integer.parseInt(nb);
		} catch (NumberFormatException  e) {
			total = R.TOTAL_MOUSE;
		}
		return total;
	}
	
	private ActionListener startListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			start.setEnabled(false);
			game = new Game(graph, gamePanel, GameWindow.this);
			game.initMouse(getTotalDoorMouse(nbMouseDoor1.getText()), 1);
			game.initMouse(getTotalDoorMouse(nbMouseDoor2.getText()), 2);
			Thread gameThread = new Thread(game);
			gameThread.start();
		}
	};
	
	/**
	 * On met a jour les info
	 * @param tour
	 */
	public void updateInfo(int tour, int nbMoveMouse, int total, int nbNotHungryMouses) {
		movementMouseLabel.setText("Movement=" + nbMoveMouse);
		mousesNotHungryLabel.setText("Mouses not hungry=" + nbNotHungryMouses);
		totalMouvementsLabel.setText("Total=" + total);
		tourLabel.setText("Turn=" + tour);
	}
	
	private ChangeListener speedChangeListener = new ChangeListener() {
		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
			speedLabel.setText("Speed=" + source.getValue());
		}
	};
}
