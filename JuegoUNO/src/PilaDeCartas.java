import java.util.Random;

public class PilaDeCartas {
    
	private Carta[] pila;
	private int numCartas;
	
	public PilaDeCartas (int numeroMaximoDeCartas) {
		numCartas = 0;
		pila = new Carta[numeroMaximoDeCartas];
		
	}
	
	public boolean hayCartasDisponibles () {
		boolean hayCartas = false;
		if(numCartas>0) {
			hayCartas = true;
		}
		return hayCartas;
	}
	
	public void agregarCarta(Carta carta) {
	
	pila[numCartas]=carta;
	numCartas++;
	
	}
	
	public Carta extraerCartaParteSuperior() {
	Carta extraida = pila[numCartas-1];
	pila[numCartas-1]=null;
	numCartas--;
	
	return extraida;
	}
	
	public Carta verCartaParteSuperior () {
		
		Carta mostrada = pila[numCartas-1];
		
		return mostrada;
	}

	public void barajar() {
		int index;
		Carta aux; 
	    Random random = new Random();
	    for (int i = pila.length - 1; i > 0; i--)
	    {
	        index = random.nextInt(i + 1);
	        if (index != i)
	        {
	            
	        	aux = pila[i]; 
	            pila[i] = pila[index];
	            pila[index] = aux;
	        }    }
	}
	        public Carta[] getCarta() {
		return pila;
	    }
	 	}
	
