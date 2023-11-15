package items;

import java.util.LinkedList;

import game.Escenario;
import game.Objetos;
import random.MiRandom;

public class Capsula extends Objetos {

	public static boolean BOLA_DE_PODER = false;
	public static boolean AYUDANTE_UNO = false;
	public static boolean AYUDANTE_DOS = false;
	private MiRandom myRandom;

	

	public Capsula(Escenario escenario) {
		super(escenario);
		myRandom = new MiRandom(System.currentTimeMillis());
		setSpritesNombres(new String[] { "items/capsula.png" });
		setPremio();
	}

	public void accion() {
		super.accion();
	}

	private void setPremio() {
		
		BOLA_DE_PODER = false;
		AYUDANTE_UNO = false;
		AYUDANTE_DOS = false;
		
		int posibilidad = (int) (myRandom.nextDouble() * 3 + 1);
		switch (posibilidad) {
		case 1:
			BOLA_DE_PODER = true;
			break;

		case 2:
			AYUDANTE_UNO = true;
			break;

		case 3:
			AYUDANTE_DOS = true;
			break;

		}
	}

}
