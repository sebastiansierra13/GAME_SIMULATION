package personajes;

import game.Animado;
import game.Escenario;
import game.Personajes;
import poderes.PoderAlexGod;
import random.MiRandom;

public class AlexGod extends Personajes {
	
	public static final int DAMAGE = 35;
	private MiRandom myRandom;
	public AlexGod(Escenario escenario) {
		super(escenario);
		myRandom = new MiRandom(System.currentTimeMillis());
		setSpritesNombres(new String[] { "personajes/AlexGod0.png",
				"personajes/AlexGod1.png"
				 });
		setVelocidadFrame(35);
		setVitalidad(70);
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
		PoderAlexGod poder = new PoderAlexGod(escenario);
		poder.setCoordenadaX(coordenadaX + getAncho());
		poder.setCoordenadaY(coordenadaY);
		escenario.addAnimado(poder);
	}
	
	@Override
	public void poderFuerte() {		
	}

	// ********************************

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
