package efectos;

import imagenes.*;

public class EfectoMarco extends Efecto {

	private int ancho;
	private ColorRGB color;
	
	public EfectoMarco (String nombre, ImagenRGB imagen, int ancho, ColorRGB color) {
		super (nombre, imagen);
		this.ancho = ancho;
		this.color = color;
		
		if (ancho<0) {
			throw new IllegalArgumentException ("ERROR: el ancho no puede ser menor que 0");
		}
		if (imagen.getAncho() > imagen.getAlto() && imagen.getAlto()/2 < ancho) {
			throw new IllegalArgumentException ("ERROR: imagen.getAlto()/2 < ancho");
			
		}else if (imagen.getAncho() <= imagen.getAlto() && imagen.getAncho()/2 < ancho) {
			throw new IllegalArgumentException ("ERROR: imagen.getAncho()/2 < ancho");
		}
		
		//otra opcion 
				/*if (ancho > imagen.getAncho()/2 || ancho > imagen.getAlto()/2) {
					throw new IllegalArgumentException ("[ERROR] ancho > imagen.getAncho()/2 || ancho > imagen.getAlto()/2");

				}*/
	
	}
	
	public int getAnchura() {
		return ancho;
	}


	public void setAnchura(int ancho) {
		this.ancho = ancho;
		
		if (ancho<0) {
			throw new IllegalArgumentException ("ERROR: el ancho no puede ser menor que 0");
		}
		if (imagen.getAncho() > imagen.getAlto() && imagen.getAlto()/2 < ancho) {
			throw new IllegalArgumentException ("ERROR: imagen.getAlto()/2 < ancho");
			
		}else if (imagen.getAncho() <= imagen.getAlto() && imagen.getAncho()/2 < ancho) {
			throw new IllegalArgumentException ("ERROR: imagen.getAncho()/2 < ancho");
		}
	}


	public ColorRGB getColor() {
		return color;
	}


	public void setColor(ColorRGB color) {
		this.color = color;
	}

	
	@Override
	public void aplicar() {
		//marco superior
		for (int i=0; i<ancho; i++) {
			for (int k=0; k<imagenProcesada.getAncho(); k++) {
				imagenProcesada.setPixel(i, imagen.getAncho()-k-1, color);
			}
		}
		
		//marco inferior
		for (int i=imagenProcesada.getAlto()-ancho; i<imagenProcesada.getAlto(); i++) {
			for (int k=0; k<imagenProcesada.getAncho(); k++) {
				imagenProcesada.setPixel(i, k, color);
			}
		}
		
		//marco derecha
		for (int i=ancho; i<imagenProcesada.getAlto()-ancho; i++) {
			for (int k=imagenProcesada.getAncho()-ancho; k<imagenProcesada.getAncho(); k++) {
				imagenProcesada.setPixel(i, k, color);
			}
		}		
		
		//marco izquierda
		for (int i=0; i<imagenProcesada.getAlto(); i++) {
			for (int k=0; k<ancho; k++) {
				imagenProcesada.setPixel(i, k, color);
			}
		}	
		
		
		
	}


	
}
