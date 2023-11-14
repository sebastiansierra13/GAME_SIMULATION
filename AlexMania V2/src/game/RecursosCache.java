package game;

import java.net.URL;
import java.util.HashMap;

/**
 * 
 * @author
 * Clase abstracta que se encarga de cargar los métodos como 
 */
public abstract class RecursosCache {

	protected HashMap<String, Object> recursos;
	
	public RecursosCache(){
		recursos = new HashMap<String, Object>();
	}
	
	/**
	 * Carga el recurso
	 * @param nombre
	 * @return - el recurso Object
	 */
	protected Object cargarRecurso(String nombre){
		URL ruta = null;
		ruta = getClass().getResource(nombre);
		return cargarRecurso(ruta);
	}
	
	/**
	 * Obtiene el recurso
	 * @param nombre
	 * @return
	 */
	protected Object getRecurso(String nombre){
		Object recurso = recursos.get(nombre);
		if (recurso == null){
			recurso = cargarRecurso("/recursos/"+nombre);
			recursos.put(nombre, recurso);
		}
		return recurso;
	}
	
	protected abstract Object cargarRecurso(URL ruta);
	
}
