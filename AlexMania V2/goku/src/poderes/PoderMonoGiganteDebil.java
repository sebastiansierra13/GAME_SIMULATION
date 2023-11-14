package poderes;

import game.Escenario;
import game.Poderes;

public class PoderMonoGiganteDebil extends Poderes {

	public static int DAMAGE = 70; 

	public PoderMonoGiganteDebil(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] {"poderes/poderMonoGigante.png"});
		setVelocidadPoder(8);
	}
	
	public void accion(){
		super.accion();
		poderEnemigo();
		
	}

}
