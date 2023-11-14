package game;

import personajes.Robot;
import personajes.Alex;
import personajes.AlexGod;
import poderes.PoderDemonioDebil;
import poderes.PoderDemonioMDebil;
import poderes.PoderDemonioMFuerte;
import poderes.PoderRobot;
import poderes.PoderAlexDebil;
import poderes.PoderAlexGod;
import poderes.PoderGiganteDeHierroDebil;
import poderes.PoderSucuboDebil;
import poderes.PoderSucuboFuerte;
import poderes.PoderUndead;

import java.util.Random;

import enemigos.*;

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

		} else if (animado instanceof PoderSucuboDebil) {
			animado.remover();
			addVitalidad(-PoderSucuboDebil.DAMAGE);

		} else if (animado instanceof PoderSucuboFuerte) {
			animado.remover();
			addVitalidad(-PoderSucuboFuerte.DAMAGE);

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
			
		}else if (animado instanceof PoderGiganteDeHierroDebil) {
			animado.remover();
			addVitalidad(-PoderGiganteDeHierroDebil.DAMAGE);

		} else if (animado instanceof PoderDemonioMDebil) {
			animado.remover();
			addVitalidad(-PoderDemonioMDebil.DAMAGE);

		} else if (animado instanceof PoderDemonioMFuerte) {
			animado.remover();
			addVitalidad(-PoderDemonioMFuerte.DAMAGE);

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
