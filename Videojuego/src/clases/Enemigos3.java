package clases;

import java.util.HashMap;

import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Enemigos3 extends ObjetoJuego {
	
	private int cantidadVidas;
	private boolean capturado = false;
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





		public Enemigos3  (int x, int y, String nombreImagen, int velocidad, int cantidadVidas,String animacionActual) {
			super(x, y, nombreImagen, velocidad);
			this.cantidadVidas = cantidadVidas;
			this.animacionActual = animacionActual;
			
			
			animaciones = new HashMap<String,Animacion>();
			
			inicializarAnimaciones();
			
		}
		
		
		
		

		
		
		public int getCantidadVidas() {
			return cantidadVidas;
		}





		public void setCantidadVidas(int cantidadVidas) {
			this.cantidadVidas = cantidadVidas;
		}
		
		public String getAnimacionActual() {
			return animacionActual;
		}





		public void setAnimacionActual(String animacionActual) {
			this.animacionActual = animacionActual;
		}




		public void inicializarAnimaciones() 
		{
			//ArrayList<Rectangle>coordenadasCorrer= new ArrayList<Rectangle>();
			//coordenadasCorrer.add(new Rectangle(13,228,86,297));//14,228,86,297
			
			Rectangle coordenadasCorrerDere[] = 
			{
			new Rectangle(331,41,61,80),
			new Rectangle(324,150,63,77),
			new Rectangle(324,257,58,80),
			
			
			
			};
			
			Animacion animacionCorrerDere= new Animacion(0.012,coordenadasCorrerDere);
			animaciones.put("correrD",animacionCorrerDere);
			
			
			
			Rectangle coordenadasCorrerA[] = 
				{
			
						new Rectangle(59,46,54,79),
						new Rectangle(58,151,64,82),
						new Rectangle(59,256,54,84),
						
				
				
				};
				
				Animacion animacionCorrerA= new Animacion(0.012,coordenadasCorrerA);
				animaciones.put("correrA",animacionCorrerA);
			
				
				
				
				Rectangle coordenadasCorrerArriba[] = 
					{
					
					
					
					new Rectangle(251,46,56,76),
					new Rectangle(249,151,59,75),
					new Rectangle(254,259,60,75),
					
					
					};
					
					Animacion animacionCorrerArriba= new Animacion(0.012,coordenadasCorrerArriba);
					animaciones.put("correrArriba",animacionCorrerArriba);
			
		
			
			//idle animation
			Rectangle coordenadasDescanso[] = 
				{
				
					new Rectangle(59,46,54,79),
					new Rectangle(251,46,56,76),
					new Rectangle(331,41,61,80),
						
				
				};
			Animacion animacionDescanso = new Animacion (0.38,coordenadasDescanso);
			animaciones.put("descanso",animacionDescanso);
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
			return new Rectangle(x, y, (direccion*anchoImagen)-10, altoImagen);
		
		
		}
		
		
		
		//se ejecuta por cada iteracion de ciclo de juego
		@Override
		public void pintar(GraphicsContext graficos) 
		{
			if (this.capturado) 
			{
				return;
			}
			else {
			
			//expresionBoleana?verdadero:falso
			//(direccion= -1?anchoImagen:0)
			
			
			//graficos.drawImage(new Image(nombreImagen),x,y);
			//aqui es donde se cambia la imagen
			graficos.drawImage(Juego.imagenes.get("enemigo3"),xImagen,yImagen,anchoImagen,altoImagen,x+ (direccion== -1?anchoImagen:0) ,y,direccion*anchoImagen,altoImagen);//xImage,Yimage,anchoFragmento,altoFragmento,Xpintar,Y pintar,anchoPintar,altoPintar
			
			//quitar los cuadros para el producto final
			//graficos.setStroke(Color.RED);
			//graficos.strokeRect(x, y, anchoImagen-10, altoImagen);
			}
			
		}
		//se ejecuta por cada iteracion de ciclo de juego
		@Override
		public void mover() 
		{
			
			if (x<0) 
			{
				x=800;
			}
			
			if (y <0) 
			{
				y=400;
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
				x +=velocidad;
				
			}
			if(Juego.arriba) 
			{
				y +=velocidad;
				
			}
			if(Juego.abajo) 
			{
				y -=velocidad;
				
			}
		}





		public boolean isCapturado() {
			return capturado;
		}





		public void setCapturado(boolean capturado) {
			this.capturado = capturado;
		}





		
		
		/*public void verificarColisionesItem(Item item) 
		{
			if (!item.isCapturado() && this.obtenerRectangulo().getBoundsInLocal().intersects(item.obtenerRectangulo().getBoundsInLocal()))
				
						
			{
				this.cantidadVidas += item.getCantidadVidas();
				
				item.setCapturado(true);
				
				//System.out.println("estan colisionando");
				
				}
			
			
				
				
		
		}*/
		
	
		
		
		
			
			
		

}

