package poderes;

import game.Escenario;
import game.Poderes;

public class PoderAlexGod extends Poderes {

	public static int DAMAGE = 20; 

	public PoderAlexGod(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] {"poderes/poderAlexGod0.png", "poderes/poderAlexGod1.png", "poderes/poderAlexGod2.png"});
		setVelocidadFrame(20);
		setVelocidadPoder(7);
	}

	public void accion(){
		super.accion();
		poderAliado();
		
	}
	
}
