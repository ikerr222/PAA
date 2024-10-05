
public class Carta {
  
	private final int valor;
	private final String letra;
	
	public Carta (int valor, String letra) {
		this.valor = valor;		
		this.letra = letra;
		
	}
	
	public String getIdentificador() {
		return valor+letra;
		
	}
	
	public boolean sePuedeApilarSobre(Carta otraCarta) {
		
		boolean apilable = false;
		
		if (valor == otraCarta.valor || letra.equals(otraCarta.letra)){
			
			apilable = true;
			
		}
		
			return apilable;
	}
}

	
	