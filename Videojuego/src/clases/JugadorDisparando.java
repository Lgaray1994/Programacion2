package clases;

import java.util.HashMap;

import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class JugadorDisparando extends ObjetoJuego {
	
	
		private int vidas;
		private static int win= 0;
		private HashMap<String, Animacion>animaciones;//arraylist de indices textos en vez de numeros
			private int xImagen;
			private int yImagen;
			private int anchoImagen;
			private int altoImagen;
			private String animacionActual;
			private int direccion =1;
			
			
			
			
			public int getDireccion() {
				return direccion;
			}





			public void setDireccion(int direccion) {
				this.direccion = direccion;
			}





			public JugadorDisparando(int x, int y, String nombreImagen, int velocidad, int vidas,String animacionActual) {
				super(x, y, nombreImagen, velocidad);
				this.vidas = vidas;
				this.animacionActual = animacionActual;
				
				
				animaciones = new HashMap<String,Animacion>();
				
				inicializarAnimaciones();
				
			}
			
			
			
			

			public int getVidas() {
				return vidas;
			}

			public void setVidas(int vidas) {
				this.vidas = vidas;
			}
			
			public void inicializarAnimaciones() 
			{
				//ArrayList<Rectangle>coordenadasCorrer= new ArrayList<Rectangle>();
				//coordenadasCorrer.add(new Rectangle(13,228,86,297));//14,228,86,297
				
				Rectangle coordenadasCorrerIzq[] = 
				{
				new Rectangle(98,495,84,63),
				//new Rectangle(218,220,46,57),
				
				
				};
				
				Animacion animacionCorrerIzq= new Animacion(0.012,coordenadasCorrerIzq);
				animaciones.put("correIzq",animacionCorrerIzq);
				
				
				
				Rectangle coordenadasCorrerDere[] = 
					{
					new Rectangle(206,496,92,65),
					//new Rectangle(218,220,46,57),
					
					
					};
					
					Animacion animacionCorrerDere= new Animacion(0.012,coordenadasCorrerDere);
					animaciones.put("correrD",animacionCorrerDere);
				
				
				
				Rectangle coordenadasCorrerA[] = 
					{
					new Rectangle(40,497,51,63),
					//new Rectangle(44,310,52,62),
					
					
					
					};
					
					Animacion animacionCorrerA= new Animacion(0.012,coordenadasCorrerA);
					animaciones.put("correrA",animacionCorrerA);
				
					
					
					
					Rectangle coordenadasCorrerArriba[] = 
						{
						new Rectangle(302,500,58,61),
						//new Rectangle(305,316,48,61),
						
						
						
						};
						
						Animacion animacionCorrerArriba= new Animacion(0.012,coordenadasCorrerArriba);
						animaciones.put("correrArriba",animacionCorrerArriba);
				
			
				
				//idle animation
				Rectangle coordenadasDescanso[] = 
					{
					
						new Rectangle(43,408,50,60),
						new Rectangle(122,408,62,60),
						new Rectangle(205,408,65,65)
							
							
					
					};
				Animacion animacionDescanso = new Animacion (0.38,coordenadasDescanso);
				animaciones.put("descanso",animacionDescanso);
				
				
				Rectangle coordenadasInactivo[] = 
					{
					
						new Rectangle(400,0,13,12),
						
							
							
					
					};
				Animacion animacionInactivo = new Animacion (0.38, coordenadasInactivo);
				animaciones.put("Inactivo",animacionInactivo);
			}

			public void calcularFrame(double t) 
			{
				Rectangle coordenadas =  animaciones.get(animacionActual).calcularFrameActual(t);
				this.xImagen= (int)coordenadas.getX();
				this.yImagen= (int)coordenadas.getY();
				this.altoImagen= (int)coordenadas.getHeight();
				this.anchoImagen= (int)coordenadas.getWidth();
				
				
			
			
			}
			
			
			public Rectangle obtenerRectangulo() 
			{
				return new Rectangle(x, y, (direccion*anchoImagen)+20, altoImagen+20);
			
			
			}
			
			
			
			//se ejecuta por cada iteracion de ciclo de juego
			@Override
			public void pintar(GraphicsContext graficos) 
			{
				
				//expresionBoleana?verdadero:falso
				//(direccion= -1?anchoImagen:0)
				
				
				//graficos.drawImage(new Image(nombreImagen),x,y);
				//aqui es donde se cambia la imagen
				graficos.drawImage(Juego.imagenes.get("doom"),xImagen,yImagen,anchoImagen,altoImagen,x+ (direccion== -1?anchoImagen:0) ,y,direccion*anchoImagen,altoImagen);//xImage,Yimage,anchoFragmento,altoFragmento,Xpintar,Y pintar,anchoPintar,altoPintar
				
				//quitar los cuadros para el producto final
				//graficos.setStroke(Color.RED);
				//graficos.strokeRect(x, y, anchoImagen+20, altoImagen+20);
				
			}
			//se ejecuta por cada iteracion de ciclo de juego
			@Override
			public void mover() 
			{
				
				if (x<0) 
				{
					x=20;
				}
				
				if (y <0) 
				{
					y=20;
				}
				
				
				if (x>800) 
				{
					x=80;
				}
				
				if (y>400) 
				{
					y=380;
				}
				
				if(Juego.derecha) 
				{
					x +=velocidad;
					
				}
				if(Juego.izquierda) 
				{
					x -=velocidad;
					
				}
				if(Juego.arriba) 
				{
					y -=velocidad;
					
				}
				if(Juego.abajo) 
				{
					y +=velocidad;
					
				}
			}





			public String getAnimacionActual() {
				return animacionActual;
			}





			public void setAnimacionActual(String animacionActual) {
				this.animacionActual = animacionActual;
			}
			
			public void verificarColisionesItem(Item item) 
			{
				if (!item.isCapturado() && this.obtenerRectangulo().getBoundsInLocal().intersects(item.obtenerRectangulo().getBoundsInLocal()))
					
							
				{
					this.vidas += item.getCantidadVidas();
					
					item.setCapturado(true);
					
					//System.out.println("estan colisionando");
					
					}
				
				
					
					
			
			}
			
			public void verificarColisionesPared(ParedContacto paredContacto) 
			{
				//if (!item.isCapturado() && this.obtenerRectangulo().getBoundsInLocal().intersects(item.obtenerRectangulo().getBoundsInLocal()))
					if (this.obtenerRectangulo().getBoundsInLocal().intersects(paredContacto.obtenerRectangulo().getBoundsInLocal()))
							
				{
					this.vidas -= paredContacto.getCantidadVidas();
					
					paredContacto.setCapturado(true);
					
					System.out.println("estan colisionando");
					
					}
				
				
			
			}
			
			
			public void verificarColisionesEnemigo1(Enemigos enemigos) 
			{
					
				
				if (!enemigos.isCapturado() && this.obtenerRectangulo().getBoundsInLocal().intersects(enemigos.obtenerRectangulo().getBoundsInLocal()))
					
				{
					enemigos.setCapturado(true);
					enemigos.setCantidadVidas(0);
					win += 1;
					//System.out.println("estan colisionando");
					}
			
			}
			
			
			
			public void verificarColisionesEnemigo2(Enemigos2 enemigos2) 
			{
					
				
				if (!enemigos2.isCapturado() && this.obtenerRectangulo().getBoundsInLocal().intersects(enemigos2.obtenerRectangulo().getBoundsInLocal()))
					
				{
					enemigos2.setCapturado(true);
					enemigos2.setCantidadVidas(0);
					win += 1;
					
					//System.out.println("estan colisionando");
					}
			
			}
			
			
			public void verificarColisionesEnemigo3(Enemigos3 enemigos3) 
			{
					
				
				if (!enemigos3.isCapturado() && this.obtenerRectangulo().getBoundsInLocal().intersects(enemigos3.obtenerRectangulo().getBoundsInLocal()))
					
				{
					enemigos3.setCapturado(true);
					win += 1;
					enemigos3.setCantidadVidas(0);
					//System.out.println("estan colisionando");
					}
			
			}
			
			
			
			public void verificarColisionesEnemigo4(Enemigos4 enemigos4) 
			{
					
				
				if (!enemigos4.isCapturado() && this.obtenerRectangulo().getBoundsInLocal().intersects(enemigos4.obtenerRectangulo().getBoundsInLocal()))
					
				{
					enemigos4.setCapturado(true);
					
					win += 1;
					enemigos4.setCantidadVidas(0);
					
					//System.out.println("estan colisionando");
					}
			
			}





			public static int getWin() {
				return win;
			}





			public void setWin(int win) {
				this.win = win;
			}
				
				
			

}
