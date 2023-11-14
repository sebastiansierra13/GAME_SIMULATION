package poderes;

import game.Escenario;
import game.Poderes;

public class PoderAYUDANTE_DOS extends Poderes{
	
	public static int DAMAGE = 10; 

	public PoderAYUDANTE_DOS(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] {"poderes/poderAYUDANTE_DOS.png"});
		setVelocidadPoder(7);
	}

	@Override
	public void accion(){
		super.accion();
		poderAliado();
	}
	
}
