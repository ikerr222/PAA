package efectos;

import imagenes.ImagenRGB;

public abstract class Efecto {
	
	private final String nombre;
	protected final ImagenRGB imagen;  //Imagen original
	protected ImagenRGB imagenProcesada;  
	protected boolean aplicado;
	
	public Efecto (String nombre, ImagenRGB imagen) {
		this.nombre = nombre;
		this.imagen = imagen;  //Imagen original
		imagenProcesada = new ImagenRGB(imagen);  
		aplicado = false;
	}
	
	
	public String getNombre () {
		return nombre;
	}
	
	
	public ImagenRGB getImagen() {
		return imagenProcesada;
	}
	
	public abstract void aplicar();
	
	
	public void deshacer() throws EfectoException {
		if (aplicado == false) {
			throw new EfectoException ("ERROR: Se ha intentado deshacer un efecto no aplicado");
		}
		imagenProcesada = new ImagenRGB(imagen);
		aplicado = false;
	}
	
	
}

