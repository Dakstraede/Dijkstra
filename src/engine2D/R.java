package engine2D;

import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * Resources Manager
 * @author egor
 *
 */
public class R {
	
	private static String imgPath = "resources" + File.separator + "tiles" + File.separator;
	
	private static HashMap<String, BufferedImage> imageMap = new HashMap<>();
	private static HashMap<String, TexturePaint> textureMap = new HashMap<>();
	
	public final static int H = 26;
	public final static int W = 26;
	
	// Singleton
	private R() {}
	
	private static String[] imagesNames = {
		"cheese",
		"door",
		"empty",
		"grass",
		"sandshrew",
		"wall"
	};
	
	public static void load() throws IOException {
		load(imgPath);
	}
	
	public static void load(String imgPath) throws IOException {
		for (String imagesName : imagesNames) {
			BufferedImage tmp = ImageIO.read(new File(imgPath + imagesName + ".png"));
			imageMap.put(imagesName, tmp);
			initTextures(imagesName, tmp);
			System.out.println("Load File : " + imgPath + imagesName + ".png");
		}
	}
	
	private static void initTextures(String key, BufferedImage img) {
		textureMap.put(key, new TexturePaint(img, new Rectangle(0, 0, W, H)));
	}
	
	public static TexturePaint getTexture(String key) {
		return textureMap.get(key);
	}
	
	public static BufferedImage getImage(String key) {
		return imageMap.get(key);
	}
}
