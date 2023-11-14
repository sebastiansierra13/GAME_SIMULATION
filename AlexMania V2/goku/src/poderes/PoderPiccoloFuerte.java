package poderes;

import game.Escenario;
import game.Poderes;

public class PoderPiccoloFuerte extends Poderes {

	public static int DAMAGE = 50; 

	public PoderPiccoloFuerte(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] {"poderes/poderPiccoloFuerte.png"});
		setVelocidadPoder(9);
	}

	public void accion(){
		super.accion();
		poderEnemigo();
		
	}
	
	
}
