package engine2D;

import java.util.ArrayList;
import java.util.PriorityQueue;

import dijkstra.graph.Edge;
import dijkstra.graph.Graph;
import dijkstra.graph.GraphPath;
import dijkstra.graph.Mouse;
import dijkstra.graph.Node;

public class Game implements Runnable {
	
	private GamePanel gamePanel = null;
	private GameWindow gameWindow = null;
	private Graph graph = null;
	
	private int totalMouvements = 0;
	private int nbNotHungryLabelMouses = 0;
	
	/**
     * Liste de souris pour la porte 1
     */
    private ArrayList<Mouse> mouseListDoor1 = new ArrayList<Mouse>();
    
    /**
     * Liste de souris pour la porte 2
     */
    private ArrayList<Mouse> mouseListDoor2 = new ArrayList<Mouse>();
	
	public Game(Graph graph, GamePanel gamePanel, GameWindow gameWindow) {
		this.gamePanel = gamePanel;
		this.gameWindow = gameWindow;
		this.graph = graph;
	}

	@Override
	public void run() {
		ArrayList<Node> doors = graph.getDoor();
		ArrayList<Mouse> deleteSouris = new ArrayList<Mouse>();
		int tour = 1;
		totalMouvements = 0;
		
		while( (mouseListDoor1.size()>0) || (mouseListDoor2.size()>0) ) {
			gameWindow.updateInfo(tour, getNbMovementMouse(), totalMouvements, nbNotHungryLabelMouses);
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
		
		gameWindow.updateInfo(tour, getNbMovementMouse(), totalMouvements, nbNotHungryLabelMouses);
		gamePanel.updateSouris(mouseListDoor1);
		gameWindow.start.setEnabled(true);
	}
	
	/**
	 * On supprime tous les souris qui on atteint le fromage
	 * @param deleteSouris
	 * @return
	 */
	public void removeSouris(ArrayList<Mouse> deleteSouris) {
		for (Mouse s : deleteSouris) {
			mouseListDoor1.remove(s);
			mouseListDoor2.remove(s);
		}
	}
	
	public void initMouse(int nb, int door) {
		for (int i=1; i<=nb; i++) {
			if (door == 1) {
				mouseListDoor1.add(new Mouse(i, door));
			}
			if (door == 2) {
				mouseListDoor2.add(new Mouse(i, door));
			}
		}
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
			Thread.sleep(gameWindow.speedSlider.getValue()); 
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
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
