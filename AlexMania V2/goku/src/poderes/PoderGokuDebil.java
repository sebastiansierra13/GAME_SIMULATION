package poderes;

import game.Escenario;
import game.Poderes;

public class PoderGokuDebil extends Poderes {
	
	public static int DAMAGE = 15; 

	public PoderGokuDebil(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] { "poderes/poderGokuDebil.png" });
		setVelocidadPoder(8);
	}

	@Override
	public void accion() {

		super.accion();
		poderAliado();
	}

}
