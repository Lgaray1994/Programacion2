package clases;

import java.util.HashMap;

import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Portales  extends ObjetoJuego {

	private int vidas;
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





		public Portales(int x, int y, String nombreImagen, int velocidad, int vidas,String animacionActual) {
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
			
			
			
			//idle animation
			Rectangle coordenadasPortal[] = 
				{
				new Rectangle(6,7,71,499),
				new Rectangle(77,7,70,450),
				
				
				};
			Animacion animacionPortal = new Animacion (0.38,coordenadasPortal);
			animaciones.put("portales2",animacionPortal);
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
			
			//expresionBoleana?verdadero:falso
			//(direccion= -1?anchoImagen:0)
			
			
			//graficos.drawImage(new Image(nombreImagen),x,y);
			//aqui es donde se cambia la imagen
			graficos.drawImage(Juego.imagenes.get("portales"),xImagen,yImagen,anchoImagen,altoImagen,x+ (direccion== -1?anchoImagen:0) ,y,direccion*anchoImagen,altoImagen);//xImage,Yimage,anchoFragmento,altoFragmento,Xpintar,Y pintar,anchoPintar,altoPintar
			
			//quitar los cuadros para el producto final
			//graficos.setStroke(Color.RED);
			//graficos.strokeRect(x, y, anchoImagen-10, altoImagen);
			
		}
		





		public String getAnimacionActual() {
			return animacionActual;
		}





		public void setAnimacionActual(String animacionActual) {
			this.animacionActual = animacionActual;
		}
		
		





		@Override
		public void mover() {
			// TODO Auto-generated method stub
			
		}

}
