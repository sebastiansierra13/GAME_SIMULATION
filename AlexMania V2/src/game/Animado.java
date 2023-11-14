package game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Encargada de mover las imagenes, heredada por todo aquello que tenga que pintarse
 * @author 
 *
 */
public class Animado {

	protected int velocidadX, velocidadY;
	protected int coordenadaX, coordenadaY;
	protected int ancho, alto;
	protected String [] spritesNombres;
	protected int actualFrame;
	protected int velocidadFrame;
	protected int t;
	protected boolean listoParaRemover;
	
	protected SpriteCache spriteCache;
	protected Escenario escenario;
	private Poderes poderActual;

	public Animado(Escenario escenario){
		this.escenario = escenario;
		spriteCache = escenario.getSpriteCache();
		actualFrame = 0;
		velocidadFrame = 1;
		t = 0;
	}
	
	public void pintar(Graphics2D g){
		g.drawImage(spriteCache.getSprite(spritesNombres[actualFrame]), coordenadaX, coordenadaY, escenario);
	}
	
	public void setSpritesNombres(String [] nombres){
		spritesNombres = nombres;
		ancho = 0;
		alto = 0;
		for (int x = 0; x < spritesNombres.length; x++){
			BufferedImage imagen = spriteCache.getSprite(spritesNombres[x]);
			ancho = Math.max(ancho, imagen.getWidth());
			alto = Math.max(alto, imagen.getHeight());
		}

	}
	
	public void accion(){
		t++;
		if (t % velocidadFrame == 0){
			t = 0;
			actualFrame = (actualFrame + 1) % spritesNombres.length;
		}
	}
	
	public void colisiones(Animado animado){
		
	}
	
	public Rectangle getCoordenadas(){
		return new Rectangle (coordenadaX, coordenadaY, ancho, alto);
	}
	
	// ********************************
	
	// Set y get de las coordenadas
	public void setCoordenadaX(int x){
		coordenadaX = x;
	}
	public int getCoordenadaX(){
		return coordenadaX;
	}
	public void setCoordenadaY(int y){
		coordenadaY = y;
	}
	public int getCoordenadaY(){
		return coordenadaY;
	}
	
	// ********************************
	
	
	protected void setVelocidadX(int vX){
		velocidadX = vX;
	}
	protected int getVelocidadX(){
		return velocidadX;
	}
	protected void setVelocidadY(int vY){
		velocidadY = vY;
	}
	protected int getVelocidadY(){
		return velocidadY;
	}
	
	// ********************************
	
	// Set y get de el ancho y alto
	public void setAncho(int ancho){
		this.ancho = ancho;
	}
	public int getAncho(){
		return ancho;
	}
	public void setAlto(int alto){
		this.alto = alto;
	}
	public int getAlto(){
		return alto;
	}
	
	// ********************************
	
	public void setVelocidadFrame(int velocidadF){
		velocidadFrame = velocidadF;
	}
	public void setPoder(Poderes poder) {
		this.poderActual = poder;
	}
	// ********************************
	
	public boolean estaListoParaRemover(){
		return listoParaRemover;
	}
	
	public void remover(){
		listoParaRemover = true;
	}
	
}