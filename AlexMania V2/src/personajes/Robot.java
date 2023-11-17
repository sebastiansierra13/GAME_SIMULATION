package personajes;

import game.Animado;
import game.Escenario;
import game.Personajes;
import poderes.PoderRobot;
import random.MiRandom;

public class Robot extends Personajes {

	public static final int DAMAGE = 35;
	private MiRandom myRandom;

	public Robot(Escenario escenario) {
		super(escenario);
		myRandom = new MiRandom(System.currentTimeMillis());
		setSpritesNombres(new String[] { "personajes/Robot0.png",
				"personajes/Robot1.png" });
		setVelocidadFrame(35);
		setVitalidad(50);
	}

	@Override
	public void accion() {

		super.accion();
		sinMovimiento();
		if (myRandom.nextDouble() < FRECUENCIA_ATAQUE) {
			poderDebil();
		}

	}
	@Override
	public void poderDebil() {
		PoderRobot poder = new PoderRobot(escenario);
		poder.setCoordenadaX(coordenadaX + getAncho());
		poder.setCoordenadaY(coordenadaY);
		escenario.addAnimado(poder);
	}

	@Override
	public void poderFuerte() {
		
	}
		@Override
	public void colisiones(Animado animado) {
		colisionesAliado(animado);
		if (getVitalidad() <= 0)
			remover();
	}
	

	@Override
	public void addVitalidad(int v) {
		vitalidad += v;
	}

}
