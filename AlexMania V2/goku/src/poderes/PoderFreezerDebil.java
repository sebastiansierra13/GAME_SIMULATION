package poderes;

import game.Escenario;
import game.Poderes;

public class PoderFreezerDebil extends Poderes{
	
	public static int DAMAGE = 50; 

	public PoderFreezerDebil(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String [] {"poderes/poderFreezerDebil0.png"});
		setVelocidadPoder(5);
	}
	
	@Override
	public void accion(){
		super.accion();
		poderEnemigo();
	}
	
}
