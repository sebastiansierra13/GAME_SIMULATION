package poderes;

import game.Escenario;
import game.Poderes;

public class PoderAYUDANTE_UNO extends Poderes {

	public static int DAMAGE = 20; 

	public PoderAYUDANTE_UNO(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] {"poderes/poderAYUDANTE_UNO0.png", "poderes/poderAYUDANTE_UNO1.png", "poderes/poderAYUDANTE_UNO2.png"});
		setVelocidadFrame(20);
		setVelocidadPoder(7);
	}

	public void accion(){
		super.accion();
		poderAliado();
		
	}
	
}
