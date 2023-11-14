package game;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Es la encargada de cargar los sprites
 * @author 
 *
 */
public class SpriteCache extends RecursosCache implements ImageObserver {

	@Override
	protected Object cargarRecurso(URL ruta) {
		try {
			return ImageIO.read(ruta);

		} catch (Exception ex) {
			System.out
					.println("Error al cargar imagen: cargarRecurso(URL ruta)");
			System.out.println("El error fue : " + ex.getClass().getName()
					+ " " + ex.getMessage());
			return null;
		}
	}

	/**
	 * Crea una imagen compatible
	 * @param ancho
	 * @param alto
	 * @param transparencia
	 * @return
	 */
	public BufferedImage crearCompatible(int ancho, int alto, int transparencia) {
		try {
			GraphicsConfiguration gconfig = GraphicsEnvironment
					.getLocalGraphicsEnvironment().getDefaultScreenDevice()
					.getDefaultConfiguration();
			BufferedImage compatible = gconfig.createCompatibleImage(ancho,
					alto, transparencia);
			return compatible;

		} catch (Exception ex) {
			System.out
					.println("Error al crear compatible: crearCompatible(int ancho, int alto, int transparencia)");
			return null;
		}

	}

	/**
	 * Obtiene la imagen, el sprite
	 * @param nombre
	 * @return
	 */
	public BufferedImage getSprite(String nombre) {
		try {
			BufferedImage cargada = (BufferedImage) getRecurso(nombre);
			BufferedImage compatible = crearCompatible(cargada.getWidth(),
					cargada.getHeight(), Transparency.BITMASK);
			Graphics g = compatible.getGraphics();
			g.drawImage(cargada, 0, 0, this);
			return compatible;

		} catch (Exception ex) {
			System.out
					.println("Error al obtener sprite: getSprite(String nombre)");
			return null;
		}

	}

	@Override
	public boolean imageUpdate(Image img, int infoFlags, int x, int y, int w,
			int h) {
		return (infoFlags & (ALLBITS | ABORT)) == 0;
	}

}
