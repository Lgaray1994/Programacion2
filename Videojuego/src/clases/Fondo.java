package clases;

import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;

public class Fondo extends ObjetoJuego{
	private String nombreImagen2;
	private int x2;

	public Fondo(int x, int y, String nombreImagen,String nombreImagen2,  int velocidad) {
		super(x, y, nombreImagen, velocidad);
		// TODO Auto-generated constructor stub
		this.nombreImagen2 = nombreImagen2;
		
		this.ancho = (int)Juego.imagenes.get("fondo1").getWidth();
		this.alto = (int)Juego.imagenes.get("fondo1").getHeight();
		this.x2 = this.ancho + this.x; 
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(this.nombreImagen), this.x, this.y);
		
		graficos.drawImage(Juego.imagenes.get(this.nombreImagen2), this.x2  , this.y);
		
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
		
		if (x >= -1 * ancho) 
		{
			x= 0;
		}
		
		
		if (x <= -1 * ancho) 
		{
			x= ancho;
		}
		
		if (x2 <= -1 * ancho) 
		{
			x2= ancho;
		}
		
		
		if(Juego.derecha) 
		{
			x -=velocidad*2;
			x2 -=velocidad*2;
			
		}
		if(Juego.izquierda) 
		{
			x +=velocidad;
			x2 +=velocidad;
			
		}
		
	}
	

}
