package Implementacion;


import java.awt.Font;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import clases.Enemigos;
import clases.Enemigos2;
import clases.Enemigos3;
import clases.Enemigos4;
import clases.Fondo;
import clases.GameOver;

import clases.Item;

import clases.JugadorAnimado;
import clases.JugadorDisparando;
import clases.ParedContacto;
import clases.ParedesAnimadas;
import clases.Portales;
import clases.Puntuacion;
import clases.Salud;
import clases.Tile;
import clases.Win;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Juego extends Application {
	
	private GraphicsContext graficos ;
	private Group root;
	private Scene escena;
	private Fondo fondo;
	//private Jugador jugador;
	
	private String score  ;
	
	
	//___________________________________________________________________________
	private JugadorAnimado jugadorAnimado; 
	
	private JugadorDisparando jugadorDisparando ;
	
	private Salud salud;
	private Portales portales;
	private ParedesAnimadas paredesAnimadas;
	private GameOver gameOver;
	
	private Win win;
	
	
	private Puntuacion puntuacion;
	
	/////////////////////////////////////////////
	private Enemigos enemigos;
	
	private Enemigos2 enemigos2;
	private Enemigos3 enemigos3;
	private Enemigos4 enemigos4;
	
	private Enemigos enemigos5;
	private Enemigos2 enemigos6;
	
	//////////////////////////////////////
	
	public static boolean arriba;
	public static boolean abajo;
	public static boolean izquierda;
	public static boolean derecha;
	public static HashMap<String, Image> imagenes;
							//indice
	
	private Item item;
	private Item item2;
	private Item item3;
	
	private ParedContacto paredContacto;
	
	
	
	//private Tile tile;
	private ArrayList<Tile>tiles;
	private int tilemap[][]= {
			{1,9,10,11,10,11,10,11,10,11,10,11,10},
			{2,8,6,5,7,8,7,5,5,5,5,8,5},
			{2,8,6,5,8,0,0,5,8,5,6,5,5},
			{2,7,8,5,7,0,0,6,5,7,5,6,5},
			{2,5,7,6,5,8,5,8,5,5,5,5,5},
			{2,8,5,8,7,8,6,5,5,6,8,7,5},
			{4,12,13,14,13,14,13,14,13,14,13,14,13},
			
	};
	
	
	//______________________________________________________________
	
	public static void main(String[] args) {
		launch(args);
		
		
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		
		
/////////////////////////////////////////////////////////////////////////////////////////////
		
		/*Puntuacion puntuaciones = new Puntuacion(0,0,"",0,"100");
		
		try {
		ObjectOutputStream flujoSalida = new ObjectOutputStream(new FileOutputStream("MaxScore2.txt"));
		flujoSalida.writeObject(puntuaciones);
		
		flujoSalida.close();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		
		
		
		//________________________________________
		
		try {
			ObjectInputStream flujoSalida= new 	ObjectInputStream(new FileInputStream("MaxScore2.txt"));
			try {
				while(true) {
					Puntuacion puntuaciones1 = (Puntuacion)flujoSalida.readObject();
					System.out.println(puntuaciones1);
					
				}
				} catch (EOFException e) {//end of file
					// TODO Auto-generated catch block
					System.out.println("Fin del Archivo");
				}
			flujoSalida.close();
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	}

	@Override
	public void start(Stage ventana) throws Exception {
		// TODO Auto-generated method stub
		 
		inicializarComponentes();
		gestionEventos();
		gestionSalud();
		
		
		//pintar();
		
		
		ventana.setScene(escena);
		ventana.setTitle("Doom Java");
		ventana.show();
		cicloJuego();
		
		
		
	}
	
	public void cicloJuego() 
	{
		long tiempoInicial= System.nanoTime();
		AnimationTimer animationTimer = new AnimationTimer()
				{
					//60fps aprox
					@Override
					public void handle(long tiempoActual) {
						// TODO Auto-generated method stub
						//double t = (tiempoActual - tiempoInicial )/1000000000;
						double t = (tiempoActual - tiempoInicial )/100000000;
						System.out.println(t);
						actualizarEstado(t) ;
						pintar();
						gestionSalud();
						
					}
			
				};
				animationTimer.start();
				
	}
	
	
	public void actualizarEstado(double t) 
	{
		
		jugadorAnimado.verificarColisionesItem(item);
		
		
		
		
		
		jugadorAnimado.verificarColisionesEnemigo1(enemigos);
		jugadorAnimado.verificarColisionesEnemigo2(enemigos2);
		jugadorAnimado.verificarColisionesEnemigo3(enemigos3);
		jugadorAnimado.verificarColisionesEnemigo4(enemigos4);
		jugadorAnimado.verificarColisionesEnemigo1(enemigos5);
		jugadorAnimado.verificarColisionesEnemigo2(enemigos6);
		
		//jugadorDisparando.verificarColisionesEnemigo1(enemigos);
		
		
		jugadorAnimado.verificarColisionesPared(paredContacto);
		
		
		
		paredesAnimadas.calcularFrame(t);
		paredesAnimadas.mover();
		
		portales.calcularFrame(t);
		portales.mover();
		
		salud.calcularFrame(t);
		salud .mover();
		
		
		jugadorDisparando.calcularFrame(t);
		jugadorDisparando.mover();
		
		jugadorAnimado.calcularFrame(t);
		jugadorAnimado.mover();
		fondo.mover();
		
		
	///////////////////////////////////////////////	
		enemigos.calcularFrame(t);
		enemigos.mover();
	
		
		enemigos2.calcularFrame(t);
		enemigos2.mover();
		
		
		enemigos3.calcularFrame(t);
		enemigos3.mover();
		
		enemigos4.calcularFrame(t);
		enemigos4.mover();
		
	enemigos5.calcularFrame(t);
	enemigos5.mover();
		 
	enemigos6.calcularFrame(t);
	enemigos6.mover();	
		
	//////////////////////////////////////////////////	
		
	}
	
	
	public void inicializarComponentes() 
	{
		imagenes = new HashMap<String,Image>();
		cargarImagenes();
		
		
		jugadorAnimado = new JugadorAnimado(60,150,"doom",9,200,"descanso");
		
		jugadorDisparando = new JugadorDisparando (60,150,"doom",9,80,"descanso");
		
	//////////////////////////////////////////////	
		enemigos= new Enemigos(220,200,"enemigo1",5,1,"descanso");
		enemigos5= new Enemigos(400,150,"enemigo1",6,1,"descanso");
		enemigos6= new Enemigos2(50,300,"enemigo2",6,1,"descanso");
		
	
		
		enemigos2= new Enemigos2(500,300,"enemigo2",7,1,"descanso");
		
		enemigos3= new Enemigos3(360,50,"enemigo3",8,1,"descanso");
		
		enemigos4= new Enemigos4(700,100,"enemigo4",8,1,"descanso");
		
	////////////////////////////////////////////////////////////	
		
		paredesAnimadas = new ParedesAnimadas(0,0,"fuego",9,0,"Gif");
		
		portales = new Portales(780,0,"portales",9,0,"portales2");
		
		
		
		salud = new Salud(400,5,"salud",9,0,"fine");
		
		
		fondo= new Fondo(0,0,"fondo1","fondo", 15);
		
		gameOver = new GameOver(0,0,"over",0,0);
		
		win = new Win(0,0,"win",0,0);
		
		
		//*********************************************************************************************
		try 
		{
			ObjectInputStream flujoInicioJuego = new ObjectInputStream(new FileInputStream("MaxScore2.txt"));
		
			while (true) 
			{
				//inventarios.add((Inventario)flujoEntrada.readObject());
				
				
				Puntuacion puntuacionInicioJuego = (Puntuacion)flujoInicioJuego.readObject();
				
				System.out.println(puntuacionInicioJuego);
				
				puntuacion = new Puntuacion(0,0,"",0,puntuacionInicioJuego.getPunt_max());

			}
		
		} 
		
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Fin del archivo");
			System.out.println(e.toString());
			//puntuacion = new Puntuacion(0,0,"",0,"95");
		}
		
		
		
		//*********************************************************************************************
		
	
		inicializarTile();
		
		
		//tile = new Tile(0,0,"tilemap",0,420,490,70,70);
		
		
		
		
		item= new Item(200,100,"item",0,20);
		
		paredContacto = new ParedContacto(0,10,"fuego",0,5);
		
		item2= new Item(400,180,"item",0,30);
		item3= new Item(300,280,"item",0,100);
		
		
		
		
		root = new Group();
		escena = new Scene(root,850,450);
		Canvas lienzo = new Canvas(850,450);
		root.getChildren().add(lienzo);
		graficos = lienzo.getGraphicsContext2D();
		graficos.fillRect(0, 0, 100, 100);
						//x      y     ancho alto
		
	}
	
	public void inicializarTile() 
	{
		tiles = new ArrayList<Tile>();
		for(int i=0; i <tilemap.length;i++) 
		{
			for(int j=0; j <tilemap[i].length;j++) 
			{
				if(tilemap[i][j] != 0) 
				{
					this.tiles.add( new Tile(tilemap[i][j],j*70,i*70,"tilemap",0,70,70));
				}
				
			}
		}
		
	}
	
	
	
	
	public void cargarImagenes()
	{
		
		imagenes.put("doom", new Image("Sprite animations - doom guy.png"));
		imagenes.put("salud", new Image("salud.png"));
		imagenes .put("fondo1",new Image ("fondo.jpg"));
		imagenes .put("fondo",new Image ("fondo-2.jpg"));
		imagenes .put("tilemap",new Image ("Tilemap.png"));
		//////////////////////////////////////////////////
		
		imagenes.put("enemigo1", new Image("Sprite animations - soldier .png"));
		imagenes.put("enemigo2", new Image("Sprite animations - revenant.png"));
		imagenes.put("enemigo3", new Image("Sprite animations - baron.png"));
		imagenes.put("enemigo4", new Image("Sprite animations - cyberdemon.png"));
		
		
		//////////////////////////////////////////////////
		
		imagenes.put("fuego",new Image("barrera de fuego.png"));
		imagenes.put("portales",new Image("portales.png")); 
		imagenes.put("over",new Image("GameOver.png")); 
		imagenes.put("ganar",new Image("win.png"));
		
		
		
		imagenes.put("item",new Image("item.png"));
	}
	
	
	public void pintar() 
	{
		fondo.pintar(graficos);
		
		
		for(int i=0; i<tiles.size();i++) 
		{
			tiles.get(i).pintar(graficos);
		}
		

		//???????????
		paredContacto.pintar(graficos);
		paredesAnimadas.pintar(graficos);
		portales.pintar(graficos);
		jugadorAnimado.pintar(graficos);
		puntuacion.pintar(graficos);
		
		jugadorDisparando.pintar(graficos);
		///////////////////////////////////////
		enemigos.pintar(graficos);
		enemigos2.pintar(graficos);
		enemigos3.pintar(graficos);
		enemigos4.pintar(graficos);
		enemigos5.pintar(graficos);
		enemigos6.pintar(graficos);
		
		///////////////////////////////////////
		salud.pintar(graficos);
		
		item.pintar(graficos);
		
		
		//quitar al final
		graficos.fillText("SPACE TO SHOOT", 300, 35);
		
		///////////////////////////////////////-----------------------------------------------------------
		graficos.fillText("vidas:  "+jugadorAnimado.getVidas(), 300, 50);
		graficos.fillText("La puntuacion maxima "+puntuacion.getPunt_max(),550,35);
		
		//graficos.fillText("puntuacion maxima "+score,550,35);
		
		///////////////////////////////////////////-----------------------------------------------------------
	}
	
	
	public void vida1() 
	{
		
		
		item2.pintar(graficos);
		jugadorAnimado.verificarColisionesItem(item2);
		
		
		
	}
	
	public void vida2() 
	{
		
		
		item3.pintar(graficos);
		jugadorAnimado.verificarColisionesItem(item3);
		
		
		
	}
	
	
	
	public void over() 
	{
		
		gameOver.pintar(graficos);
		
		
		
	}
	
	public void win() 
	{
		
		
		
//************************************************************************************************************************************
		//jugadorAnimado.getVidas()
		//Puntuacion puntuaciones1 = new Puntuacion(0,0,"",0,puntuacion.getPunt_max());
		//score= String.valueOf(jugadorAnimado.getVidas());
		//puntuacion.setPunt_max(score);
		///score = puntuacion.getPunt_max();
		System.out.println("La puntuacion maxima inicial "+puntuacion.getPunt_max());
		
		if (jugadorAnimado.getVidas() > Integer.parseInt(puntuacion.getPunt_max()) ) 
		{
			
			
			
//***************************************************
			score= String.valueOf(jugadorAnimado.getVidas());
			
			Puntuacion puntuaciones1 = new Puntuacion(0,0,"",0,score);
			
			puntuacion.setPunt_max(score);
			
			
			
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			//score= String.valueOf(jugadorAnimado.getVidas());
			//puntuacion.setPunt_max(score);
			
			try {
				ObjectOutputStream flujoSalida= new ObjectOutputStream(new FileOutputStream("MaxScore2.txt"));
				//inventarios.add((Inventario)flujoEntrada.readObject());
				//flujoSalida.writeObject(inventarios.get(i));
				
				
				
				//score= String.valueOf(jugadorAnimado.getVidas());
				
				
				
				
				//Puntuacion puntuaciones1 = new Puntuacion(0,0,"",0,score);
				
				
				
				
				flujoSalida.writeObject(puntuaciones1);
				System.out.println(puntuaciones1);
				
				graficos.fillText("puntuacion maxima "+ puntuacion.getPunt_max(),550,35);


				flujoSalida.close();

				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			
			//***************************************************
			
			
			try {
				ObjectInputStream flujoEntrada = new ObjectInputStream(new FileInputStream("MaxScore2.txt"));
			
			while (true) 
			{
				//inventarios.add((Inventario)flujoEntrada.readObject());
				
				
				Puntuacion puntuaciones = (Puntuacion)flujoEntrada.readObject();
				
				System.out.println(puntuaciones);
				
				System.out.println(puntuacion);
				
				
				
			}
			
			} catch (EOFException e) {
				// TODO Auto-generated catch block
				System.out.println("Fin del archivo");
			}
			 catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//********************************************************
		}
		
		win.pintar(graficos);
		//puntuacion.setPunt_max(String.valueOf(jugadorAnimado.getVidas())); posible error
		
		
		graficos.fillText("tu puntuacion es :  "+jugadorAnimado.getVidas(), 250, 35);
		//score =puntuacion.getPunt_max();posible error 2 
		graficos.fillText("La puntuacion maxima "+puntuacion.getPunt_max(),550,35);
		
		System.out.println("vidas" +jugadorAnimado.getVidas());
		
		System.out.println("La puntuacion maxima2 "+puntuacion.getPunt_max());
		
		
		System.out.println("-------------------------------------");
		
		
		
//************************************************************************************************************************************
		
		
		
	}
	
	public void gestionEventos() 
	{
		//escena.setOnKeyPressed(new evento());
		
		
		escena.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			
			public void handle(KeyEvent evento) {
				System.out.println("se presiono la tecla " + evento.getCode());
				switch(evento.getCode().toString()) 
				{
				case "RIGHT":
	
					derecha = true;
					jugadorAnimado.setDireccion(1);
					jugadorAnimado.setAnimacionActual("correrD");
					jugadorDisparando.setAnimacionActual("Inactivo");
					
					
				///////////////////////////
				enemigos.setDireccion(1);
				enemigos.setAnimacionActual("correrD");
				
				
				
				enemigos2.setDireccion(1);
				enemigos2.setAnimacionActual("correrD");
				
				
				enemigos3.setDireccion(1);
				enemigos3.setAnimacionActual("correrD");
				
				
				enemigos4.setDireccion(1);
				enemigos4.setAnimacionActual("correrD");
				
				
				
				
				////////////////////////////
					
				break;
				
				case "LEFT":
					izquierda = true;
					jugadorAnimado.setDireccion(-1);
				
					
					///////////////////////////
					enemigos.setDireccion(-1);
					enemigos.setAnimacionActual("correrD");
					
					
					enemigos2.setDireccion(-1);
					enemigos2.setAnimacionActual("correrD");
					
					
					enemigos3.setDireccion(-1);
					enemigos3.setAnimacionActual("correrD");
					
					enemigos4.setDireccion(-1);
					enemigos4.setAnimacionActual("correrD");
					
					
					
					////////////////////////////
					jugadorAnimado.setAnimacionActual("correrD");
					jugadorDisparando.setAnimacionActual("Inactivo");
					
					break;
				case "UP":
					arriba = true;
					jugadorAnimado.setAnimacionActual("correrArriba");
					jugadorDisparando.setAnimacionActual("Inactivo");
					
					///////////////////////////
		
			enemigos.setAnimacionActual("correrArriba");
			
			enemigos2.setAnimacionActual("correrArriba");
			
			enemigos3.setAnimacionActual("correrArriba");
			
			enemigos4.setAnimacionActual("correrArriba");
			
			////////////////////////////
					
					break;
				case "DOWN":
					abajo = true;
					jugadorAnimado.setAnimacionActual("correrA");
					jugadorDisparando.setAnimacionActual("Inactivo");
					
					
					///////////////////////////
					
					enemigos.setAnimacionActual("correrA");
					
					enemigos2.setAnimacionActual("correrA");
					
					enemigos3.setAnimacionActual("correrA");
					
					enemigos4.setAnimacionActual("correrA");
		
					////////////////////////////
					
					
					break;
				case "SPACE":
					//jugadorAnimado.setVelocidad(30);
					/////// cambiar la foto
					//cambiar la imagen al precionar space
					//jugadorAnimado.setNombreImagen("doom2");
					
					
					if (derecha == true) 
					{
						jugadorAnimado.setAnimacionActual("Inactivo");
						
						jugadorDisparando.setAnimacionActual("correrD");
						jugadorDisparando.verificarColisionesEnemigo1(enemigos);
						jugadorDisparando.verificarColisionesEnemigo2(enemigos2);
						jugadorDisparando.verificarColisionesEnemigo3(enemigos3);
						jugadorDisparando.verificarColisionesEnemigo4(enemigos4);
						jugadorDisparando.verificarColisionesEnemigo1(enemigos5);
						jugadorDisparando.verificarColisionesEnemigo2(enemigos6);
					}
					
					else if (izquierda == true) 
					{
						jugadorAnimado.setAnimacionActual("Inactivo");
						
						jugadorDisparando.setAnimacionActual("correIzq");
						jugadorDisparando.verificarColisionesEnemigo1(enemigos);
						
						jugadorDisparando.verificarColisionesEnemigo2(enemigos2);
						jugadorDisparando.verificarColisionesEnemigo3(enemigos3);
						jugadorDisparando.verificarColisionesEnemigo4(enemigos4);
						jugadorDisparando.verificarColisionesEnemigo1(enemigos5);
						jugadorDisparando.verificarColisionesEnemigo2(enemigos6);
						
					}
					
					else if (arriba == true) 
					{
						jugadorAnimado.setAnimacionActual("Inactivo");
						jugadorDisparando.setAnimacionActual("correrArriba");
						jugadorDisparando.verificarColisionesEnemigo1(enemigos);
						
						
						jugadorDisparando.verificarColisionesEnemigo2(enemigos2);
						jugadorDisparando.verificarColisionesEnemigo3(enemigos3);
						jugadorDisparando.verificarColisionesEnemigo4(enemigos4);
						jugadorDisparando.verificarColisionesEnemigo1(enemigos5);
						jugadorDisparando.verificarColisionesEnemigo2(enemigos6);
					}
					
					else if (abajo == true) 
					{
						jugadorAnimado.setAnimacionActual("Inactivo");
						jugadorDisparando.setAnimacionActual("correrA");
						jugadorDisparando.verificarColisionesEnemigo1(enemigos);
						
						
						jugadorDisparando.verificarColisionesEnemigo2(enemigos2);
						jugadorDisparando.verificarColisionesEnemigo3(enemigos3);
						jugadorDisparando.verificarColisionesEnemigo4(enemigos4);
						jugadorDisparando.verificarColisionesEnemigo1(enemigos5);
						jugadorDisparando.verificarColisionesEnemigo2(enemigos6);
					}
//////////////////////////
					if (derecha == false && izquierda == false && arriba == false && abajo == false ) 
					{
						jugadorAnimado.setAnimacionActual("Inactivo");
						jugadorDisparando.setDireccion(1);
						jugadorDisparando.setAnimacionActual("correrD");
						jugadorDisparando.verificarColisionesEnemigo1(enemigos);
						jugadorDisparando.verificarColisionesEnemigo2(enemigos2);
						jugadorDisparando.verificarColisionesEnemigo3(enemigos3);
						jugadorDisparando.verificarColisionesEnemigo4(enemigos4);
						jugadorDisparando.verificarColisionesEnemigo1(enemigos5);
						jugadorDisparando.verificarColisionesEnemigo2(enemigos6);
					}
					
					
					
				
					/*j
					*/
					break;	
					
					
				}
				
			}

			
			
			
		});
		
		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent evento) {
					// TODO Auto-generated method stub
					switch(evento.getCode().toString()) 
					{
					case "RIGHT":
					/* x += 10;
					 System.out.println("se presiono la tecla " + evento.getCode());
					 System.out.println("X:"+x);*/	
						derecha = false;
						
						jugadorAnimado.setAnimacionActual("descanso");
						
						
						///////////////////////////
						
					enemigos.setAnimacionActual("descanso");
		
					////////////////////////////
						
					break;
					
					case "LEFT":
						izquierda = false;
						
						jugadorAnimado.setAnimacionActual("descanso");
						break;
					case "UP":
						arriba = false;
						jugadorAnimado.setAnimacionActual("descanso");
						break;
					case "DOWN":
						abajo = false;
						jugadorAnimado.setAnimacionActual("descanso");
						break;
					case "SPACE":
						//jugadorAnimado.setVelocidad(15);
						//restablecer la imagen
						//jugadorAnimado.setNombreImagen("DOOM-Eternal.jpg");
						
						jugadorAnimado.setAnimacionActual("descanso");
						jugadorDisparando.setAnimacionActual("Inactivo");
						
						break;	
					
				}
			}
			
		});
		
		
	}
	
	
	public void gestionSalud() 
	{
		//escena.setOnKeyPressed(new evento());
		
		
		
		if(jugadorAnimado.getVidas()>140 && jugadorAnimado.getVidas()<200 ) 
		{
			
			salud.setAnimacionActual("fine");
			
		}
		else
		if(jugadorAnimado.getVidas()>90 && jugadorAnimado.getVidas()<140 ) 
		{
			salud.setAnimacionActual("caution");
			
			vida1() ;
			
		}
		else
		if(jugadorAnimado.getVidas()>0 && jugadorAnimado.getVidas()<90 ) 
		{
			salud.setAnimacionActual("danger");
			
			vida2() ;
			
		}
		
		else
			if(jugadorAnimado.getVidas()<0  ) 
			{
				over() ;
				
			}
		
				
		if(JugadorDisparando.getWin()>5) 
		{
			win() ;
		}
		
		
		
	}
	
	
	
	

}
