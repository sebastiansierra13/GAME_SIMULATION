package poderes;

import game.Escenario;
import game.Poderes;
import random.MiRandom;

public class PoderDemonioMFuerte extends Poderes implements CalcularDuracion {

	public static int DAMAGE = 200;
	private MiRandom myRandom;
	private static double probabilidadDuracion = 0.6; // Probabilidad de duración

	public PoderDemonioMFuerte(Escenario escenario) {
		super(escenario);
		myRandom = new MiRandom(System.currentTimeMillis());
		setSpritesNombres(new String[]{"poderes/poderDemonioMFuerte0.png"});
		setVelocidadPoder(4);
	}

	@Override
	public void accion() {
		super.accion();
		poderEnemigo();
	}

	public int calcularDuracion() {
		// Implementación del método Montecarlo para calcular la duración
		int duracionMinima = 1;
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

