package engine2D;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.JPanel;

import dijkstra.graph.Graph;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -8000054825505140131L;
	
	private Graph graph = null;
	
	public GamePanel(Graph graph) throws IOException {
		this.graph = graph;
		R.load();
		System.out.println(graph.getWidth() + ":" + graph.getHeight());
		setPreferredSize(new Dimension(graph.getWidth() * R.W, graph.getHeight() * R.H));
	}
	
	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int c=0;
		for (int i=0; i<graph.getHeight(); i++) {
			for(int j=0; j<graph.getWidth(); j++) {
				//System.out.println(graph.getWidth() + ":" + graph.getHeight());
				g2d.setPaint(R.getTexture("wall"));
				g2d.fillRect(j*R.H, i*R.W, R.W, R.H);
				c++;
			}
		}
		System.out.println("Paint loop c = " + c);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

}
