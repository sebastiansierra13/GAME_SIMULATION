package poderes;

import game.Escenario;
import game.Poderes;

public class PoderFreezerFuerte extends Poderes {
	
	public static int DAMAGE = 200; 

	public PoderFreezerFuerte(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String [] {"poderes/poderFreezerFuerte0.png"});
		setVelocidadPoder(4);
	}
	
	@Override
	public void accion(){
		super.accion();
		poderEnemigo();
	}

}
