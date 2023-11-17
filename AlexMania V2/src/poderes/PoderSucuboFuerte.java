
package poderes;

import game.Escenario;
import game.Poderes;
import random.MiRandom;

public class PoderSucuboFuerte extends Poderes implements CalcularDuracion {

	public static int DAMAGE = 50;
	private MiRandom myRandom;
	private double probabilidadDuracion = 0.5; // Probabilidad de duración, ajusta según necesites

	public PoderSucuboFuerte(Escenario escenario) {
		super(escenario);
		myRandom = new MiRandom(System.currentTimeMillis());
		aplicarVariabilidadEnVelocidad();
		setDuracion(calcularDuracion()); // Establecer la duración del poder
		setSpritesNombres(new String[]{"poderes/poderPiccoloFuerte.png"});
		setVelocidadPoder(9);
	}
	public void aplicarVariabilidadEnVelocidad() {
		// Aplicación del método de Monte Carlo para la variabilidad en la velocidad en ambos ejes
		int velocidadMinX = -100;
		int velocidadMaxX = -500;
		int velocidadAleatoriaX = (int) (myRandom.nextDouble() * (velocidadMaxX - velocidadMinX + 1) + velocidadMinX);

		int velocidadMinY = -100;
		int velocidadMaxY = -500;
		int velocidadAleatoriaY = (int) (myRandom.nextDouble() * (velocidadMaxY - velocidadMinY + 1) + velocidadMinY);

		// Asignación de las velocidades aleatorias al jefe Sucubo
		setVelocidadX(velocidadAleatoriaX);
		setVelocidadY(velocidadAleatoriaY);

	}
	@Override
	public void accion() {
		super.accion();
		poderEnemigo();
	}

	@Override
	public int calcularDuracion() {
		// Implementación del método Montecarlo para calcular la duración
		int duracionMinima = 2;
		int duracionMaxima = 5;
		int duracion = 0;

		for (int i = 0; i < duracionMaxima; i++) {
			if (myRandom.nextDouble() < probabilidadDuracion) {
				duracion = i + duracionMinima;
				break;
			}
		}

		return duracion;
	}

}
