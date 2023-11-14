package items;

import game.Escenario;
import game.Objetos;

public class Comidas extends Objetos {
	
	private boolean banana = false;
	private boolean manzana = false;
	private boolean pineapple = false;
	private boolean pollo = false;

	public static int VITALIDAD = 0;
	
	public Comidas(Escenario escenario) {
		super(escenario);
		setSpritesNombres(setComida());
	}

	public void accion() {
		super.accion();
	}

	public String[] setComida() {
		
		banana = false;
		manzana = false;
		pineapple = false;
		pollo = false;
		
		int comida = (int) (myRandom.nextDouble() * (4 - 1 + 1) + 1);
		String[] comidaSprite = {};
		switch (comida) {
		case 1:
			comidaSprite = new String[] { "items/banana.png" };
			banana = true;
			break;

		case 2:
			comidaSprite = new String[] { "items/manzana.png" };
			manzana = true;
			break;

		case 3:
			comidaSprite = new String[] { "items/pina.png" };
			pineapple = true;
			break;

		case 4:
			comidaSprite = new String[] { "items/pollo.png" };
			pollo = true;
			break;

		}

		actualizarVitalidad();
		return comidaSprite;
	}

	private void actualizarVitalidad() {
		if (banana) {
			VITALIDAD = 10;

		} else if (manzana) {
			VITALIDAD = 15;

		} else if (pineapple) {
			VITALIDAD = 20;

		} else if (pollo) {
			VITALIDAD = 30;

		}
	}

}
