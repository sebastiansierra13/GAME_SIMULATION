package enemigos;

import game.Animado;
import game.Escenario;
import game.Personajes;
import poderes.PoderAlexFuerte;
import poderes.GiganteDeHierroDebil;
import random.MiRandom;

public class GigantedeHierro extends Personajes {

	public static final int DAMAGE = 30;
	private MiRandom myRandom;
	public GigantedeHierro(Escenario escenario) {
		super(escenario);
		myRandom = new MiRandom(System.currentTimeMillis());
		setSpritesNombres(new String[] { "enemigos/gigantedeHierro0.png",
				"enemigos/gigantedeHierro1.png" });
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
		
		if (animado instanceof PoderAlexFuerte) {
			animado.remover();
			addVitalidad(-PoderAlexFuerte.DAMAGE);
		}
		
		if (getVitalidad() <= 0) {
			remover();
			escenario.getJugador().addPuntuacion(3000);
			escenario.setGiganteDeHierroMuerto(true);
		}
	}

	@Override
	public void poderDebil() {
		GiganteDeHierroDebil poder = new GiganteDeHierroDebil(escenario);
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
