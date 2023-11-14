package poderes;

import game.Escenario;
import game.Poderes;

public class PoderAlexDebil extends Poderes {
	
	public static int DAMAGE = 15; 

	public PoderAlexDebil(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] { "poderes/poderAlexDebil.png" });
		setVelocidadPoder(8);
	}

	@Override
	public void accion() {

		super.accion();
		poderAliado();
	}

}
