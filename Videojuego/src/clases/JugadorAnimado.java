package clases;

import java.util.ArrayList;
import java.util.HashMap;

import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class JugadorAnimado extends ObjetoJuego {
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





	public JugadorAnimado(int x, int y, String nombreImagen, int velocidad, int vidas,String animacionActual) {
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
		
		Rectangle coordenadasCorrerDere[] = 
		{
		new Rectangle(218,220,46,57),
		new Rectangle(220,316,43,60),
		new Rectangle(218,220,46,57),
		new Rectangle(220,316,43,60),
		
		
		};
		
		Animacion animacionCorrerDere= new Animacion(0.012,coordenadasCorrerDere);
		animaciones.put("correrD",animacionCorrerDere);
		
		
		
		Rectangle coordenadasCorrerA[] = 
			{
			new Rectangle(41,215,51,63),
			new Rectangle(44,310,52,62),
			new Rectangle(41,215,51,63),
			new Rectangle(44,310,52,62),
			
			
			};
			
			Animacion animacionCorrerA= new Animacion(0.012,coordenadasCorrerA);
			animaciones.put("correrA",animacionCorrerA);
		
			
			
			
			Rectangle coordenadasCorrerArriba[] = 
				{
				new Rectangle(308,220,48,59),
				new Rectangle(305,316,48,61),
				new Rectangle(308,220,48,59),
				new Rectangle(305,316,48,61),
				
				
				};
				
				Animacion animacionCorrerArriba= new Animacion(0.012,coordenadasCorrerArriba);
				animaciones.put("correrArriba",animacionCorrerArriba);
		
	
		
		//idle animation
		Rectangle coordenadasDescanso[] = 
			{
			new Rectangle(43,408,50,60),
			new Rectangle(122,408,62,60),
			//new Rectangle(122,408,62,60),
			//new Rectangle(43,408,50,60),
			//new Rectangle(43,408,50,60),
			//new Rectangle(205,408,65,65),
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
		graficos.drawImage(Juego.imagenes.get("doom"),xImagen,yImagen,anchoImagen,altoImagen,x+ (direccion== -1?anchoImagen:0) ,y,direccion*anchoImagen,altoImagen);//xImage,Yimage,anchoFragmento,altoFragmento,Xpintar,Y pintar,anchoPintar,altoPintar
		
		//quitar los cuadros para el producto final
		//graficos.setStroke(Color.RED);
		//graficos.strokeRect(x, y, anchoImagen-10, altoImagen);
		
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
		//if (!item.isCapturado() && this.obtenerRectangulo().getBoundsInLocal().intersects(item.obtenerRectangulo().getBoundsInLocal()))
			if (this.obtenerRectangulo().getBoundsInLocal().intersects(enemigos.obtenerRectangulo().getBoundsInLocal()))
					
		{
			this.vidas -= enemigos.getCantidadVidas();
			
			
			
			System.out.println("estan colisionando los adversarios");
			System.out.println("estan colisionando los adversarios");
			System.out.println("estan colisionando los adversarios");
			
			}	
	
	}
		
	public void verificarColisionesEnemigo2(Enemigos2 enemigos2) 
	{
		//if (!item.isCapturado() && this.obtenerRectangulo().getBoundsInLocal().intersects(item.obtenerRectangulo().getBoundsInLocal()))
			if (this.obtenerRectangulo().getBoundsInLocal().intersects(enemigos2.obtenerRectangulo().getBoundsInLocal()))
					
		{
			this.vidas -= enemigos2.getCantidadVidas();
			
			
			
			System.out.println("estan colisionando los adversarios");
			System.out.println("estan colisionando los adversarios");
			System.out.println("estan colisionando los adversarios");
			
			}	
	
	}
	
	
	public void verificarColisionesEnemigo3(Enemigos3 enemigos3) 
	{
		//if (!item.isCapturado() && this.obtenerRectangulo().getBoundsInLocal().intersects(item.obtenerRectangulo().getBoundsInLocal()))
			if (this.obtenerRectangulo().getBoundsInLocal().intersects(enemigos3.obtenerRectangulo().getBoundsInLocal()))
					
		{
			this.vidas -= enemigos3.getCantidadVidas();
			
			
			
			System.out.println("estan colisionando los adversarios");
			System.out.println("estan colisionando los adversarios");
			System.out.println("estan colisionando los adversarios");
			
			}	
	
	}
	
	
	public void verificarColisionesEnemigo4(Enemigos4 enemigos4) 
	{
		//if (!item.isCapturado() && this.obtenerRectangulo().getBoundsInLocal().intersects(item.obtenerRectangulo().getBoundsInLocal()))
			if (this.obtenerRectangulo().getBoundsInLocal().intersects(enemigos4.obtenerRectangulo().getBoundsInLocal()))
					
		{
			this.vidas -= enemigos4.getCantidadVidas();
			
			
			
			System.out.println("estan colisionando los adversarios");
			System.out.println("estan colisionando los adversarios");
			System.out.println("estan colisionando los adversarios");
			
			}	
	
	}
	

}
