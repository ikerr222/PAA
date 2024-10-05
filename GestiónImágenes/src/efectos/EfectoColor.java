package efectos;

import colores.ITransformarColor;
import imagenes.ColorRGB;
import imagenes.ImagenRGB;

public class EfectoColor extends Efecto {

	ITransformarColor tipo;
	
	public EfectoColor (String nombre, ImagenRGB imagen, ITransformarColor tipo) {
		super (nombre, imagen);
		this.tipo = tipo;
	}

	@Override
	public void aplicar() {
		aplicado = true;
		ColorRGB nuevoColor;
		for (int i=0; i<imagenProcesada.getAlto(); i++) {
			//Recorremos columnas
			for (int k=0; k<imagenProcesada.getAncho(); k++) {
				nuevoColor = tipo.transformar(imagen.getPixel(i, k));
				imagenProcesada.setPixel(i, k, nuevoColor);
			}
		}
	}
	
		
}
