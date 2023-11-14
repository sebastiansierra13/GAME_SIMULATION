package poderes;

import game.Escenario;
import game.Poderes;

public class PoderGokuFuerte extends Poderes {
	
	public static int DAMAGE = 100; 

	public PoderGokuFuerte(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] { "poderes/poderGokuFuerte0.png",
				"poderes/poderGokuFuerte1.png",
				"poderes/poderGokuFuerte2.png" });
		setVelocidadFrame(40);
		setVelocidadPoder(5);
	}

	@Override
	public void accion() {
		super.accion();
		poderAliado();
	}

}
