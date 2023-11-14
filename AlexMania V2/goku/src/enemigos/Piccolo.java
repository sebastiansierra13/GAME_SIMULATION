package enemigos;

import game.Animado;
import game.Escenario;
import game.Personajes;
import poderes.PoderGokuFuerte;
import poderes.PoderPiccoloDebil;
import poderes.PoderPiccoloFuerte;

public class Piccolo extends Personajes {

	public static final int DAMAGE = 30;
	private int contadorAtaques = 0;

	public Piccolo(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] { "enemigos/piccolo0.png",
				"enemigos/piccolo1.png" });
		setVelocidadFrame(35);
		setVitalidad(500);
	}

	@Override
	public void accion() {
		super.accion();

		conMovimiento();

		if (contadorAtaques > 1) {
			if (myRandom.nextDouble() < FRECUENCIA_ATAQUE) {
				poderFuerte();
				contadorAtaques = 0;
			}

		} else if (myRandom.nextDouble() < FRECUENCIA_ATAQUE) {
			poderDebil();
			contadorAtaques++;
		}

	}

	@Override
	public void colisiones(Animado animado) {
		
		colisionesEnemigo(animado);

		if (animado instanceof PoderGokuFuerte) {
			animado.remover();
			addVitalidad(-PoderGokuFuerte.DAMAGE);
		}
		
		if (getVitalidad() <= 0) {
			remover();
			escenario.getJugador().addPuntuacion(1000);
			escenario.setPiccoloMuerto(true);
		}
	}

	@Override
	public void poderDebil() {
		PoderPiccoloDebil poderDebil = new PoderPiccoloDebil(escenario);
		poderDebil.setCoordenadaX(coordenadaX);
		poderDebil.setCoordenadaY(coordenadaY);
		escenario.addAnimado(poderDebil);
	}

	@Override
	public void poderFuerte() {
		PoderPiccoloFuerte poderFuerte = new PoderPiccoloFuerte(escenario);
		poderFuerte.setCoordenadaX(coordenadaX);
		poderFuerte.setCoordenadaY(coordenadaY);
		escenario.addAnimado(poderFuerte);
		escenario.getSonidoCache().playSonido("audio/piccoloFuerte.wav");
	}

	// ************************************

	public void addVitalidad(int v) {
		vitalidad += v;
	}

}
