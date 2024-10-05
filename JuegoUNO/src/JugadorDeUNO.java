
public class JugadorDeUNO {

	private final String nombre;
	private ManoDeUNO manoJugador;
	
	public JugadorDeUNO  (String nombre,int numeroMaximoDeCartas) {
		 this.nombre = nombre;
		 manoJugador = new ManoDeUNO(numeroMaximoDeCartas);
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public boolean sinCartasEnLaMano() {
		return manoJugador.estaVacia();
	}
	
	public void cogeCartas(PilaDeCartas cartasParaCoger, int numeroDeCartasACoger) {
		
		
while(cartasParaCoger.hayCartasDisponibles() && numeroDeCartasACoger>0) {
			
			
			Carta cartaExtraida = cartasParaCoger.extraerCartaParteSuperior();
			
			
			manoJugador.agregarCarta(cartaExtraida);

			numeroDeCartasACoger--;
			
		}
		
	}
	 
	public void juega(PilaDeCartas cartasParaCoger,PilaDeCartas cartasTiradas) {
	
		if(!manoJugador.estaVacia()) {
			
			Carta ultimacartaTirada = cartasTiradas.verCartaParteSuperior();
			Carta cartaExtraidaParaTirar = manoJugador.extraerCartaApilableSobre(ultimacartaTirada);
			
			if(cartaExtraidaParaTirar == null) {
				
				cogeCartas(cartasParaCoger, 1);
				cartaExtraidaParaTirar = manoJugador.extraerCartaApilableSobre(ultimacartaTirada);
			
			if(cartaExtraidaParaTirar !=null) {
				
				cartasTiradas.agregarCarta(cartaExtraidaParaTirar);
			}
			}else {
				cartasTiradas.agregarCarta(cartaExtraidaParaTirar);
			}
				
			}
		
}
}