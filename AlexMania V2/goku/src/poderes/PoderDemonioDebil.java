package poderes;

import game.Escenario;
import game.Poderes;

public class PoderDemonioDebil extends Poderes {

	public static int DAMAGE = 5; 

	public PoderDemonioDebil(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] { "poderes/poderDemonioDebil.png" });
		setVelocidadPoder(7);
	}

	@Override
	public void accion() {
		super.accion();

		poderEnemigo();

	}

}
