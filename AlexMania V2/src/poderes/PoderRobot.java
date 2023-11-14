package poderes;

import game.Escenario;
import game.Poderes;

public class PoderRobot extends Poderes{
	
	public static int DAMAGE = 10; 

	public PoderRobot(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] {"poderes/poderRobot.png"});
		setVelocidadPoder(7);
	}

	@Override
	public void accion(){
		super.accion();
		poderAliado();
	}
	
}
