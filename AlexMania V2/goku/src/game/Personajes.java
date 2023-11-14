package game;

import personajes.AYUDANTE_DOS;
import personajes.Goku;
import personajes.AYUDANTE_UNO;
import poderes.PoderDemonioDebil;
import poderes.PoderFreezerDebil;
import poderes.PoderFreezerFuerte;
import poderes.PoderAYUDANTE_DOS;
import poderes.PoderGokuDebil;
import poderes.PoderAYUDANTE_UNO;
import poderes.PoderMonoGiganteDebil;
import poderes.PoderPiccoloDebil;
import poderes.PoderPiccoloFuerte;
import poderes.PoderRobot;
import enemigos.Demonio;
import enemigos.MonoGigante;
import enemigos.Piccolo;
import enemigos.Robot;

/**
 * Clase de la que heredan todos los personajes, tanto el jugador, aliados y enemigos
 * @author 
 *
 */
public abstract class Personajes extends Animado {

	protected int vitalidad;
	protected static final double FRECUENCIA_ATAQUE = 0.01;

	public Personajes(Escenario escenario) {
		super(escenario);
	}

	/**
	 * Metodo que no provee movimiento
	 */
	protected void sinMovimiento() {
		if (getCoordenadaX() < 0) {
			setCoordenadaX(0);

		} else if (getCoordenadaX() > Escenario.ANCHO - getAncho()) {
			setCoordenadaX(Escenario.ANCHO - getAncho());

		}
		if (getCoordenadaY() < 0) {
			setCoordenadaY(0);

		} else if (getCoordenadaY() > Escenario.ALTO_JUGABLE - getAncho()) {
			setCoordenadaY(Escenario.ALTO_JUGABLE - getAncho());

		}
	}

	/**
	 * Asigna movimiento en el eje y
	 */
	protected void conMovimiento() {
		setCoordenadaY(getCoordenadaY() + getVelocidadY());

		if (getCoordenadaY() < 0
				|| getCoordenadaY() > Escenario.ALTO_JUGABLE - getAlto())
			setVelocidadY(-getVelocidadY());

	}
	
	/**
	 * Chequea las colisiones para los enemigos
	 * @param animado
	 */
	protected void colisionesEnemigo(Animado animado){

		if (animado instanceof Goku) {
			addVitalidad(-Goku.DAMAGE);

		} else if (animado instanceof AYUDANTE_DOS) {
			addVitalidad(-AYUDANTE_DOS.DAMAGE);

		} else if (animado instanceof AYUDANTE_UNO) {
			addVitalidad(-AYUDANTE_UNO.DAMAGE);

		} else if (animado instanceof PoderGokuDebil) {
			animado.remover();
			addVitalidad(-PoderGokuDebil.DAMAGE);

		} else if (animado instanceof PoderAYUDANTE_DOS) {
			animado.remover();
			addVitalidad(-PoderAYUDANTE_DOS.DAMAGE);

		} else if (animado instanceof PoderAYUDANTE_UNO) {
			animado.remover();
			addVitalidad(-PoderAYUDANTE_UNO.DAMAGE);

		}
	
	}
	
	/**
	 * Chequea las colisiones para los alidos o personajes
	 * @param animado
	 */
	protected void colisionesAliado(Animado animado){
		
		if (animado instanceof PoderDemonioDebil) {
			animado.remover();
			addVitalidad(-PoderDemonioDebil.DAMAGE);

		} else if (animado instanceof PoderPiccoloDebil) {
			animado.remover();
			addVitalidad(-PoderPiccoloDebil.DAMAGE);

		} else if (animado instanceof PoderPiccoloFuerte) {
			animado.remover();
			addVitalidad(-PoderPiccoloFuerte.DAMAGE);

		} else if (animado instanceof Demonio) {
			addVitalidad(-Demonio.DAMAGE);

		} else if (animado instanceof Piccolo) {
			addVitalidad(-Piccolo.DAMAGE);

		} else if (animado instanceof Robot) {
			addVitalidad(-Robot.DAMAGE);

		} else if (animado instanceof PoderRobot) {
			animado.remover();
			addVitalidad(-PoderRobot.DAMAGE);

		} else if (animado instanceof MonoGigante){
			addVitalidad(-MonoGigante.DAMAGE);
			
		}else if (animado instanceof PoderMonoGiganteDebil) {
			animado.remover();
			addVitalidad(-PoderMonoGiganteDebil.DAMAGE);

		} else if (animado instanceof PoderFreezerDebil) {
			animado.remover();
			addVitalidad(-PoderFreezerDebil.DAMAGE);

		} else if (animado instanceof PoderFreezerFuerte) {
			animado.remover();
			addVitalidad(-PoderFreezerFuerte.DAMAGE);

		}

	}

	/**
	 * Poder debil
	 */
	public abstract void poderDebil();

	/**
	 * Poder fuerte
	 */
	public abstract void poderFuerte();

	// ********************************

	/**
	 * Obtiene la vitalidad
	 * @return
	 */
	public int getVitalidad() {
		return vitalidad;
	}

	/**
	 * Aisgna la vitalidad
	 * @param v
	 */
	public void setVitalidad(int v) {
		vitalidad = v;
	}

	/**
	 * Quita o agrega vitalidad 
	 * @param v
	 */
	public abstract void addVitalidad(int v);

	// ********************************

}
