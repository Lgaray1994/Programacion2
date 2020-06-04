package clases;

import java.io.Serializable;

import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Puntuacion extends ObjetoJuego  {
	private  String Punt_max;

	public Puntuacion(int x, int y, String nombreImagen, int velocidad, String punt_max) {
		super(x, y, nombreImagen, velocidad);
		this.Punt_max = punt_max;
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
		
	}

	public String getPunt_max() {
		return Punt_max;
	}

	public void setPunt_max(String punt_max) {
		Punt_max = punt_max;
	}

	@Override
	public String toString() {
		return "Puntuacion [Punt_max=" + Punt_max + ", x=" + x + ", y=" + y + ", nombreImagen=" + nombreImagen
				+ ", velocidad=" + velocidad + ", ancho=" + ancho + ", alto=" + alto + "]";
	}

	

	
	
	
	
	
	
	
	
	
	

	

}
