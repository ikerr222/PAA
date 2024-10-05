package paa.airline.presentacion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import paa.airline.business.AirlineServiceException;
import paa.airline.business.RemoteAirlineService;
import paa.airline.model.Airport;

public class CreateAirportDialog extends JDialog {

	private static final long serialVersionUID = 5337894429948444149L;
    private static final int numFilas = 5;
    private static final int numColumnas = 2;
	
    //Atributos
    
	private JTextField iataCodeText;
	private JTextField airportNameText;
	private JTextField airportCityText;
	private JTextField airportLongitudeText;
	private JTextField airportLatitudeText;
	
	private JButton ok_BTN;
    private JButton cancel_BTN;
    private JButton autofill_BTN;
    private RemoteAirlineService service;
    private NewFlightDialog nuevoVueloDialog;
	
    //Constructor
	
    public CreateAirportDialog (MainFrame ventanaPrincipal, RemoteAirlineService service, NewFlightDialog nuevoVueloDialog) {
		super(ventanaPrincipal,"Create Airport", true); //True-> No podemos seleccionar nada despues.
		this.service = service;
		this.nuevoVueloDialog = nuevoVueloDialog;
		
		JPanel panelInfo = new JPanel (new GridLayout(numFilas, numColumnas));
	   
		JLabel iataCodeLabel = new JLabel ("IATA code: ");
	    JLabel airportNameLabel = new JLabel ("Name: ");
	    JLabel airportCityLabel = new JLabel ("City: ");
	    JLabel airportLongitudeLabel = new JLabel ("Longitude: ");
	    JLabel airportLatitudeLabel = new JLabel ("Latitude: ");
	    
	    JTextField iataCodeText = new JTextField();
		JTextField airportNameText = new JTextField();
		JTextField airportCityText  = new JTextField();
		JTextField airportLongitudeText  = new JTextField();
		JTextField airportLatitudeText  = new JTextField();
	
		panelInfo.add(iataCodeLabel);            panelInfo.add(iataCodeText);
		panelInfo.add(airportNameLabel);         panelInfo.add(airportNameText);
		panelInfo.add(airportCityLabel);         panelInfo.add(airportCityText);
		panelInfo.add(airportLongitudeLabel);    panelInfo.add(airportLongitudeText);
		panelInfo.add(airportLatitudeLabel);     panelInfo.add(airportLatitudeText);

        JPanel panelBotones = new JPanel();
        ok_BTN = new JButton("OK");
        cancel_BTN = new JButton("Cancel");
        autofill_BTN = new JButton("Autofill");
        panelBotones.add(ok_BTN);
        panelBotones.add(cancel_BTN);
        panelBotones.add(autofill_BTN);
        autofill_BTN.addActionListener(new AutofillClick());
   
        cancel_BTN.addActionListener(new ActionListener () {
		public void actionPerformed (ActionEvent e) {
			if(nuevoVueloDialog!=null) {
				nuevoVueloDialog.setActualizarCombo(false);
			}
			dispose(); 
			}
		});
        
        ok_BTN.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {

				if (iataCodeText.getText().isEmpty() || airportNameText.getText().isEmpty() ||airportCityText.getText().isEmpty()
						||airportLongitudeText.getText().isEmpty()||airportLatitudeText.getText().isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos",
							  "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
				}else {
					try {
				    Airport newAirport = service.createAirport(
				    		iataCodeText.getText().trim(),
				    		airportCityText.getText().trim(), 
				    		airportNameText.getText().trim(),
				    		Double.parseDouble(airportLongitudeText.getText().trim()), 
				    		Double.parseDouble(airportLatitudeText.getText().trim()));
				    		
					
						if(newAirport != null) {
							JOptionPane.showMessageDialog(null, "El aeropuerto se ha añadido correctamente ");
							
							if(nuevoVueloDialog!=null) {
								nuevoVueloDialog.setActualizarCombo(true);
							}
							dispose();
									  
						}else {
							JOptionPane.showMessageDialog(null, "No ha sido posible añadir el locker",
									  "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
						}
					}catch(AirlineServiceException excep) {
						JOptionPane.showMessageDialog(null, excep.getMessage(),
								  "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
					}}}});
        
      
					
        cancel_BTN.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				if(nuevoVueloDialog!=null) {
					nuevoVueloDialog.setActualizarCombo(false);
				}
				dispose(); 
			}
		});
        
        setLayout(new BorderLayout());
        add(panelInfo, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
        
        setLocationRelativeTo(getParent());
        setSize(400,200);
        setVisible(false);
        
        
    		
    	}
    public void resetTextFields(){
		airportNameText.setText(null);
		airportCityText.setText(null);
		airportLatitudeText.setText(null);
		airportLongitudeText.setText(null);
		iataCodeText.setText(null);
    }   
    class AutofillClick implements ActionListener { // Clase interna
		@Override
		public void actionPerformed(ActionEvent e) {
			List<Airport> listaAeropuertos = service.listAirports();
			
			if (listaAeropuertos != null && listaAeropuertos.size()>0 ) {
				boolean encontrado = false;
				int i=0;
				Airport aux;
				while (listaAeropuertos.get(i).getIataCode().equals(iataCodeText.getText().trim())&& !encontrado) {
					aux = listaAeropuertos.get(i);
					airportNameText.setText(aux.getAirportName());
					airportCityText.setText(aux.getCityName());
					airportLongitudeText.setText(String.valueOf(aux.getLongitude()));
					airportLatitudeText.setText(String.valueOf(aux.getLatitude()));
					encontrado = true;
				}
				i++;
			}
		}
	}
    }


			
