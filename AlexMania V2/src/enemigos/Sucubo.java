package enemigos;

import game.Animado;
import game.Escenario;
import game.Personajes;
import poderes.PoderAlexFuerte;
import poderes.SucuboDebil;
import poderes.SucuboFuerte;
import random.MiRandom;

public class Sucubo extends Personajes {

	public static final int DAMAGE = 30;
	private int contadorAtaques = 0;
	private MiRandom myRandom;

	public Sucubo(Escenario escenario) {
		super(escenario);
		myRandom = new MiRandom(System.currentTimeMillis());
		setSpritesNombres(new String[] { "enemigos/sucubo0.png",
				"enemigos/sucubo1.png" });
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

		if (animado instanceof PoderAlexFuerte) {
			animado.remover();
			addVitalidad(-PoderAlexFuerte.DAMAGE);
		}
		
		if (getVitalidad() <= 0) {
			remover();
			escenario.getJugador().addPuntuacion(1000);
			escenario.setSucuboMuerto(true);
		}
	}

	@Override
	public void poderDebil() {
		SucuboDebil poderDebil = new SucuboDebil(escenario);
		poderDebil.setCoordenadaX(coordenadaX);
		poderDebil.setCoordenadaY(coordenadaY);
		escenario.addAnimado(poderDebil);
	}

	@Override
	public void poderFuerte() {
		SucuboFuerte poderFuerte = new SucuboFuerte(escenario);
		poderFuerte.setCoordenadaX(coordenadaX);
		poderFuerte.setCoordenadaY(coordenadaY);
		escenario.addAnimado(poderFuerte);
		escenario.getSonidoCache().playSonido("audio/sucuboFuerte.wav");
	}

	// ************************************

	public void addVitalidad(int v) {
		vitalidad += v;
	}

}
