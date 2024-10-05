package paa.airline.presentacion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import paa.airline.business.AirlineServiceException;
import paa.airline.business.RemoteAirlineService;
import paa.airline.model.Flight;
import paa.airline.model.Ticket;

public class BuyTicketDialog extends JDialog{

	private static final long serialVersionUID = 1L;

	private JTextField firstName;
	private JTextField lastName;
	private JTextField flightDate;
	private JComboBox<Flight> comboFlight;
	private JButton okBoton;
	private JButton cancelBoton;
	private RemoteAirlineService service;
	private MainFrame ventanaPrincipal;
	
	public BuyTicketDialog (MainFrame ventanaPrincipal, RemoteAirlineService service, int selectedIndex) {
		super(ventanaPrincipal,"Buy Ticket",true);
		this.service = service;	
		this.ventanaPrincipal = ventanaPrincipal;
		
		 JLabel firstNameLabel = new JLabel("Traveller first Name: ");
		 JLabel lastNameLabel = new JLabel("Traveller last Name: ");
		 JLabel flightDateLabel = new JLabel("Flight Date: ");
		 JLabel flightLabel = new JLabel("Flight");
		 
		 firstName = new JTextField();;
	     lastName = new JTextField();
	     flightDate = new JTextField();;
		 
		 comboFlight = new JComboBox<Flight>(new Vector<Flight> (service.listFlights()));
		 comboFlight.setEditable(false);
		 comboFlight.setSelectedIndex(selectedIndex);
	     comboFlight.addActionListener(new ComboVuelosValueChange());
		 
		 okBoton = new JButton("OK");
		 cancelBoton = new JButton("Cancelar");
	     
		 okBoton.addActionListener(new OKClick());
		 cancelBoton.addActionListener(new CancelClick());
	    
		 JPanel panelInfo = new JPanel(new GridLayout(0,2));
		 panelInfo.add(firstNameLabel); panelInfo.add(firstName);
		 panelInfo.add(lastNameLabel); panelInfo.add(lastName);
		 panelInfo.add(flightLabel); panelInfo.add(comboFlight);
		 panelInfo.add(flightDateLabel); panelInfo.add(flightDate);
		 
		 JPanel panelButton = new JPanel();
		 panelButton.add(okBoton);
		 panelButton.add(cancelBoton);
		 
		 add(panelInfo, BorderLayout.CENTER);
		 add(panelButton, BorderLayout.SOUTH);
		
		 setSize(400,200);
		 setLocationRelativeTo(getParent());
		 //setVisible(false);
	}
		 
		 //Clases internas
		 
		   class OKClick implements ActionListener {
				@Override
				public void actionPerformed(ActionEvent e) {
	           //AAAA-MM-DD
	            if(firstName.getText().trim().isEmpty()||
	            lastName.getText().trim().isEmpty() ||
	            flightDate.getText().trim().isEmpty()){
	            	JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos",
	            			"WARNING MESSAGE", JOptionPane.WARNING_MESSAGE);
	            }else {
	            LocalDate fechaSalida = validarFecha();
	            	if(fechaSalida==null) {JOptionPane.showMessageDialog(null, "Formato fecha incorrecto",
	            			"WARNING MESSAGE", JOptionPane.WARNING_MESSAGE);
	            	
	            }else {
	            	try {
	            	Long flightNumber = ((Flight)comboFlight.getSelectedItem()).getFlightNumber();
	            	service.purchaseTicket(firstName.getText().trim(),lastName.getText().trim() ,flightNumber,ventanaPrincipal.getFechaSimulada(),fechaSalida);
	            	ventanaPrincipal.updateCombo((Flight)comboFlight.getSelectedItem(), comboFlight.getSelectedIndex());
	            	
	            	dispose();
	            	}catch(AirlineServiceException exc) {
	            		JOptionPane.showMessageDialog(null, exc.getMessage(),
		            			"ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
	            	}catch(Exception exc2) {
	            		JOptionPane.showMessageDialog(null, "Error inesperado",
		            			"ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
	            	}
	            }
	            }
				}
				}
		 
		 class CancelClick implements ActionListener {
				@Override
				public void actionPerformed(ActionEvent e) {
	            dispose();
	            
				}
		 }
		 class ComboVuelosValueChange implements ActionListener {
					@Override
					public void actionPerformed(ActionEvent e) {
						
					}
					
				}
		 private LocalDate validarFecha() {
			    LocalDate date=null;
			    try {
			        //Formato de fecha (día/mes/año)
			    	DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("d-MM-yyyy");
			        //Comprobación de la fecha
			        String dateStr = flightDate.getText().trim();
			        formatoFecha.parse(dateStr);
			        date = LocalDate.parse(dateStr, formatoFecha);
			   
			    } catch (DateTimeParseException e) {
			        //Si la fecha no es correcta, pasará por aquí
			        date = null;
			    }
		 
			    return date;
			
	}

	}

