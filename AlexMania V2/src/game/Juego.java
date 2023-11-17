package game;

import items.Capsula;
import items.Comidas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.Transparency;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import personajes.Alex;
import enemigos.Demonio;
import enemigos.DemonioM;
import enemigos.GigantedeHierro;
import enemigos.Sucubo;
import enemigos.Undead;
import poderes.PoderDemonioMDebil;
import poderes.PoderGiganteDeHierroDebil;
import poderes.PoderSucuboFuerte;
import random.MiRandom;

public class Juego extends Canvas implements Escenario, KeyListener {

	private static final long serialVersionUID = 7534850285592863856L;
	private static final double PROBABILIDAD_CREACION = 0.6;
	private MiRandom myRandom;
	private BufferStrategy strategy;

	private SpriteCache spriteCache;
	private SonidoCache sonidoCache;
	private SonidoFondo sonidoFondo;

	private ArrayList<Animado> animados;
	private Alex jugador;

	private BufferedImage fondo, fondoTile;
	private int fondoX;

	private boolean reiniciar;
	private boolean juegoPausado;

	private boolean AlexVivo;
	private boolean juegoTerminado;

	private boolean jefeSucuboLlamado;
	private boolean jefeSucuboMuerto;

	private boolean jefeDemonioMLlamado;
	private boolean jefeDemonioMMuerto;

	private boolean jefeGiganteDeHierroLlamado;
	private boolean jefeGiganteDeHierroMuerto;

	private int contadorEnemigos;
	private int contadorMuertes;

	private JFrame ventanaPrincipal;
	private JPanel panel;

	public Juego() {
		myRandom = new MiRandom(System.currentTimeMillis());
		ventanaPrincipal = new JFrame();
		ventanaPrincipal.setTitle("Alex Mania");
		ventanaPrincipal
				.setSize(new Dimension(Escenario.ANCHO, Escenario.ALTO));
		ventanaPrincipal.setResizable(false);
		ventanaPrincipal.setLocationRelativeTo(null);
		ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanaPrincipal.setVisible(true);

	}

	// ********************************

	/**
	 * Inicializa el mundo, todos los elementos que pertenecen al juego
	 */
	public void inicializarMundo() {

		panel = (JPanel) ventanaPrincipal.getContentPane();
		setBounds(0, 0, Escenario.ANCHO, Escenario.ALTO);
		panel.setPreferredSize(new Dimension(Escenario.ANCHO, Escenario.ALTO));
		panel.setLayout(null);
		panel.add(this);

		jefeSucuboLlamado = false;
		jefeSucuboMuerto = false;

		jefeDemonioMLlamado = false;
		jefeDemonioMMuerto = false;

		jefeGiganteDeHierroLlamado = false;
		jefeGiganteDeHierroMuerto = false;

		contadorEnemigos = 0;
		contadorMuertes = 0;

		spriteCache = new SpriteCache();
		sonidoCache = new SonidoCache();
		sonidoFondo = new SonidoFondo();

		AlexVivo = true;
		juegoTerminado = false;

		createBufferStrategy(2);
		strategy = getBufferStrategy();
		requestFocus();
		addKeyListener(this);
		setIgnoreRepaint(true);

		// Inicializa el arreglo de animados y los mounstruos
		animados = new ArrayList<Animado>();

		for (int x = 0; x < 4; x++) {
			Demonio enemigo = new Demonio(this);
			enemigo.setCoordenadaX((int) (myRandom.nextDouble()
					* ((Escenario.ANCHO - 50) - (Escenario.ANCHO / 2) + 1) + (Escenario.ANCHO / 2)));
			enemigo.setCoordenadaY((int) (myRandom.nextDouble() * (Escenario.ALTO_JUGABLE - enemigo
					.getAlto())));
			enemigo.setVelocidadY((int) (myRandom.nextDouble() * (2 - 6) - 1));
			animados.add(enemigo);
			contadorEnemigos++;
		}
		for (int x = 0; x < 4; x++) {
			Undead enemigo = new Undead(this);
			enemigo.setCoordenadaX((int) (myRandom.nextDouble()
					* ((Escenario.ANCHO - 50) - (Escenario.ANCHO / 2) + 1) + (Escenario.ANCHO / 2)));
			enemigo.setCoordenadaY((int) (myRandom.nextDouble() * (Escenario.ALTO_JUGABLE - enemigo
					.getAlto())));
			enemigo.setVelocidadY((int) (myRandom.nextDouble() * (2 - 6) - 1));
			animados.add(enemigo);
			contadorEnemigos++;
		}

		// Inicializa al jugador
		jugador = new Alex(this);
		jugador.setCoordenadaX(jugador.getAncho());
		jugador.setCoordenadaY(Escenario.ALTO_JUGABLE / 2);

		fondoTile = spriteCache.getSprite("fondo.png");
		fondo = spriteCache.crearCompatible(
				Escenario.ANCHO + fondoTile.getWidth(), Escenario.ALTO,
				Transparency.OPAQUE);
		Graphics2D g = (Graphics2D) fondo.getGraphics();
		g.setPaint(new TexturePaint(fondoTile, new Rectangle(0, 0, fondoTile
				.getWidth(), fondoTile.getHeight())));
		g.fillRect(0, 0, fondo.getWidth(), fondo.getHeight());
		fondoX = fondoTile.getHeight();

		reiniciar = false;
		juegoPausado = false;

	}

