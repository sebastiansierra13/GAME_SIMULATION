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
	private CadenaMarkov cadenaMarkov;

	

	public Capsula(Escenario escenario) {
		super(escenario);
		myRandom = new MiRandom(System.currentTimeMillis());
		cadenaMarkov = new CadenaMarkov();
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

		String estadoMarkov = cadenaMarkov.siguienteEstado("He");
		switch (estadoMarkov) {
		case "He":
			BOLA_DE_PODER = true;
			System.out.println("he");
			break;

		case "Ia":
			AYUDANTE_UNO = true;
			System.out.println("Ia");
			break;

		case "Ir":
			AYUDANTE_DOS = true;
			System.out.println("Ir");
			break;

		}
	}

}
