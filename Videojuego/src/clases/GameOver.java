package clases;

import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameOver extends ObjetoJuego {
	
	private int cantidadVidas;
	private boolean capturado = false;
	

	public GameOver(int x, int y, String nombreImagen, int velocidad, int cantidadVidas) {
		super(x, y, nombreImagen, velocidad);
		this.setCantidadVidas(cantidadVidas);
		
		this.ancho = (int)Juego.imagenes.get("over").getWidth();
		this.alto = (int)Juego.imagenes.get("over").getHeight();
		
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		// TODO Auto-generated method stub
		if (this.capturado) 
		{
			return;
		}
		else {
		
		
		graficos.drawImage(Juego.imagenes.get("over"), this.x, this.y);
		graficos.setStroke(Color.RED);
		graficos.strokeRect(x, y, ancho, alto);
		}
		
	
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
		
	}
	
	public Rectangle obtenerRectangulo() 
	{
		return new Rectangle(x, y, ancho, alto);
	
	
	}

	public int getCantidadVidas() {
		return cantidadVidas;
	}

	public void setCantidadVidas(int cantidadVidas) {
		this.cantidadVidas = cantidadVidas;
	}

	public boolean isCapturado() {
		return capturado;
	}

	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}

}
