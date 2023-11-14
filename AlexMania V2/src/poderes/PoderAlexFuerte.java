package poderes;

import game.Escenario;
import game.Poderes;

public class PoderAlexFuerte extends Poderes {
	
	public static int DAMAGE = 100; 

	public PoderAlexFuerte(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] { "poderes/poderAlexFuerte0.png",
				"poderes/poderAlexFuerte1.png",
				"poderes/poderAlexFuerte2.png" });
		setVelocidadFrame(40);
		setVelocidadPoder(5);
	}

	@Override
	public void accion() {
		super.accion();
		poderAliado();
	}

}
