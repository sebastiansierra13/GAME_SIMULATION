package game;

/**
 * 
 * @author 
 * Clase de la que heredan todos los poderes
 */
public class Poderes extends Animado {

	private int velocidadPoder = 5;
	
	public Poderes(Escenario escenario) {
		super(escenario);
	}
	
	/**
	 * Se le asigna si el ejecutante del poder es un enemigo
	 */
	protected void poderAliado(){
		setCoordenadaX(getCoordenadaX() + getVelocidadPoder());
		if (getCoordenadaX() > Escenario.ANCHO)
			remover();
	}
	
	/**
	 * Se le asigna si el ejecutante es un aliado o el jugador
	 */
	protected void poderEnemigo(){
		setCoordenadaX(getCoordenadaX() - getVelocidadPoder());
		if (getCoordenadaX() < 0)
			remover();
	}
	
	/**
	 * Asigna la velocidad del poder
	 * @param vp
	 */
	protected void setVelocidadPoder(int vp){
		velocidadPoder = vp;
	}
	
	/**
	 * Obtiene la velocidad del poder
	 * @return
	 */
	protected int getVelocidadPoder(){
		return velocidadPoder;
	}

}