	/**
	 * Pinta el mundo una vez haya sido actualizado
	 */
	public void pintarMundo() {
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

		g.drawImage(fondo, 0, 0, Escenario.ANCHO, Escenario.ALTO, fondoX, 0,
				Escenario.ANCHO + fondoX, Escenario.ALTO, this);

		for (int x = 0; x < animados.size(); x++) {
			Animado animado = (Animado) animados.get(x);
			animado.pintar(g);
		}

		jugador.pintar(g);

		pintarDatos(g);

		strategy.show();

	}

	/**
	 * Actualiza y repinta todos los elementos visibles
	 */
	public void actualizarMundo() {

		for (int x = 0; x < animados.size(); x++) {

			Animado animado = (Animado) animados.get(x);
			if (animado.estaListoParaRemover()) {
				if (animado instanceof Demonio || animado instanceof Undead) {
					contadorMuertes++;
					contadorEnemigos--;
				}

				animados.remove(x);

			} else {
				animado.accion();

			}
		}

		if (getContadorMuertes() > 15 && !jefeSucuboMuerto
				&& !jefeSucuboLlamado) {
			llamarJefBossSucubo();

		}
		if (getContadorMuertes() > 30 && jefeSucuboMuerto
				&& !jefeGiganteDeHierroMuerto && !jefeGiganteDeHierroLlamado) {
			llamarJefeGiganteDeHierro();

		}
		if (getContadorMuertes() > 45 && jefeGiganteDeHierroMuerto
				&& !jefeDemonioMMuerto && !jefeDemonioMLlamado) {
			llamarJefeDemonioM();

		}
		if (getContadorMuertes() > 50 && jefeDemonioMMuerto) {
			juegoTerminado = true;
		}

		if (contadorEnemigos < 2) {
			agregarEnemigo();
		}

		jugador.accion();
		agregarItems();
	}

	/**
	 * Agrega un item al azar
	 */
	public void agregarItems() {
		int oportunidad =  (myRandom.nextIntInRange(1,180));
		if (oportunidad == 23) {
			oportunidad =  (myRandom.nextIntInRange(1,2));
			if (oportunidad == 1) {
				Capsula capsula = new Capsula(this);
				capsula.setCoordenadaX((int) (myRandom.nextDouble()
						* ((Escenario.ANCHO - 50) - (Escenario.ANCHO / 2) + 1) + (Escenario.ANCHO / 2)));
				capsula.setCoordenadaY((int) (myRandom.nextDouble()
						* ((Escenario.ALTO_JUGABLE - capsula.getAlto()) - (0) + 1) + 0));
				animados.add(capsula);

			} else if (oportunidad == 2) {
				Comidas comida = new Comidas(this);
				comida.setCoordenadaX((int) (myRandom.nextDouble()
						* ((Escenario.ANCHO - 50) - (Escenario.ANCHO / 2) + 1) + (Escenario.ANCHO / 2)));
				comida.setCoordenadaY((int) (myRandom.nextDouble()
						* ((Escenario.ALTO_JUGABLE - comida.getAlto()) - (0) + 1) + 0));
				animados.add(comida);

			}
		}
	}

