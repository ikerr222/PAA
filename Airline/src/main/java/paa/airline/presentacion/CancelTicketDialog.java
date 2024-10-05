package paa.airline.presentacion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import paa.airline.business.AirlineServiceException;
import paa.airline.business.RemoteAirlineService;
import paa.airline.model.Flight;
import paa.airline.model.Ticket;

import javax.swing.JComboBox;

public class CancelTicketDialog extends JDialog{

	private static final long serialVersionUID = 1L;

	private JComboBox<Flight> comboVuelos;
	private JComboBox<Ticket> comboTicket;
	
	private JLabel labelVuelos;
	private JLabel labelTickets;
	
	private JButton okBtn;
	private JButton cancelBtn;
	
	private Ticket selectedTicket;
	private RemoteAirlineService service;
	private MainFrame ventanaPrincipal;
	
	public CancelTicketDialog(MainFrame ventanaPrincipal, RemoteAirlineService service,int selectedFlightIndex) {
		
		super(ventanaPrincipal, "", true);
		this.ventanaPrincipal = ventanaPrincipal;
		this.service = service;
		
		JPanel panelInfo = new JPanel (new GridLayout (2,2));
		labelVuelos = new JLabel ("Flight: ");
		labelTickets = new JLabel ("Ticket: ");
		
		List<Flight> listaVuelos = service.listFlights();
		comboVuelos = new JComboBox<Flight> (new Vector <Flight>(listaVuelos));
		comboVuelos.setSelectedIndex(selectedFlightIndex);
		Flight selectedFlightItem = (Flight) comboVuelos.getSelectedItem();
		
		List<Ticket> listaTickets = selectedFlightItem.getTickets();
		comboTicket = new JComboBox<Ticket> (new Vector <Ticket>(listaTickets));
		comboTicket.setSelectedIndex(listaTickets.size()-1);
		
		selectedTicket = (Ticket) comboTicket.getSelectedItem();
		panelInfo.add(labelVuelos); panelInfo.add(comboVuelos);
		panelInfo.add(labelTickets); panelInfo.add(comboTicket);
		/**++++++++****+++**/
		
		JPanel panelBotones = new JPanel();
		
		okBtn = new JButton("OK");
		cancelBtn = new JButton("Cancel");
		
		panelBotones.add(okBtn);
		panelBotones.add(cancelBtn);
		
		okBtn.addActionListener(new OKClick());
		cancelBtn.addActionListener(new CancelClick());
		comboVuelos.addActionListener(new ComboVuelosValueChange());

		/**++++++++****+++**/
		
		add(panelInfo, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
		setSize(400, 200);
		setLocationRelativeTo(getParent());
	
	}
	
	class CancelClick implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose(); 
		}
	}	
	
	class OKClick implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if( comboVuelos.getSelectedItem() != null && comboTicket.getSelectedItem() != null) {
				
				Flight f = (Flight) comboVuelos.getSelectedItem();
				int selectedIndex = comboVuelos.getSelectedIndex();

				Long idTicket = ((Ticket) comboTicket.getSelectedItem()).getTicketNumber();
				
				try {
					service.cancelTicket(idTicket, LocalDate.now());
					ventanaPrincipal.updateCombo(f, selectedIndex);
					ventanaPrincipal.updateList(f);
				}catch(AirlineServiceException e1){
					JOptionPane.showMessageDialog(null, e1.getMessage(), 
							"WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
				}
				
			}
			
			dispose();
		}
	}
	
	class ComboVuelosValueChange implements ActionListener {		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (comboVuelos.getSelectedItem() != null) {
				if (((Flight)comboVuelos.getSelectedItem()).getTickets().size() > 0) {
					
					Flight selectedFlight = (Flight) comboVuelos.getSelectedItem();
					comboTicket.removeAllItems(); //=new JComboBox<Ticket> (new Vector <Ticket>(listaVuelos));
					for (Ticket t : selectedFlight.getTickets()) {
						comboTicket.addItem(t);
					}
					comboTicket.setSelectedIndex(selectedFlight.getTickets().size()-1);
					
				}else {
					
					JOptionPane.showMessageDialog(null, "No hay tickets asociados a este vuelo", 
							"WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
					
				}
				
			}
			
			
			
		}
	}
	
	
	
}