 package paa.airline.presentacion;

	 import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
	 import paa.airline.model.AircraftType;
	 import paa.airline.model.Airport;
import paa.airline.model.Flight;

	 public class NewFlightDialog extends JDialog {

	 	private static final long serialVersionUID = 1L;
	 	private static final int numFilas = 4;
	 	private static final int numColumnas=3;
	 	
	 	private JComboBox<Airport> comboOrigenAirport;
	 	private JComboBox<Airport> comboDestinoAirport;
	 	private JComboBox<AircraftType> comboAviones;
	 	
	 	private JButton newAirportOrigen_btn;
	 	private JButton newAirportDest_btn;
	 	private JButton newAircraft_btn;
	 	
	 	private JButton ok_btn;
	 	private JButton cancel_btn;
	 	
	 	
        private MainFrame ventanaPrincipal;
        private RemoteAirlineService service;
        private boolean actualizaCombo;
	 	
	 	public NewFlightDialog (MainFrame ventanaPrincipal, RemoteAirlineService service) {
	 		super(ventanaPrincipal, "New flight",true);
	 		this.ventanaPrincipal=ventanaPrincipal;
	 		this.service = service;
	 		actualizaCombo = false;
	 		
	 		JPanel panelInfo = new JPanel (new GridLayout(numFilas,numColumnas));
      
	 		JLabel originAirportLabel = new JLabel ("Origin: ");
	 		JLabel destinationAirportLabel = new JLabel ("Destination: ");
	 		JLabel aircraftTypeLabel = new JLabel ("Aircraft type: ");
	 		
	 		
	 		comboOrigenAirport = new JComboBox<Airport>(new Vector<Airport>(service.listAirports()));
	 		comboOrigenAirport.setEditable(false);
	 		comboOrigenAirport.setSelectedItem(0);
	 				
	 		comboDestinoAirport = new JComboBox<Airport>(new Vector<Airport>(service.listAirports()));
	 		comboDestinoAirport.setEditable(false);
	 		comboDestinoAirport.setSelectedItem(0);
	 		
	 		comboAviones = new JComboBox<AircraftType>(new Vector<AircraftType>(service.listAircraftTypes()));
	 		comboAviones.setEditable(false);
	 		comboAviones.setSelectedItem(0);
	 		
	 		newAirportOrigen_btn = new JButton ("Nuevo Aeropuerto");
	 		newAirportDest_btn = new JButton ("Nuevo Aeropuerto");
	 		newAircraft_btn = new JButton ("Nuevo avion");
	 		
	 		
	 		panelInfo.add(originAirportLabel); panelInfo.add(comboOrigenAirport); panelInfo.add(newAirportOrigen_btn);
	 		panelInfo.add(destinationAirportLabel); panelInfo.add(comboDestinoAirport); panelInfo.add(newAirportDest_btn);
	 		panelInfo.add(aircraftTypeLabel); panelInfo.add(comboAviones); panelInfo.add(newAircraft_btn);
	 		
	 		
	 		JPanel buttonPanel = new JPanel ();
	 		ok_btn = new JButton ("OK");
	 		cancel_btn = new JButton ("Cancel");
	 		buttonPanel.add(ok_btn);
	 		buttonPanel.add(cancel_btn);
	 				
	 		/** misma visualizacion que con setLayout(new GridLayout(2,1));
	 		 * ---------------------------------------------------------------
	 		 * Box verticalBox = Box.createVerticalBox();
	 		 * verticalBox.add(panelInfo);
	 		 * verticalBox.add(buttonPanel);
	 		 * add(verticalBox);
	 		 * 
	 		 * */
	 		setLayout(new BorderLayout(2,1));
	 		add(panelInfo, BorderLayout.NORTH);
	 		add(buttonPanel, BorderLayout.SOUTH);
	 		
	 		setLocationRelativeTo(getParent().getParent());
	 		setSize(700,200);
	 		setVisible(false);
	 		
	 		newAirportOrigen_btn.addActionListener(new NewAirportClick());
		 	newAirportDest_btn.addActionListener(new NewAirportClick());
		 	newAircraft_btn.addActionListener(new NewAircraftClick());
		 	
		 	ok_btn.addActionListener(new OkClick());
		 	cancel_btn.addActionListener(new CancelClick());
	 		
	 	}
	 	
	 	
	 	
	 	class OkClick implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboAviones.getSelectedItem()==null) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un avion.",
							  "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
				
				}else if (comboOrigenAirport.getSelectedItem()==null){
					JOptionPane.showMessageDialog(null, "Debe seleccionar un aeropuerto de origen.",
							  "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
				
				}else if (comboDestinoAirport.getSelectedItem()==null ){
					JOptionPane.showMessageDialog(null, "Debe seleccionar un aeropuerto de origen.",
							  "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
				}else{
					try {
						Flight f = service.createFlight(((Airport)comboOrigenAirport.getSelectedItem()).getIataCode(),
								((Airport)comboDestinoAirport.getSelectedItem()).getIataCode(),
								((AircraftType)comboAviones.getSelectedItem()).getId());
						List <Flight>listaVuelos = service.listFlights();
						int selectedIndex = -1;
						int i=0;
						for (Flight v:listaVuelos) {			
							if(v.equals(f)) {
								selectedIndex=i;
							}
							i++;
						}
						ventanaPrincipal.updateCombo(f, selectedIndex);
						dispose();
					} catch (AirlineServiceException e1) {
						
						JOptionPane.showMessageDialog(null, "No ha sido posible añadir el nuevo vuelo.",
								  "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
		class CancelClick implements ActionListener {
				@Override
				public void actionPerformed(ActionEvent e) {
				dispose();
		 }}
		class NewAirportClick implements ActionListener {
			    @Override
				public void actionPerformed(ActionEvent e) {
					CreateAirportDialog crearAeropuerto = new CreateAirportDialog(ventanaPrincipal, service, NewFlightDialog.this);
			        crearAeropuerto.setVisible(true);
					
			        if(actualizaCombo) {
			        updateAirportCombo(comboOrigenAirport);
			        updateAirportCombo(comboDestinoAirport);
			        }
			        actualizaCombo=false;
			}
	   }
		class NewAircraftClick implements ActionListener {
						@Override
				public void actionPerformed(ActionEvent e) {
					NewAircraftDialog crearAvion = new NewAircraftDialog(ventanaPrincipal, service, NewFlightDialog.this);
				    crearAvion.setVisible(true);
					if(actualizaCombo) {
						updateAircraftCombo(comboAviones);
					}
						}
				 }
		public void updateAirportCombo(JComboBox<Airport> combo) {
			
			combo.removeAllItems();
			List<Airport>listaAeropuertos = service.listAirports();
			
			for (Airport f:listaAeropuertos) //=new JComboBox<Parking>(new Vector<Parking>(vParking));
			{			
				combo.addItem(f);
				
			}
		}
			
			public void updateAircraftCombo(JComboBox<AircraftType> combo) {
				
				combo.removeAllItems();
				List<AircraftType>listaAviones = service.listAircraftTypes();
				
				for (AircraftType f:listaAviones) //=new JComboBox<Parking>(new Vector<Parking>(vParking));
				{			
					combo.addItem(f);
				}
			//combo.setSelectedIndex(selectedIndex);
			//combo.setSelectedItet(combo.getSelectedItem());
		
			
			
			
		}
		
		public boolean getActualizarCombo() {return actualizaCombo;}
		
		public void setActualizarCombo(boolean actualizaCombo) {
			this.actualizaCombo=actualizaCombo;
		}
		}
	 	
	