	/**
	 * Agrega un enemigo al azar
	 */
	//METODO MONTECARLO

	/*En el método agregarEnemigo(), se aplicó el método Montecarlo para
	introducir variabilidad en la generación de enemigos. Se utilizó una
	probabilidad predefinida para decidir si se crea un Undead o un Demonio,
	simulando así un evento incierto. Al emplear números aleatorios proporcionados
	por la función nextDouble() de la clase Random, se logra una predicción
	probabilística de los resultados posibles, cumpliendo con la esencia de
	las simulaciones de Montecarlo. Este enfoque permite ajustar la frecuencia
	de aparición de cada tipo de enemigo, otorgando dinamismo y diversidad
	al juego de acuerdo con la probabilidad establecida.*/
	public void agregarEnemigo() {
		double probabilidadUndead = PROBABILIDAD_CREACION; // Probabilidad de que aparezca un Undead
		if (myRandom.nextDouble() < probabilidadUndead) {
			Undead enemigo = new Undead(this);
			enemigo.setCoordenadaX((int) (myRandom.nextDouble() * ((Escenario.ANCHO - 50) - (Escenario.ANCHO / 2) + 1) + (Escenario.ANCHO / 2)));
			enemigo.setCoordenadaY((int) (myRandom.nextDouble() * ((Escenario.ALTO_JUGABLE - enemigo.getAlto()) - (0) + 1) + 0));
			enemigo.setVelocidadY((int) (myRandom.nextDouble() * (2 - 6) - 1));
			animados.add(enemigo);
			contadorEnemigos++;
		} else {
			Demonio enemigo = new Demonio(this);
			enemigo.setCoordenadaX((int) (myRandom.nextDouble() * ((Escenario.ANCHO - 50) - (Escenario.ANCHO / 2) + 1) + (Escenario.ANCHO / 2)));
			enemigo.setCoordenadaY((int) (myRandom.nextDouble() * ((Escenario.ALTO_JUGABLE - enemigo.getAlto()) - (0) + 1) + 0));
			enemigo.setVelocidadY((int) (myRandom.nextDouble() * (2 - 6) - 1));
			animados.add(enemigo);
			contadorEnemigos++;
		}
	}

	public void pintarDatos(Graphics2D g) {

		g.setPaint(Color.gray);
		g.fillRect(0, Escenario.ALTO_JUGABLE, Escenario.ANCHO, 3);

		g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		g.setPaint(Color.black);
		g.drawString("Vida:", 10, Escenario.ALTO_JUGABLE + 30);

		g.setPaint(Color.red);
		g.fillRect(70, Escenario.ALTO_JUGABLE + 15, Alex.MAX_VIDA, 20);

		g.setPaint(Color.green);
		g.fillRect(70, Escenario.ALTO_JUGABLE + 15, jugador.getVitalidad(), 20);

		g.setPaint(Color.gray);
		g.fillRect(350, Escenario.ALTO_JUGABLE + 10, 2, 30);

		g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		g.setPaint(Color.black);
		g.drawString("Puntos:", 360, Escenario.ALTO_JUGABLE + 30);

		g.setPaint(Color.yellow);
		g.drawString(Integer.toString(jugador.getPuntiacion()), 440,
				Escenario.ALTO_JUGABLE + 30);

		g.setPaint(Color.gray);
		g.fillRect(540, Escenario.ALTO_JUGABLE + 10, 2, 30);

		for (int x = 0; x < jugador.getBOLA_DE_PODER(); x++) {
			BufferedImage genki = spriteCache
					.getSprite("items/iconBOLA_DE_PODER.png");
			g.drawImage(genki, 550 + x * genki.getWidth(),
					Escenario.ALTO_JUGABLE + 15, this);

		}
		g.setPaint(Color.gray);
		g.fillRect(640, Escenario.ALTO_JUGABLE + 10, 2, 30);
		for (int x = 0; x < jugador.getAYUDANTE_UNO(); x++) {
			BufferedImage AYUDANTE_UNO = spriteCache
					.getSprite("items/iconAlexGod.png");
			g.drawImage(AYUDANTE_UNO, 660 + x * AYUDANTE_UNO.getWidth(),
					Escenario.ALTO_JUGABLE + 15, this);
		}
		g.fillRect(740, Escenario.ALTO_JUGABLE + 10, 2, 30);
		for (int x = 0; x < jugador.getAYUDANTE_DOS(); x++) {
			BufferedImage AYUDANTE_DOS = spriteCache.getSprite("items/iconRobot.png");
			g.drawImage(AYUDANTE_DOS, 760 + x * AYUDANTE_DOS.getWidth(),
					Escenario.ALTO_JUGABLE + 15, this);
		}

	}

