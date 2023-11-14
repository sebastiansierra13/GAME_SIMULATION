package poderes;

import game.Escenario;
import game.Poderes;
import random.MiRandom;

public class PoderGiganteDeHierroDebil extends Poderes implements CalcularDuracionPoder {

	public static int DAMAGE = 70;
	private MiRandom myRandom;
	private double probabilidadDuracion = 0.7; // Probabilidad de duración, ajusta según necesites

	public PoderGiganteDeHierroDebil(Escenario escenario) {
		super(escenario);
		myRandom = new MiRandom(System.currentTimeMillis());
		setDuracion(calcularDuracion()); // Establecer la duración del poder
		setSpritesNombres(new String[]{"poderes/poderGiganteDeHierro.png"});
		setVelocidadPoder(8);
	}

	@Override
	public void accion() {
		super.accion();
		poderEnemigo();
	}

	public int calcularDuracion() {
		// Implementación del método Montecarlo para calcular la duración
		int duracionMinima = 2;
		int duracionMaxima = 6;
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
