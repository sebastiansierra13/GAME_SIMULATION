package game;

import java.awt.image.ImageObserver;

import personajes.Goku;

/**
 * 
 * @author
 * Escenario que observa a las imagenes, determina las caracteristicas sobre lo que se pinta.
 * Se manejas datos globales desde aqui
 *
 */
public interface Escenario extends ImageObserver{

	public static final int ANCHO = 900;
	public static final int ALTO = 400;
	public static final int ALTO_JUGABLE = 320;
	public static final int VELOCIDAD = 10;
	
	public SpriteCache getSpriteCache();
	public SonidoCache getSonidoCache();
	
	public void addAnimado(Animado animado);
	
	public Goku getJugador();
	
	public void setPiccoloMuerto(boolean muerto);
	public void setMonoGiganteMuerto(boolean muerto);
	public void setFreezerMuerto(boolean muerto);
	public void setGokuVivo(boolean vivo);
	public void setJuegoTerminado(boolean juegoTerminado);
	
}
