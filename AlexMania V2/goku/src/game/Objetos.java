package game;

/**
 * Heredada de todos los items y comidas
 * @author Rodolfo
 *
 */
public class Objetos extends Animado{

	public Objetos(Escenario escenario) {
		super(escenario);
		setVelocidadX(2);
		setVelocidadY(2);
	}
	
	// ********************************

	@Override
	public void accion(){
		super.accion();
		setCoordenadaX(getCoordenadaX() + getVelocidadX());
		setCoordenadaY(getCoordenadaY() + getVelocidadY());

		if (getCoordenadaX() < 0 || getCoordenadaX() > Escenario.ANCHO - getAncho()) {
			setVelocidadX(- getVelocidadX()) ;
		}
		if (getCoordenadaY() < 0 || getCoordenadaY() > Escenario.ALTO_JUGABLE - getAlto()) {
			setVelocidadY(- getVelocidadY()) ;
		}
	}

	

}
