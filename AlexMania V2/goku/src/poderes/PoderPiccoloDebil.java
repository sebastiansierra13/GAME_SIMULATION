package poderes;

import game.Escenario;
import game.Poderes;

public class PoderPiccoloDebil extends Poderes {
	
	public static int DAMAGE = 20; 

	public PoderPiccoloDebil(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] { "poderes/poderPiccoloDebil.png" });
		setVelocidadPoder(6);
	}

	public void accion() {
		super.accion();
		poderEnemigo();
	}

}
