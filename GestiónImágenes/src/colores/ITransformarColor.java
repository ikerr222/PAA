/**
 * 
 */
package colores;

import imagenes.ColorRGB;

/**
 * @author iker
 *@params colorOriginal
 *@return El nuevo color generado
 */
public interface ITransformarColor {
	
 ColorRGB transformar(ColorRGB colorOriginal);
}
