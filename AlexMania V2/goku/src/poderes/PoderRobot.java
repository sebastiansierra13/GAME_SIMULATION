package poderes;

import game.Escenario;
import game.Poderes;

public class PoderRobot extends Poderes {

	public static int DAMAGE = 10; 

	public PoderRobot(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] { "poderes/poderRobot0.png",
				"poderes/poderRobot1.png" });
		setVelocidadFrame(35);
		setVelocidadPoder(5);
	}

	public void accion() {
		super.accion();
		poderEnemigo();
	}

}
