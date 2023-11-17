package poderes;

import game.Escenario;
import game.Poderes;
import random.MiRandom;

public class PoderSucuboDebil extends Poderes implements CalcularDuracion {

	public static int DAMAGE = 20;
	private MiRandom myRandom;
	private double probabilidadDuracion = 0.7; // Probabilidad de duración, ajusta según necesites

	public PoderSucuboDebil(Escenario escenario) {
		super(escenario);
		myRandom = new MiRandom(System.currentTimeMillis());
		aplicarVariabilidadEnVelocidad();
		setDuracion(calcularDuracion()); // Establecer la duración del poder
		setSpritesNombres(new String[]{"poderes/poderPiccoloDebil.png"});
		setVelocidadPoder(6);
	}

	@Override
	public void accion() {
		super.accion();
		poderEnemigo();
	}

	public void aplicarVariabilidadEnVelocidad() {
		// Aplicación del método de Monte Carlo para la variabilidad en la velocidad en ambos ejes

		int velocidadAleatoriaX =myRandom.nextIntInRange(100,600);

		int velocidadAleatoriaY = myRandom.nextIntInRange(100,200);

		// Asignación de las velocidades aleatorias al jefe Sucubo
		System.out.println(velocidadAleatoriaX);
		System.out.println(velocidadAleatoriaY);
		setVelocidadX(1);
		setVelocidadY(1);

	}
	@Override
	public int calcularDuracion() {
		// Implementación del método Montecarlo para calcular la duración
		int duracionMinima = 1;
		int duracionMaxima = 4;
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