	/**
	 * Pinta el mensaje de juego perdido
	 */
	public void juegoPerdido() {
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		g.setPaint(Color.BLACK);
		g.drawString("FIN, HAS MUERTO", 100, 100);
		strategy.show();
		this.getSonidoCache().playSonido("audio/youLose.wav");
	}

	/**
	 * Pinta el mensaje de juego ganado
	 */
	public void juegoGanado() {
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		g.setPaint(Color.BLACK);
		g.drawString("FELICIDADES. HAS GANADO.", 100, 100);
		g.setPaint(Color.white);
		strategy.show();
		
	}
	
	/**
	 * Pinta el mensaje Pausa 
	 */
	public void juegoPausado(){
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		g.setPaint(Color.red);
		g.drawString("PAUSA", 10, 20);
		strategy.show();
	}

	// ********************************

	/**
	 * Verifica las colisiones
	 */
	public void verificarColisiones() {
		Rectangle jugadorCoordenadas = jugador.getCoordenadas();

		for (int x = 0; x < animados.size(); x++) {

			Animado animado1 = (Animado) animados.get(x);
			Rectangle rectangulo1 = animado1.getCoordenadas();
			if (rectangulo1.intersects(jugadorCoordenadas)) {
				jugador.colisiones(animado1);
				animado1.colisiones(jugador);
			}

			for (int y = x + 1; y < animados.size(); y++) {
				Animado animado2 = (Animado) animados.get(y);
				Rectangle rectangulo2 = animado2.getCoordenadas();
				if (rectangulo1.intersects(rectangulo2)) {
					animado1.colisiones(animado2);
					animado2.colisiones(animado1);
				}
			}

		}
	}

	// ********************************

	public void jugar() {

		do {

			inicializarMundo();

			while (isVisible() && !juegoTerminado) {
				long tiempoInicio = System.currentTimeMillis();

				fondoX--;
				if (fondoX < 0)
					fondoX = fondoTile.getWidth();

				actualizarMundo();
				verificarColisiones();
				pintarMundo();

				do {
					Thread.yield();
				} while (System.currentTimeMillis() - tiempoInicio < 17);

				while(juegoPausado){
					juegoPausado();
				}
				
			}

			sonidoFondo.detenerSonido();

			if (AlexVivo)
				juegoGanado();
			else
				juegoPerdido();

			while (reiniciar == false) {
				
			}

		} while (reiniciar);
	}

	// ********************************

	@Override
	public void keyPressed(KeyEvent evex) {
		jugador.keyPressed(evex);
	}

	@Override
	public void keyReleased(KeyEvent evex) {
		jugador.keyReleased(evex);

		if (evex.getKeyCode() == KeyEvent.VK_R)
			if (!AlexVivo)
				reiniciar = true;
			
		if (evex.getKeyCode() == KeyEvent.VK_P)
			pausarJuego();
	}

