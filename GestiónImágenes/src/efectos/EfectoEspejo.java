package efectos;

import imagenes.*;

public class EfectoEspejo extends Efecto{

	private boolean tipo;
	
	public EfectoEspejo (String nombre, ImagenRGB imagen, boolean tipo) {
		super(nombre, imagen);
		this.tipo = tipo;  //tipo==true --> espejado vertical
		                   //tipo==false --> espejado horizontal
	}
	
	
	public void setTipo (boolean tipo) {
		this.tipo = tipo;
	}
	
	
	@Override
	public void aplicar() {
		aplicado = true;
		//fila:0 columna:0
		//fila:0 columna:1
		//fila:0 columna:2
		
		if(tipo == true) {
			//Recorre las filas
			for (int i=0; i<imagenProcesada.getAlto(); i++) {
				//fila 0
				for (int k=0; k<imagenProcesada.getAncho(); k++) {
					//fila 0 --> columna 0
					//fila 0 --> columna 1
					//fila 0 --> columna 2
					imagenProcesada.setPixel(i, imagen.getAncho()-k-1, imagen.getPixel(i, k));
				}
			}
		}else {
			//Recorre las filas
			for (int i=0; i<imagenProcesada.getAlto(); i++) {
				//fila 0
				for (int k=0; k<imagenProcesada.getAncho(); k++) {
					//fila 0 --> columna 0
					//fila 0 --> columna 1
					//fila 0 --> columna 2
					imagenProcesada.setPixel(imagen.getAlto()-i-1, k, imagen.getPixel(i, k));
				}
			}
			
		}
		
	}
	
}
