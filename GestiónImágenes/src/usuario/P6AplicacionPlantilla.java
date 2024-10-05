package usuario;

import colores.TransformarColorEnBrillo;
import colores.TransformarColorEnGris;
import efectos.Efecto;
import efectos.EfectoColor;
import efectos.EfectoException;
import efectos.EfectoMarco;
import imagenes.ColorRGB;
import imagenes.ImagenRGB;

/**
 * Clase principal para la Pr�ctica 6.
 * El alumno debe realizar la siguiente secuencia de operaciones:
 *   - Leer una imagen de disco y mostrarla por pantalla.
 *   - Incluir un marco de anchura 10 p�xeles.
 *   - En una segunda ejecuci�n cambie este valor por un n�mero negativo y luego
 *     por un ancho muy grande (por ejemplo 1000 p�xeles) y compruebe qu� sucede
 *   - Transformar la imagen a escala de grises.
 *   - Aumentar el brillo de la imagen en un 30%.
 *   - En una segunda ejecuci�n cambie este valor por 130% y compruebe qu� sucede.
 *   - Guardar imagen en disco.
 *   - Deshacer el �ltimo efecto realizado.
 *   - Intentar volver a deshacer el efecto.
 * Para ello complete las l�neas que se indican con la etiqueta "Rellene". En cada
 * espacio designado con esta etiqueta deber� completar o introducir una �nica l�nea
 * de c�digo.
 * Tambi�n debe controlar los errores generados tal y como se recoge en el enunciado
 * de la pr�ctica.
 * @author DTE-UPM
 * @version 1.0
 */
public class P6AplicacionPlantilla {

	public static void main(String[] args) {
	    ImagenRGB imagen;
	    Efecto efecto;
	    String fichero = "MicroHobby.jpg";

	    //Generaci�n de la imagen original.
	    imagen = new ImagenRGB (fichero);                    //Rellene
	    imagen.presentarImagen();

	    try {
	    	//Procesamiento para incluir en la imagen un marco de 10 p�xeles de color rojo.
		    efecto = new EfectoMarco ("Efecto marco", imagen, 10, new ColorRGB(255, 0, 0));                 //Rellene
		    imagen = operarEfecto (efecto, false);             //Rellene

		    //Procesamiento para incluir en la imagen un marco de ancho negativo (y posteriormente de ancho 1000 p�xeles)
		//	efecto = new EfectoMarco ("Efecto marco mal", imagen, 1000, new ColorRGB(255, 0, 0));                                        //Rellene
		//    imagen = operarEfecto (efecto, false);           //Rellene

		    //Procesamiento a color gris.
		    efecto = new EfectoColor ("Imagen en gris", imagen, new TransformarColorEnGris ());               //Rellene
		    imagen = operarEfecto (efecto, false);             //Rellene

		    //Procesamiento aumentando el brillo 30%.
		    efecto = new EfectoColor ("Brillo al 30%", imagen, new TransformarColorEnBrillo(30));                                          //Rellene
		    imagen = operarEfecto (efecto, false);             //Rellene

		    //Procesamiento aumentando el brillo 130%.
		//    efecto = new EfectoColor ("Brillo al 30%", imagen, new TransformarColorEnBrillo(130));									       //Rellene
		//    imagen = operarEfecto (efecto, false);           //Rellene

		    //Guarda resultado en fichero.
		    imagen.escribirImagen("ImagenResultado.bmp");                                                   //Rellene

		    //Deshacer.
		    operarEfecto (efecto, true);												   //Rellene

		    //Deshacer (2).
		    operarEfecto (efecto, true);   
	    	
	    }catch(EfectoException e) {
	    	System.out.println(e.getMessage());
	    	
	    }catch(IllegalArgumentException e) {
	    	System.out.println(e.getMessage());  	
	    }
	    												   //Rellene												   //Rellene
	}


	/**
	 * Este m�todo se encarga de aplicar el efecto indicado, devolviendo la imagen
	 * resultante y mostr�ndola por pantalla.
	 * El m�todo permite aplicar el efecto o deshacer las operaciones previamente
	 * realizadas con el mismo.
	 * @param efecto Efecto a aplicar o deshacer.
	 * @param deshacer Con true deshace el tratamiento implementado con el efecto; false
	 *                 aplica el efecto.
	 * @throws EfectoException 
	 */
	private static ImagenRGB operarEfecto(Efecto efecto, boolean deshacer) throws EfectoException {
	    ImagenRGB imagenResultado;

		if(!deshacer) {
			efecto.aplicar();	                                                       //Rellene
	    }
		else {
			efecto.deshacer();			                                               //Rellene
		}
		imagenResultado = efecto.getImagen();                                 //Rellene
	    imagenResultado.presentarImagen();

		/* Inicialmente el mismo c�digo de la pr�ctica 5 */
	    return imagenResultado;
	}

}
