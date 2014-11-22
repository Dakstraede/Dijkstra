package engine2D;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class Surface extends JPanel {
	
	private TexturePaint cheesep;
	private TexturePaint deerp;
	private TexturePaint emptyp;
	private TexturePaint grassp;
	private TexturePaint sandshrewp;
	private TexturePaint wallp;
	
	
	private static final long serialVersionUID = 3686833008183249761L;
	
	public Surface() throws IOException {
		R.load();
	}

	private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString("Java 2D", 50, 50);
        
        emptyp = new TexturePaint(R.getImage("empty"), new Rectangle(0, 0, 26, 26));
        g2d.setPaint(emptyp);
        g2d.fillRect(0, 0, 26, 26);
        
        cheesep = new TexturePaint(R.getImage("cheese"), new Rectangle(0, 0, 26, 26));
        g2d.setPaint(cheesep);
        g2d.fillRect(0, 0, 26, 26);
        
        g2d.setPaint(emptyp);
        g2d.fillRect(26, 0, 26, 26);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}


public class Skeleton extends JFrame {

	private static final long serialVersionUID = 1L;

	public Skeleton() {

        initUI();
    }

    private void initUI() {

        setTitle("Simple Java 2D example");

        try {
			add(new Surface());
		} catch (IOException e) {
			e.printStackTrace();
		}

        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                Skeleton sk = new Skeleton();
                sk.setVisible(true);
            }
        });
    }
}