	@Override
	public void keyTyped(KeyEvent evex) {
	}

	// ********************************

	@Override
	public SpriteCache getSpriteCache() {
		return spriteCache;
	}

	@Override
	public SonidoCache getSonidoCache() {
		return sonidoCache;
	}

	@Override
	public void addAnimado(Animado animado) {
		animados.add(animado);
	}

	@Override
	public Alex getJugador() {
		return jugador;
	}

	// ********************************

	public void llamarJefBossSucubo() {
		Sucubo BossSucubo = new Sucubo(this);
		// Crear instancia del poder con duración aleatoria
		PoderSucuboFuerte poderSucuboFuerte = new PoderSucuboFuerte(this);

		// Agregar el poder al jefe
		BossSucubo.setPoder(poderSucuboFuerte);
		BossSucubo.setCoordenadaX(Escenario.ANCHO - BossSucubo.getAncho() - 100);
		BossSucubo.setCoordenadaY((int) (myRandom.nextDouble() * (Escenario.ALTO_JUGABLE - BossSucubo
				.getAlto())));
		BossSucubo.setVelocidadY(2);
		animados.add(BossSucubo);
		jefeSucuboLlamado = true;

	}

	public void llamarJefeGiganteDeHierro() {
		GigantedeHierro BossGiganteDeHierro = new GigantedeHierro(this);
		// Crear instancia del poder con duración aleatoria
		PoderGiganteDeHierroDebil poderGiganteDeHierroDebil = new PoderGiganteDeHierroDebil(this);

		// Agregar el poder al jefe
		BossGiganteDeHierro.setPoder(poderGiganteDeHierroDebil);
		BossGiganteDeHierro.setCoordenadaX(Escenario.ANCHO - BossGiganteDeHierro.getAncho() - 100);
		BossGiganteDeHierro.setCoordenadaY((int) (myRandom.nextDouble() * (Escenario.ALTO_JUGABLE - BossGiganteDeHierro
				.getAlto())));
		BossGiganteDeHierro.setVelocidadY(1);
		animados.add(BossGiganteDeHierro);
		jefeGiganteDeHierroLlamado = true;
	}

	public void llamarJefeDemonioM() {
		DemonioM BossDemonioM = new DemonioM(this);
		// Crear instancia del poder con duración aleatoria
		PoderDemonioMDebil poderDemonioMDebil = new PoderDemonioMDebil(this);

		// Agregar el poder al jefe
		BossDemonioM.setPoder(poderDemonioMDebil);
		BossDemonioM.setCoordenadaX(Escenario.ANCHO - BossDemonioM.getAncho() - 100);
		BossDemonioM.setCoordenadaY((int) (myRandom.nextDouble() * (Escenario.ALTO_JUGABLE - BossDemonioM
				.getAlto())));
		BossDemonioM.setVelocidadY(2);
		animados.add(BossDemonioM);
		jefeDemonioMLlamado = true;
	}


	// ********************************

	public int getContadorMuertes() {
		return contadorMuertes;
	}

	public void setContadorMuertes(int cm) {
		contadorMuertes = cm;
	}

	public void addContadorMuertes(int cm) {
		contadorMuertes += cm;
	}

	// ********************************

	public void setSucuboMuerto(boolean muerto) {
		jefeSucuboMuerto = muerto;
	}

	public void setGiganteDeHierroMuerto(boolean muerto) {
		jefeGiganteDeHierroMuerto = muerto;
	}

	public void setDemonioMMuerto(boolean muerto) {
		jefeDemonioMMuerto = muerto;
	}

	@Override
	public void setAlexVivo(boolean vivo) {
		AlexVivo = vivo;
	}

	@Override
	public void setJuegoTerminado(boolean juegoT) {
		juegoTerminado = juegoT;
	}

	// ********************************

	public void pausarJuego(){
		if (juegoPausado == true)
			juegoPausado = false;
		else 
			juegoPausado = true;
		
	}
	
}
