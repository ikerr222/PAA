package paa.airline.presentacion;

import javax.swing.SwingUtilities;

public class VentanaPrincipal {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				//LLamada a la ventana principal
				
				MainFrame mf = new MainFrame ();
				mf.setVisible(true);
				
				
				
				
			}
		});

	}

}
