package personajes;

import game.Animado;
import game.Escenario;
import game.Personajes;
import poderes.PoderAYUDANTE_DOS;

public class AYUDANTE_DOS extends Personajes {

	public static final int DAMAGE = 35;

	public AYUDANTE_DOS(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] { "personajes/aliadoAYUDANTE_DOS0.png",
				"personajes/aliadoAYUDANTE_DOS1.png" });
		setVelocidadFrame(35);
		setVitalidad(50);
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
	public void colisiones(Animado animado) {
		colisionesAliado(animado);
		if (getVitalidad() <= 0)
			remover();
	}

	@Override
	public void poderDebil() {
		PoderAYUDANTE_DOS poder = new PoderAYUDANTE_DOS(escenario);
		poder.setCoordenadaX(coordenadaX + getAncho());
		poder.setCoordenadaY(coordenadaY);
		escenario.addAnimado(poder);
	}
	
	@Override
	public void poderFuerte() {
		
	}

	@Override
	public void addVitalidad(int v) {
		vitalidad += v;
	}

}
