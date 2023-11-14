package personajes;

import game.Animado;
import game.Escenario;
import game.Personajes;
import items.Capsula;
import items.Comidas;

import java.awt.event.KeyEvent;

import poderes.PoderAlexDebil;
import poderes.PoderAlexFuerte;

public class Alex extends Personajes {

	public static final int DAMAGE = 20;

	public static final int MAX_VIDA = 250;
	public static final int JUGADOR_VELOCIDAD = 4;
	public static final int MAX_BOLA_DE_PODER = 4;
	public static final int MAX_AYUDANTE_UNO = 2;
	public static final int MAX_AYUDANTE_DOS = 2;

	private boolean arriba, abajo, derecha, izquierda;

	private int puntuacion = 0;
	private int BOLA_DE_PODER = 1;
	private int AYUDANTE_UNO = 0;
	private int AYUDANTE_DOS = 0;

	public Alex(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] { "personajes/Alex0.png",
				"personajes/Alex1.png" });
		setVelocidadFrame(35);
		setVitalidad(MAX_VIDA);

	}

	public void accion() {
		super.accion();
		setCoordenadaX(getCoordenadaX() + getVelocidadX());
		setCoordenadaY(getCoordenadaY() + getVelocidadY());

		if (getCoordenadaX() < 0)
			setCoordenadaX(0);
		if (getCoordenadaX() > Escenario.ANCHO - getAncho())
			setCoordenadaX(Escenario.ANCHO - getAncho());
		if (getCoordenadaY() < 0)
			setCoordenadaY(0);
		if (getCoordenadaY() > Escenario.ALTO_JUGABLE - getAlto())
			setCoordenadaY(Escenario.ALTO_JUGABLE - getAlto());
	}

	// ********************************

	public void keyPressed(KeyEvent evex) {
		switch (evex.getKeyCode()) {

		case KeyEvent.VK_UP:
			arriba = true;
			break;

		case KeyEvent.VK_DOWN:
			abajo = true;
			break;

		case KeyEvent.VK_RIGHT:
			derecha = true;
			break;

		case KeyEvent.VK_LEFT:
			izquierda = true;
			break;

		}
		actualizarVelocidad();
	}

	public void keyReleased(KeyEvent evex) {
		switch (evex.getKeyCode()) {

		case KeyEvent.VK_UP:
			arriba = false;
			break;

		case KeyEvent.VK_DOWN:
			abajo = false;
			break;

		case KeyEvent.VK_RIGHT:
			derecha = false;
			break;

		case KeyEvent.VK_LEFT:
			izquierda = false;
			break;

		case KeyEvent.VK_SPACE:
			poderDebil();
			break;

		case KeyEvent.VK_C:
			poderFuerte();
			break;

		case KeyEvent.VK_V:
			invocarAYUDANTE_DOS();
			break;

		case KeyEvent.VK_B:
			invocarAYUDANTE_UNO();
			break;

		}
		actualizarVelocidad();
	}

	protected void actualizarVelocidad() {
		setVelocidadX(0);
		setVelocidadY(0);

		if (arriba)
			setVelocidadY(-JUGADOR_VELOCIDAD);
		if (abajo)
			setVelocidadY(JUGADOR_VELOCIDAD);

		if (derecha)
			setVelocidadX(JUGADOR_VELOCIDAD);

		if (izquierda)
			setVelocidadX(-JUGADOR_VELOCIDAD);

	}

	// ********************************

	@Override
	public void colisiones(Animado animado) {

		colisionesAliado(animado);

		if (animado instanceof Capsula) {
			animado.remover();
			escenario.getSonidoCache().playSonido("audio/toque.wav");
			if (Capsula.BOLA_DE_PODER)
 				addBOLA_DE_PODER(1);

			else if (Capsula.AYUDANTE_UNO)
				addAYUDANTE_UNO(1);

			else if (Capsula.AYUDANTE_DOS)
				addAYUDANTE_DOS(1);

		}
		if (animado instanceof Comidas) {
			animado.remover();
			escenario.getSonidoCache().playSonido("audio/toque.wav");
			addVitalidad(Comidas.VITALIDAD);
		}

		if (getVitalidad() <= 0) {
			escenario.setAlexVivo(false);
			escenario.setJuegoTerminado(true);
		}
	}

	// ********************************

	@Override
	public void poderDebil() {
		PoderAlexDebil poderDebil = new PoderAlexDebil(escenario);
		poderDebil.setCoordenadaX(coordenadaX + getAncho() - 5);
		poderDebil.setCoordenadaY(coordenadaY);
		escenario.addAnimado(poderDebil);
		escenario.getSonidoCache().playSonido("audio/alexDebil.wav"
				+ "");
	}

	@Override
	public void poderFuerte() {
		if (getBOLA_DE_PODER() > 0) {
			BOLA_DE_PODER--;
			PoderAlexFuerte poderFuerte = new PoderAlexFuerte(escenario);
			poderFuerte.setCoordenadaX(coordenadaX + getAncho() - 5);
			poderFuerte.setCoordenadaY(coordenadaY);
			escenario.addAnimado(poderFuerte);
			escenario.getSonidoCache().playSonido("audio/alexFuerte.wav");
		}

	}

	public void invocarAYUDANTE_UNO() {
		if (getAYUDANTE_UNO() > 0) {
			AYUDANTE_UNO--;
			AlexGod AYUDANTE_UNO = new AlexGod(escenario);
			AYUDANTE_UNO.setCoordenadaX(coordenadaX + getAncho() + 5);
			AYUDANTE_UNO.setCoordenadaY(coordenadaY + 5);
			escenario.addAnimado(AYUDANTE_UNO);
		}
	}

	public void invocarAYUDANTE_DOS() {
		if (getAYUDANTE_DOS() > 0) {
			AYUDANTE_DOS--;
			Robot AYUDANTE_DOS = new Robot(escenario);
			AYUDANTE_DOS.setCoordenadaX(coordenadaX + getAncho() + 5);
			AYUDANTE_DOS.setCoordenadaY(coordenadaY + 5);
			escenario.addAnimado(AYUDANTE_DOS);
		}
	}

	// ********************************

	// Set, get y add de vida y puntos
	public void setPuntuacion(int puntos) {
		puntuacion = puntos;
	}

	public int getPuntiacion() {
		return puntuacion;
	}

	public void addPuntuacion(int puntos) {
		puntuacion += puntos;
	}

	@Override
	public void addVitalidad(int vida) {
		int vidaRestante = MAX_VIDA - vitalidad;
		if (vida >= vidaRestante)
			vitalidad = MAX_VIDA;
		else
			vitalidad += vida;
	}

	// ********************************

	public void setBOLA_DE_PODER(int gd) {
		BOLA_DE_PODER = gd;
	}

	public int getBOLA_DE_PODER() {
		return BOLA_DE_PODER;
	}

	public void addBOLA_DE_PODER(int gd) {
		int restante = MAX_BOLA_DE_PODER - BOLA_DE_PODER;
		if (restante > 0) {
			BOLA_DE_PODER += gd;
		}
	}

	public void setAYUDANTE_UNO(int kr) {
		AYUDANTE_UNO = kr;
	}

	public int getAYUDANTE_UNO() {
		return AYUDANTE_UNO;
	}

	public void addAYUDANTE_UNO(int kr) {
		int restante = MAX_AYUDANTE_UNO - AYUDANTE_UNO;
		if (restante > 0) {
			AYUDANTE_UNO += kr;
		}
	}

	public void setAYUDANTE_DOS(int gh) {
		AYUDANTE_DOS = gh;
	}

	public int getAYUDANTE_DOS() {
		return AYUDANTE_DOS;
	}

	public void addAYUDANTE_DOS(int gh) {
		int restante = MAX_AYUDANTE_DOS - AYUDANTE_DOS;
		if (restante > 0) {
			AYUDANTE_DOS += gh;
		}
	}
}
