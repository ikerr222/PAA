package efectos;

import imagenes.*;

public class EfectoMarcoFoto extends EfectoMarco {
	private static final int ANCHOMARCO=20; //Ancho total del marco que vamos a generar
	private static final int ANCHOMARCO_EXTERIOR=10;
	private static final int ANCHOMARCO_INTERIOR=4;
	private final ColorRGB color;
	
	public EfectoMarcoFoto (String nombre, ImagenRGB imagen, ColorRGB color) {
		super(nombre, imagen, ANCHOMARCO, color);
		this.color=color;
	}
	
	@Override
	public void aplicar() {
		//Marco interior
		super.aplicar();
		//Marco medio
		//Nuevo ancho para la capa intermedia
		setAnchura (ANCHOMARCO-ANCHOMARCO_INTERIOR);
		setColor(new ColorRGB(255,255,255));
		super.aplicar();
		
		//Marco exterior
		setAnchura (ANCHOMARCO_EXTERIOR);
		setColor(color);
		super.aplicar();
	}
}

