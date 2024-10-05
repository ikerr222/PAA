package colores;

import imagenes.ColorRGB;

public class TransformarColorEnBrillo implements ITransformarColor {

	private final int cantidad; 
	
	
	/**
	 * Crea un nuevo objeto que permitirá generar un nuevo color sumando a 
	 * las componentes RGB un tanto por ciento de su valor.
	 * Precondiciones:
	 * El tanto por ciento debe situarse entre -100 y 100.
	 * @param cantidad El tanto por ciento de incremento/decremento sobre el valor de cada componente.
	 * @throws IllegalArgumentException  Si el porcentaje indicado está fuera de rango.
	 * 
	 * */
	public TransformarColorEnBrillo (int cantidad) {
		
		if(cantidad<-100 || cantidad>100) {
	    throw new IllegalArgumentException("[ERROR] El tanto por ciento debe situarse entre -100 y 100");
		}
	    this.cantidad = cantidad;
	}
	
	@Override
	public ColorRGB transformar(ColorRGB colorOriginal) {
		int r;
		int g;
		int b;
		
		int pr = (colorOriginal.getRed()*cantidad/100)+colorOriginal.getRed();
		if (pr < 0) {
			r=0;
		}else if (pr > 255) {
			r=255;	
		}else {
			r=pr;
		}
		
		int pg = (colorOriginal.getGreen()*cantidad/100)+colorOriginal.getGreen();
		if (pg < 0) {
			g=0;
		}else if (pg > 255) {
			g=255;	
		}else {
			g=pg;
		}
		
		int pb = (colorOriginal.getBlue()*cantidad/100)+colorOriginal.getBlue();
		if (pb < 0) {
			b=0;
		}else if (pb > 255) {
			b=255;	
		}else {
			b=pb;
		}
		return new ColorRGB (r,g,b);

	}

}
