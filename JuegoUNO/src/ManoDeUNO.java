/**
 * @author ikerb
 *@version v1.0
 */

public class ManoDeUNO {

	private Carta[] mano; 
	
	
	public ManoDeUNO(int numeroMaximoDeCartas) {
		
        mano = new Carta[numeroMaximoDeCartas];
		
		
}
	public boolean estaVacia () {
		
		boolean notengoCartas = true;
	    int i = 0;
	    while(i<mano.length && notengoCartas) {
	    if(mano[i]!=null) {
	    	notengoCartas = false;
	    }
	    i++;
		}
		return notengoCartas;
	}
	
	public void agregarCarta(Carta carta) {
		
		boolean auxiliar = false;
		int i=0;
		
		while (i<mano.length && !auxiliar) {
			if(mano[i] == null) {
				
				mano[i] = carta;
				auxiliar = true;
				
			}}
			i++;
		}
		
		public Carta extraerCartaApilableSobre(Carta cartaSobreLaQueHayQueApilar) {
			
			Carta cartaExtraida = null;
			int i = 0;
			
			while(i<mano.length && cartaExtraida == null) {
				if(mano[i] != null && mano[i].sePuedeApilarSobre(cartaSobreLaQueHayQueApilar)) {
				cartaExtraida = mano[i];
				
			}
				i++;
			}
			
			return cartaExtraida;
	}
	
		public String getMano() {
			
			String manoStr = "";
			
			if(estaVacia ()) {
				manoStr = "Sin cartas.";
				
			}else {
				for (int i=0; i<mano.length; i++) {
				if (mano[i] !=null) {
					manoStr += mano[i].getIdentificador() + " ";//Para que se vea: 3c 5d 
			}
				}
			}
			return manoStr;
				
		
				}	
		
		}

