package engine2D;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

import dijkstra.model.content.Content;

public class Case {
	private int x;
	private int y;
	private BufferedImage image = null;
	private TexturePaint texture = null;
	private Content item = null;
	
	public Case(Content item) {
		this.item = item;
	}
	
	public void render(Graphics2D g2d, int x, int y) {
		texture = new TexturePaint(image, new Rectangle(0, 0, 26, 26));
		g2d.setPaint(texture);
		g2d.fillRect(x, y, 26, 26);
	}
}
