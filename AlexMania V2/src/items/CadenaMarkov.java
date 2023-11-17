package items;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import random.MiRandom;

public class CadenaMarkov {
    private Map<String, Map<String, Double>> transiciones; //Clave String (estado origen), valor (otro mapa que asicia destino[clave], probabilidad[valor])double
    private Random random;
    private MiRandom myRandom;

    public CadenaMarkov() {
        transiciones = new HashMap<>();
        random = new Random();
		myRandom = new MiRandom(System.currentTimeMillis());
        inicializarTransiciones();
    }

    private void inicializarTransiciones() {
        // Definir las probabilidades de transición
        agregarTransicion("He", "Ia", 0.3);
        agregarTransicion("He", "Ir", 0.4);
        agregarTransicion("Ia", "He", 0.25);
        agregarTransicion("Ia", "Ir", 0.4);
        agregarTransicion("Ir", "He", 0.25);
        agregarTransicion("Ir", "Ia", 0.3);

        // Definir las probabilidades de permanecer en el mismo estado
        agregarTransicion("He", "He", 0.3);
        agregarTransicion("Ia", "Ia", 0.35);
        agregarTransicion("Ir", "Ir", 0.45);
    }

    private void agregarTransicion(String estadoOrigen, String estadoDestino, double probabilidad) {
        transiciones.computeIfAbsent(estadoOrigen, k -> new HashMap<>());
        transiciones.get(estadoOrigen).put(estadoDestino, probabilidad);
        //se agrega la probabilidad de transición al estado de destino en el mapa interno asociado al estado de origen.
    }

    public String siguienteEstado(String estadoActual) {
        Map<String, Double> probabilidades = transiciones.get(estadoActual);
        if (probabilidades == null) {
            throw new IllegalArgumentException("Estado no reconocido: " + estadoActual);
        }
        double miValorAleatorio = myRandom.nextDouble();
        double acumulado = 0;

        for (Map.Entry<String, Double> entry : probabilidades.entrySet()) {
            acumulado += entry.getValue();
            if (miValorAleatorio <= acumulado) {
                return entry.getKey(); // Devuelve el estado destino 
            }
        }

        // En caso de redondeo, devuelve el último estado posible
        return probabilidades.entrySet().iterator().next().getKey();
    }

    public static void main(String[] args) {
        CadenaMarkov cadenaMarkov = new CadenaMarkov();

        // Ejemplo de uso
        String estadoActual = "He";
        for (int i = 0; i < 10; i++) {
            System.out.println("Paso " + (i + 1) + ": " + estadoActual);
            estadoActual = cadenaMarkov.siguienteEstado(estadoActual);
        }
    }
}
