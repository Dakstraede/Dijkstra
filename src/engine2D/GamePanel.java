package engine2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import dijkstra.graph.Graph;
import dijkstra.graph.Node;
import dijkstra.graph.Souris;
import dijkstra.main.Main;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -8000054825505140131L;
	
	private Graph graph = null;
	
	private ArrayList<ArrayList<Node>> background = null;
	private ArrayList<Souris> sourisList = null;
	
	public GamePanel(Graph graph, ArrayList<ArrayList<Node>> background) throws IOException {
		this.graph = graph;
		this.background = background;
		R.load();
		//System.out.println(this.graph);
		setPreferredSize(new Dimension(this.graph.getWidth() * R.width, this.graph.getHeight() * R.height));
	}
	
	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		drawingBackground(g2d);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    
    private void drawingBackground(Graphics2D g2d) {
    	for (ArrayList<Node> row : background) {
			for (Node node: row) {
				if (node.type.equals(Node.CHEESE) || node.type.equals(Node.DOOR)) {
					g2d.setPaint(R.getTexture(Node.GROUND));
					g2d.fillRect(node.getCoordX() * R.height, node.getCoordY() * R.width, R.width, R.height);
				}
				g2d.setPaint(R.getTexture(node.type));
				g2d.fillRect(node.getCoordX() * R.height, node.getCoordY() * R.width, R.width, R.height);
				// Debug
				if (Main.DEBUG) {
					g2d.setColor(Color.WHITE);
					g2d.drawString((int)node.minDistance + "", node.getCoordX() * R.height, node.getCoordY() * R.width + 10);
				}
			}
		}
    	if (sourisList != null) {
	    	for (Souris souris : sourisList) {
	    		//System.out.println(souris.getPosition());
	    		if (souris.getPosition() != null) {
	    			g2d.setPaint(R.getTexture(Node.SANDSHREW));
					g2d.fillRect(souris.getPosition().getCoordX() * R.height, souris.getPosition().getCoordY() * R.width, R.width, R.height);
	    		}
	    	}
    	}
    }
    
    public void updateSouris(ArrayList<Souris> souris) {
    	this.sourisList = souris;
    	repaint();
    	validate();
    }
}
