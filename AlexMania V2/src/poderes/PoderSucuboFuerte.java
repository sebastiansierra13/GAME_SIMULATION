
package poderes;

import game.Escenario;
import game.Poderes;
import random.MiRandom;

public class PoderSucuboFuerte extends Poderes implements CalcularDuracion{

	public static int DAMAGE = 50;
	private MiRandom myRandom;
	private double probabilidadDuracion = 0.5; // Probabilidad de duración, ajusta según necesites

	public PoderSucuboFuerte(Escenario escenario) {
		super(escenario);
		myRandom = new MiRandom(System.currentTimeMillis());
		setDuracion(calcularDuracion()); // Establecer la duración del poder
		setSpritesNombres(new String[]{"poderes/poderPiccoloFuerte.png"});
		setVelocidadPoder(9);
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
