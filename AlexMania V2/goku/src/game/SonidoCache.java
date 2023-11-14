package game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

/**
 * 
 * @author
 * Se encarga de los sonidos, no se utiliza en el sonido de fondo solo en los poderes e items
 */
public class SonidoCache extends RecursosCache{

	@Override
	protected Object cargarRecurso(URL ruta) {
		return Applet.newAudioClip(ruta);
	}
	
	private boolean continuar = true;
	
	public AudioClip getAudioClip(String nombre){
		return (AudioClip) getRecurso(nombre);
	}
	
	public void playSonido(final String nombre){
		new Thread(
				new Runnable(){
					@Override
					public void run(){
						getAudioClip(nombre).play();
					}
				}).start();
	}
	
	public void loopSonido(final String nombre){
		new Thread(
				new Runnable(){
					@Override
					public void run(){
							AudioClip d = getAudioClip(nombre);
							d.loop();
							if (!continuar)
								d.stop();

					}
				}).start();
	}
	
	public void detenerSonido(){
		continuar = false;
	}

}
