package poderes;

import game.Escenario;
import game.Poderes;
import random.MiRandom;

public class DemonioMDebil extends Poderes implements CalcularDuracion {

	public static int DAMAGE = 50;
	private MiRandom myRandom;
	private double probabilidadDuracion = 0.8; // Probabilidad de duración, ajusta según necesites

	public DemonioMDebil(Escenario escenario) {
		super(escenario);
		myRandom = new MiRandom(System.currentTimeMillis());
		setSpritesNombres(new String[]{"poderes/poderDemonioMDebil0.png"});
		setVelocidadPoder(5);
	}

	@Override
	public void accion() {
		super.accion();
		poderEnemigo();
	}

	public int calcularDuracion() {
		double probabilidadDuracion = 0.8; // Ajusta según necesites

		// Genera un número aleatorio entre 0 y 1
		double random = myRandom.nextDouble();

		// Si el número aleatorio es menor que la probabilidad de duración,
		// el poder se mantiene activo por un número específico de ciclos
		if (random < probabilidadDuracion) {
			// Ajusta según tus necesidades
			// En este ejemplo, el poder dura entre 1 y 3 ciclos
			int duracionMinima = 1;
			int duracionMaxima = 3;

			// Genera un número aleatorio entre la duración mínima y máxima
			return duracionMinima + (int) (myRandom.nextDouble() * (duracionMaxima - duracionMinima + 1));
		} else {
			// Si el número aleatorio es mayor o igual que la probabilidad de duración,
			// el poder se desvanece en este ciclo
			return 0;
		}
	}


}
