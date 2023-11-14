package enemigos;

import game.Animado;
import game.Escenario;
import game.Personajes;
import poderes.PoderFreezerDebil;
import poderes.PoderFreezerFuerte;
import poderes.PoderGokuFuerte;

public class Freezer extends Personajes{
	
	public static final int DAMAGE = 30;
	private int contadorAtaques = 0;
	
	public Freezer(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] {"enemigos/enemigoFreezer0.png", "enemigos/enemigoFreezer1.png"});
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
		
		if (animado instanceof PoderGokuFuerte) {
			animado.remover();
			addVitalidad(-PoderGokuFuerte.DAMAGE);
		}
		
		if (getVitalidad() <= 0) {
			remover();
			escenario.getJugador().addPuntuacion(5000);
			escenario.setFreezerMuerto(true);
		}
	}
	
	@Override
	public void poderDebil() {
		PoderFreezerDebil poder = new PoderFreezerDebil(escenario);
		poder.setCoordenadaX(coordenadaX);
		poder.setCoordenadaY(coordenadaY);
		escenario.addAnimado(poder);
	}

	@Override
	public void poderFuerte() {
		PoderFreezerFuerte poder = new PoderFreezerFuerte(escenario);
		poder.setCoordenadaX(coordenadaX);
		poder.setCoordenadaY(coordenadaY);
		escenario.addAnimado(poder);
		escenario.getSonidoCache().playSonido("audio/freezerFuerte.wav");
	}

	// ************************************

	public void addVitalidad(int v) {
		vitalidad += v;
	}
	
}
