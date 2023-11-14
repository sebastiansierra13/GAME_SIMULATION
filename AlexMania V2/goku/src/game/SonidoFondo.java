package game;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 * Carga el sonido de fondo
 * @author 
 *
 */
public class SonidoFondo{

	private AudioClip audio;
	
	public SonidoFondo(){
		audio = Applet.newAudioClip(getClass().getResource("/recursos/audio/audio.wav"));
		audio.loop();
	}

	/**
	 * Detiene el sonido
	 */
	public void detenerSonido(){
		audio.stop();
	}
	
}
