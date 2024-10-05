package paa.airline.presentacion;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import paa.airline.business.AirlineServiceException;
import paa.airline.business.RemoteAirlineService;
import paa.airline.model.AircraftType;

public class NewAircraftDialog extends JDialog {
	
	private static final long serialVersionUID = 5337894429948444149L;
	private static final int numFilas = 5;
	private static final int numColumnas=2;
	//Atributos
	private JTextField manufacturer_tf;
	private JTextField model_tf;
	private Choice rows_choice;
	private Choice colums_choice;

	private JButton ok_BTN;
	private JButton cancel_BTN;
	private RemoteAirlineService service;
	private NewFlightDialog nuevoVueloDialog;
	
	//Constructor 
	public NewAircraftDialog (MainFrame ventanaPrincipal, RemoteAirlineService service,NewFlightDialog nuevoVueloDialog) {
		super(ventanaPrincipal ,"Create Aircraft", true);
		
		//NECESARIO PARA LA CLASE INTERNA DEL BOTON OK
		this.service = service;
		this.nuevoVueloDialog = nuevoVueloDialog;
		//--------------------------------------------
		JPanel panelInfo = new JPanel (new GridLayout(numFilas,numColumnas));
		
		JLabel manufacturerLabel = new JLabel ("Manufacturer: ");
		JLabel modelLabel = new JLabel ("Model: ");
		JLabel rowsLabel = new JLabel ("Seat rows: ");
		JLabel columsLabel = new JLabel ("Seat colums: ");
				
		manufacturer_tf = new JTextField ();
		model_tf = new JTextField ();
		rows_choice = new Choice ();
		for (int i=0; i<80; i++) {
			rows_choice.addItem(i+"");
		}
		colums_choice= new Choice ();
		for (int i=0; i<20; i++) {
			colums_choice.addItem(i+"");
		}
		
		panelInfo.add(manufacturerLabel);	panelInfo.add(manufacturer_tf);
		panelInfo.add(modelLabel);			panelInfo.add(model_tf);
		panelInfo.add(rowsLabel);			panelInfo.add(rows_choice);
		panelInfo.add(columsLabel);			panelInfo.add(colums_choice);
	
		JPanel panelBotones = new JPanel ();
		
		ok_BTN = new JButton ("OK");
		cancel_BTN= new JButton ("Cancel");
		panelBotones.add(ok_BTN);
		panelBotones.add(cancel_BTN);
		
		//PROGRAMACION DE LA ACCION DEL BOTON CON UNA CLASE INTERNA
		ok_BTN.addActionListener(new OkClick());

		cancel_BTN.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				if(nuevoVueloDialog!=null) {
					nuevoVueloDialog.setActualizarCombo(false);
				}
				dispose(); 
			}
		});
		
		setLayout(new BorderLayout());
		add (panelInfo, BorderLayout.CENTER);
		add (panelBotones, BorderLayout.SOUTH);
		
		setLocationRelativeTo(getParent());
		setSize(400,200);
		setVisible(false);
	}
	
	public void resetTextFields() {
		manufacturer_tf.setText(null);
		model_tf.setText(null);
	}
	
	//Clases para el manejo de los botones (Clase interna)
	class OkClick implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (manufacturer_tf.getText().isEmpty() || model_tf.getText().isEmpty()) {
				
				JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos",
						  "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
			}else {
				try {
					AircraftType aircraftType = service.createAircraft(manufacturer_tf.getText().trim(),
						model_tf.getText().trim(), 
			    		Integer.parseInt(rows_choice.getSelectedItem().trim()),
			    		Integer.parseInt(colums_choice.getSelectedItem().trim()));
					
						if (aircraftType != null) {
							JOptionPane.showMessageDialog(null,"El avion se ha a�adido correctamente ");
							
							if(nuevoVueloDialog!=null) {
								nuevoVueloDialog.setActualizarCombo(true);
							}
							dispose(); //setVisible(false);
						}else {
							JOptionPane.showMessageDialog(null, "No ha sido posible a�adir el avion.",
									  "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
						}
					}catch(AirlineServiceException excep) {
						JOptionPane.showMessageDialog(null,excep.getMessage(),
								  "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
					}
			}
		}
	}
	

}

