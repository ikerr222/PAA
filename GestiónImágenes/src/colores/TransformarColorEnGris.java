package colores;

import imagenes.ColorRGB;

/**
 * @author acade Iker
 * @version v1.0
 * Esta clase permite generar a partir de un color su equivalente en
 *  escala de grises, siguiendo la formula: v = (R+G+B)/3. 
 *  El resultado v se aplica a las 3 componentes del nuevo color.
 *  */


public class TransformarColorEnGris implements ITransformarColor {

	
	
	
	@Override
	public ColorRGB transformar(ColorRGB colorOriginal){
		
		 int colorGris = (colorOriginal.getRed()+colorOriginal.getGreen()+colorOriginal.getBlue())/3;
		 ColorRGB nuevoColor = new ColorRGB(colorGris,colorGris,colorGris);
				 
				 // TODO Auto-generated method stub
		return nuevoColor;
	}

}
