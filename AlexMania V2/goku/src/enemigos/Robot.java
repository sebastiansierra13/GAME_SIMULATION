package enemigos;

import game.Animado;
import game.Escenario;
import game.Personajes;
import poderes.PoderGokuFuerte;
import poderes.PoderRobot;

public class Robot extends Personajes {

	public static final int DAMAGE = 15;
	
	public Robot(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] { "enemigos/enemigoRobot0.png",
				"enemigos/enemigoRobot1.png" });
		setVelocidadFrame(35);
		setVitalidad(15);
	}

	public void accion() {
		super.accion();

		conMovimiento();

		if (myRandom.nextDouble() < FRECUENCIA_ATAQUE) {
			poderDebil();
		}
	}

	@Override
	public void colisiones(Animado animado) {

		colisionesEnemigo(animado);

		if (animado instanceof PoderGokuFuerte) {
			addVitalidad(-PoderGokuFuerte.DAMAGE);
		}

		if (getVitalidad() <= 0)
			remover();
	}

	// ********************************

	public void poderDebil() {
		PoderRobot poder = new PoderRobot(escenario);
		poder.setCoordenadaX(coordenadaX);
		poder.setCoordenadaY(coordenadaY);
		escenario.addAnimado(poder);
	}

	// ********************************

	// ********************************

	public void addVitalidad(int v) {
		vitalidad += v;
	}

	@Override
	public void poderFuerte() {

	}

}
