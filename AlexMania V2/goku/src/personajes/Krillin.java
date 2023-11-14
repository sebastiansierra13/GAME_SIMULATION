package personajes;

import game.Animado;
import game.Escenario;
import game.Personajes;
import poderes.PoderAYUDANTE_UNO;

public class AYUDANTE_UNO extends Personajes {
	
	public static final int DAMAGE = 35;
	
	public AYUDANTE_UNO(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] { "personajes/aliadoAYUDANTE_UNO0.png",
				"personajes/aliadoAYUDANTE_UNO1.png"
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
		PoderAYUDANTE_UNO poder = new PoderAYUDANTE_UNO(escenario);
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
