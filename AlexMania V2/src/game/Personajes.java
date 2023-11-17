package game;

import personajes.Robot;
import personajes.Alex;
import personajes.AlexGod;
import poderes.PoderDemonioDebil;
import poderes.DemonioMDebil;
import poderes.DemonioMFuerte;
import poderes.PoderRobot;
import poderes.PoderAlexDebil;
import poderes.PoderAlexGod;
import poderes.GiganteDeHierroDebil;
import poderes.SucuboDebil;
import poderes.SucuboFuerte;
import poderes.PoderUndead;

import java.util.Random;

import enemigos.*;
import random.MiRandom;


/**
 * Clase de la que heredan todos los personajes, tanto el jugador, aliados y enemigos
 * @author 
 *
 */
public abstract class Personajes extends Animado {

	protected int vitalidad;
	protected static final double FRECUENCIA_ATAQUE = 0.01;
	private MiRandom myRandom;

	public Personajes(Escenario escenario) {
		super(escenario);
		myRandom = new MiRandom(System.currentTimeMillis());
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


	protected void conMovimiento() {
		setCoordenadaY(getCoordenadaY() + getVelocidadY());
		if (getCoordenadaY() < 0 || getCoordenadaY() > Escenario.ALTO_JUGABLE - getAlto()) {
			setVelocidadY(-getVelocidadY());
		}
		setCoordenadaX(getCoordenadaX() + getVelocidadX());
		if (getCoordenadaX() < 0 || getCoordenadaX() > Escenario.ANCHO - getAncho()) {
			setVelocidadX(-getVelocidadX());
		}
		Random random = new Random();
		int probabilidadCambioDireccion = 5;
		if (random.nextInt(100) < probabilidadCambioDireccion) {
			setVelocidadX(random.nextInt(5) - 2);
			setVelocidadY(random.nextInt(5) - 2);
		}
	}

	

	/**
	 * Chequea las colisiones para los enemigos
	 * @param animado
	 */
	protected void colisionesEnemigo(Animado animado){

		if (animado instanceof Alex) {
			addVitalidad(-Alex.DAMAGE);

		} else if (animado instanceof Robot) {
			addVitalidad(-Robot.DAMAGE);

		} else if (animado instanceof AlexGod) {
			addVitalidad(-AlexGod.DAMAGE);

		} else if (animado instanceof PoderAlexDebil) {
			animado.remover();
			addVitalidad(-PoderAlexDebil.DAMAGE);

		} else if (animado instanceof PoderRobot) {
			animado.remover();
			addVitalidad(-PoderRobot.DAMAGE);

		} else if (animado instanceof PoderAlexGod) {
			animado.remover();
			addVitalidad(-PoderAlexGod.DAMAGE);

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

		} else if (animado instanceof SucuboDebil) {
			animado.remover();
			addVitalidad(-SucuboDebil.DAMAGE);

		} else if (animado instanceof SucuboFuerte) {
			animado.remover();
			addVitalidad(-SucuboFuerte.DAMAGE);

		} else if (animado instanceof Demonio) {
			addVitalidad(-Demonio.DAMAGE);

		} else if (animado instanceof Sucubo) {
			addVitalidad(-Sucubo.DAMAGE);

		} else if (animado instanceof Robot) {
			addVitalidad(-Robot.DAMAGE);

		} else if (animado instanceof PoderUndead) {
			animado.remover();
			addVitalidad(-PoderUndead.DAMAGE);

		} else if (animado instanceof GigantedeHierro){
			addVitalidad(-GigantedeHierro.DAMAGE);
			
		}else if (animado instanceof GiganteDeHierroDebil) {
			animado.remover();
			addVitalidad(-GiganteDeHierroDebil.DAMAGE);

		} else if (animado instanceof DemonioMDebil) {
			animado.remover();
			addVitalidad(-DemonioMDebil.DAMAGE);

		} else if (animado instanceof DemonioMFuerte) {
			animado.remover();
			addVitalidad(-DemonioMFuerte.DAMAGE);

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
