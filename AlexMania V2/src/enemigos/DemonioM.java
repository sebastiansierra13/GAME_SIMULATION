package enemigos;

import game.Animado;
import game.Escenario;
import game.Personajes;
import poderes.PoderDemonioMDebil;
import poderes.PoderDemonioMFuerte;
import poderes.PoderAlexFuerte;
import random.MiRandom;

public class DemonioM extends Personajes{
	
	public static final int DAMAGE = 30;
	private MiRandom myRandom;
	private int contadorAtaques = 0;
	
	public DemonioM(Escenario escenario) {
		super(escenario);
		myRandom = new MiRandom(System.currentTimeMillis());
		setSpritesNombres(new String[] {"enemigos/demonioM0.png", "enemigos/demonioM1.png"});
		setVelocidadFrame(35);
		setVitalidad(1000);
	}

	public void accion(){
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
			escenario.getJugador().addPuntuacion(5000);
			escenario.setDemonioMMuerto(true);
		}
	}
	
	@Override
	public void poderDebil() {
		PoderDemonioMDebil poder = new PoderDemonioMDebil(escenario);
		poder.setCoordenadaX(coordenadaX);
		poder.setCoordenadaY(coordenadaY);
		escenario.addAnimado(poder);
	}

	@Override
	public void poderFuerte() {
		PoderDemonioMFuerte poder = new PoderDemonioMFuerte(escenario);
		poder.setCoordenadaX(coordenadaX);
		poder.setCoordenadaY(coordenadaY);
		escenario.addAnimado(poder);
		escenario.getSonidoCache().playSonido("audio/demonioMFuerte.wav");
	}

	// ************************************

	public void addVitalidad(int v) {
		vitalidad += v;
	}
	
}
