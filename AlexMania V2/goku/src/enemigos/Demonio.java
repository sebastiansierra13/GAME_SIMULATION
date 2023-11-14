package enemigos;

import game.Animado;
import game.Escenario;
import game.Personajes;
import poderes.PoderDemonioDebil;
import poderes.PoderGokuFuerte;

public class Demonio extends Personajes {

	public static final int DAMAGE = 15;
	
	public Demonio(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] { "enemigos/demonio0.png",
				"enemigos/demonio1.png" });
		setVelocidadFrame(35);
		setVitalidad(15);
	}

	@Override
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

		if (getVitalidad() <= 0) {
			remover();
			escenario.getJugador().addPuntuacion(20);
		}
	}

	// ********************************

	public void poderDebil() {
		PoderDemonioDebil poderDebil = new PoderDemonioDebil(escenario);
		poderDebil.setCoordenadaX(coordenadaX);
		poderDebil.setCoordenadaY(coordenadaY);
		escenario.addAnimado(poderDebil);

	}

	// ********************************

	public void addVitalidad(int v) {
		vitalidad += v;
	}

	@Override
	public void poderFuerte() {
	}

}
