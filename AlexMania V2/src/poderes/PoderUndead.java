package poderes;

import game.Escenario;
import game.Poderes;

public class PoderUndead extends Poderes {

	public static int DAMAGE = 10; 

	public PoderUndead(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] { "poderes/poderUndead0.png",
				"poderes/poderUndead1.png" });
		setVelocidadFrame(35);
		setVelocidadPoder(5);
	}

	public void accion() {
		super.accion();
		poderEnemigo();
	}

}
