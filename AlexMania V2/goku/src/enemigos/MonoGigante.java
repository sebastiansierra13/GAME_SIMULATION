package enemigos;

import game.Animado;
import game.Escenario;
import game.Personajes;
import poderes.PoderGokuFuerte;
import poderes.PoderMonoGiganteDebil;

public class MonoGigante extends Personajes {

	public static final int DAMAGE = 30;
	
	public MonoGigante(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] { "enemigos/monoGigante0.png",
				"enemigos/monoGigante1.png" });
		setVelocidadFrame(35);
		setVitalidad(1100);
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
			animado.remover();
			addVitalidad(-PoderGokuFuerte.DAMAGE);
		}
		
		if (getVitalidad() <= 0) {
			remover();
			escenario.getJugador().addPuntuacion(3000);
			escenario.setMonoGiganteMuerto(true);
		}
	}

	@Override
	public void poderDebil() {
		PoderMonoGiganteDebil poder = new PoderMonoGiganteDebil(escenario);
		poder.setCoordenadaX(coordenadaX);
		poder.setCoordenadaY(coordenadaY + (getAlto() / 2));
		escenario.addAnimado(poder);
	}

	@Override
	public void poderFuerte() {
	}

	// ************************************

	public void addVitalidad(int v) {
		vitalidad += v;
	}

}
