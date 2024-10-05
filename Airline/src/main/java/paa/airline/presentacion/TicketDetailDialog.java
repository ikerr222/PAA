package paa.airline.presentacion;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import paa.airline.model.Ticket;
public class TicketDetailDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	JLabel labelticketNumber; 
	JLabel passengerFirstNameLabel; 
	JLabel passengerLastNameLabel; 
	JLabel seatRowLabel;
	JLabel seatColumnLabel; 
	JLabel flightDateLabel; 
	JLabel pricePaidLabel; 
	
	public TicketDetailDialog (MainFrame parentFrame,Ticket t) {
		
		super(parentFrame,"Info Ticket",true);
		JPanel panel=new JPanel();
		
		labelticketNumber = new JLabel(t.getTicketNumber().toString());
		passengerFirstNameLabel = new JLabel(t.getPassengerFirstName());
		passengerLastNameLabel= new JLabel(""+t.getPassengerLastName());
		seatRowLabel= new JLabel(""+t.getSeatRow());
		seatColumnLabel= new JLabel(""+t.getSeatColumn());
		flightDateLabel= new JLabel(""+t.getFlightDate());
		pricePaidLabel= new JLabel(""+t.getPricePaid());
		
		panel.setLayout(new GridLayout(0,2));
		panel.add(new JLabel("Ticket Number: "));
		panel.add(labelticketNumber);
		panel.add(new JLabel("First name: "));
		panel.add(passengerFirstNameLabel);
		panel.add(new JLabel("last Name: "));
		panel.add(passengerLastNameLabel);
		panel.add(new JLabel("Seat row: "));
		panel.add(seatRowLabel);
		panel.add(new JLabel("Seat column: "));
		panel.add(seatColumnLabel);
		panel.add(new JLabel("Flight date: "));
		panel.add(flightDateLabel);
		panel.add(new JLabel("Price: "));
		panel.add(pricePaidLabel);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(getParent());
	    add(panel);
		setSize(500, 200);
		setVisible(false);
	}
	
/*	public void updateInfo (Ticket t) {
		labelCodigo.setText(t.getTicketNumber().toString());
		String addresseeString = ""+p.getAddressee()+"";;
		addresseeLabel.setText(addresseeString);
		arrivalDateLabel.setText(p.getArrivalDate().toString());
		infoLockerLabel.setText(p.getLocker().toString());

	}
*/
	
}
