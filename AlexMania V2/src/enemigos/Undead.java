package enemigos;

import game.Animado;
import game.Escenario;
import game.Personajes;
import poderes.PoderAlexFuerte;
import poderes.PoderUndead;
import random.MiRandom;

public class Undead extends Personajes {

	public static final int DAMAGE = 15;
	private MiRandom myRandom;
	
	public Undead(Escenario escenario) {
		super(escenario);
		myRandom = new MiRandom(System.currentTimeMillis());
		setSpritesNombres(new String[] { "enemigos/undead0.png",
				"enemigos/undead1.png" });
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

		if (animado instanceof PoderAlexFuerte) {
			addVitalidad(-PoderAlexFuerte.DAMAGE);
		}

		if (getVitalidad() <= 0) {
			remover();
			escenario.getJugador().addPuntuacion(20);
		}
	}

	// ********************************

	public void poderDebil() {
		PoderUndead poder = new PoderUndead(escenario);
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
