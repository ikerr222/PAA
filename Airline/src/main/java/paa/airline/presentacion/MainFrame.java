package paa.airline.presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import paa.airline.business.RemoteAirlineService;
import paa.airline.model.Flight;
import paa.airline.model.Ticket;
import paa.airline.util.FlightMap;

public class MainFrame extends JFrame{

	private JMenuBar menuBar;
	private FlightMap mapa;
	private RemoteAirlineService service;
	private JComboBox<LocalDate> comboFechas;
	private JComboBox<Flight> comboVuelos;
	private List<Flight> listaVuelos;
	private JList<Ticket> listaTickets;
	private JScrollPane scroll;
	private MainFrame frame;
	Flight selectedFlight;
	private JPanel panelFecha;
	private JLabel fechaLabel;
	private JButton btnRight;
	private JButton btnLeft;
	
	private LocalDate simuladorFecha;
	
	
	public MainFrame () {
		
		super("Airline Manager");
		frame = this;
		service = new RemoteAirlineService();
		setLayout(new BorderLayout ());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
				
		setSize((int)width, (int)height-50);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //HIDE ON CLOSE: Cierra pero no acaba el programa. 
		//EXIT ON CLOSE: Cierra la ventana y el programa
		
		//Menu Toolbar
		menuBar = new JMenuBar ();
		JMenu menuOperations = new JMenu("Operations");
		JMenu menuAyuda = new JMenu("Help");
		
		
		JMenuItem menu_nuevoAeropuerto = new JMenuItem (new AbstractAction("Nuevo Aeropuerto") {
		private static final long serialVersionUID = 1L;
			public void actionPerformed (ActionEvent e) {
				CreateAirportDialog crearAeropuerto = new CreateAirportDialog(frame,service, null);
				crearAeropuerto.setVisible(true);
				
			}
		});
		
		JMenuItem menu_nuevoAvion = new JMenuItem (new AbstractAction("Nuevo Avion") {
			private static final long serialVersionUID = 1L;
				public void actionPerformed (ActionEvent e) {
			        NewAircraftDialog crearAvion = new NewAircraftDialog(frame,service, null);
					crearAvion.setVisible(true);
					
				}
			});
		
		JMenuItem menu_nuevoVuelo = new JMenuItem (new AbstractAction("Nuevo Vuelo") {
			private static final long serialVersionUID = 1L;
				public void actionPerformed (ActionEvent e) {
					NewFlightDialog crearVuelo = new NewFlightDialog(frame,service);
					crearVuelo.setVisible(true);
					
				}
			});
		
		JMenuItem menu_compraTicket = new JMenuItem (new AbstractAction("Comprar Ticket") {
			private static final long serialVersionUID = 1L;
				public void actionPerformed (ActionEvent e) {
					BuyTicketDialog compraTicket = new BuyTicketDialog(frame,service,comboVuelos.getSelectedIndex());
					compraTicket.setVisible(true);
					
				}
			});
		
		JMenuItem menu_cancelarTicket = new JMenuItem (new AbstractAction("Cancel Ticket") {
			private static final long serialVersionUID = 1L;
				public void actionPerformed (ActionEvent e) {
					CancelTicketDialog cancelTicket = new CancelTicketDialog(frame,service, comboVuelos.getSelectedIndex());
					cancelTicket.setVisible(true);
					
				}
			});
		
		JMenuItem menu_Salir = new JMenuItem ("Salir");
		
		menuOperations.add(menu_nuevoAvion);
		menuOperations.add(menu_nuevoVuelo);
		menuOperations.add(menu_compraTicket);
		menuOperations.add(menu_cancelarTicket);
		menuOperations.add(menu_nuevoAeropuerto);
		menuOperations.add(menu_Salir);
		
		
		JMenuItem menu_about = new JMenuItem ("about");
		menuAyuda.add(menu_about);
		
		menuBar.add(menuOperations);
		menuBar.add(menuAyuda);
		
		setJMenuBar(menuBar);
		
		//Panel Botones
		
		setJMenuBar(menuBar);
		//Panel Botones
		JToolBar barraBotones = new JToolBar ();
		barraBotones.setLayout(new BorderLayout());
		JPanel panelBTNs = new JPanel();
		panelBTNs.setLayout(new FlowLayout(FlowLayout.LEFT)); 
		JButton createVuelo_btn = new JButton (new ImageIcon(getClass().getResource("/newFlight.png")));
		JButton crearReserva_btn = new JButton (new ImageIcon(getClass().getResource("/buyTicket.png")));
		JButton cancelarReserva_btn = new JButton (new ImageIcon(getClass().getResource("/cancelTicket.png")));
		panelBTNs.add(createVuelo_btn);
		panelBTNs.add(crearReserva_btn);
		panelBTNs.add(cancelarReserva_btn);
		barraBotones.add(panelBTNs);
		//Panel botones fecha 
		JPanel panelRightBarraBotones = new JPanel (new BorderLayout());
		JLabel infoLabel = new JLabel ("Current date (simulator)");
		
		panelFecha = new JPanel ();
		simuladorFecha = LocalDate.now();
		fechaLabel = new JLabel (simuladorFecha.toString());
		btnRight = new JButton (">");
		btnLeft = new JButton ("<");
		panelFecha.add(btnLeft);
		panelFecha.add(fechaLabel);
		panelFecha.add(btnRight);

		panelRightBarraBotones.add(infoLabel, BorderLayout.CENTER );
		panelRightBarraBotones.add(panelFecha , BorderLayout.SOUTH);
		barraBotones.add(panelRightBarraBotones, BorderLayout.EAST);
		
		
		btnRight.addActionListener(new IncFechaClick());
		btnLeft.addActionListener(new DecFechaClick());
		
		JToolBar barraBotones2 = new JToolBar ();
		JButton addVuelo = new JButton(new ImageIcon(getClass().getResource("/newFlight.png")));
		JButton crearReserva = new JButton(new ImageIcon(getClass().getResource("/buyTicket.png")));
		JButton cancelarReserva = new JButton(new ImageIcon(getClass().getResource("/cancelTicket.png")));
		barraBotones2.add(addVuelo);
		barraBotones2.add(crearReserva);
		barraBotones2.add(cancelarReserva);
		
		add(barraBotones2, BorderLayout.NORTH);
		
		//Panel info vuelos
		
				JPanel panelWest = new JPanel();
				panelWest.setSize(this.getWidth()/4,this.getHeight()/4);
				panelWest.setLayout(new BorderLayout());
				
				//Combo 
				
				listaVuelos = service.listFlights();
				comboVuelos = new JComboBox<Flight>(new Vector<Flight>(listaVuelos));
				TitledBorder comboVuelosTitle = BorderFactory.createTitledBorder("Scheduled Flights");
				comboVuelos.setBorder(comboVuelosTitle);
				comboVuelos.setEditable(false);
				if(listaVuelos.size()>9) {
				comboVuelos.setSelectedItem(0);
				}
				panelWest.add(comboVuelos,BorderLayout.NORTH);
				
				//Lista de Tickets
				
				TitledBorder comboTicketsTitle = BorderFactory.createTitledBorder("Tickets");
				listaTickets = new JList<Ticket>();
				
				if(((Flight)comboVuelos.getSelectedItem())!=null) {
					listaTickets.setListData(new Vector<Ticket>(((Flight)comboVuelos.getSelectedItem()).getTickets()));
				}else {
				listaTickets.setListData(new Vector<Ticket> ());
				}
				
				listaTickets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//Selecciona una, al seleccionar otra la última se quita.
				scroll = new JScrollPane (listaTickets);
				scroll.setBorder(comboTicketsTitle);
				
				panelWest.add(scroll, BorderLayout.CENTER);
				
		
		
		
		//Panel Mapa
		
		JPanel centerPanel = new JPanel();
		TitledBorder mapTitle = BorderFactory.createTitledBorder("Flight Map");	
		centerPanel.setBorder(mapTitle);
		
		mapa = new FlightMap(centerPanel.getWidth(),centerPanel.getHeight(), service);
		mapa.updateMap(LocalDate.now(), ((Flight)comboVuelos.getSelectedItem()).getFlightNumber());
		comboFechas = new JComboBox<LocalDate>();
		//Codigo de prueba
		int dias = 0;
		while (dias < 15) {
			comboFechas.addItem(LocalDate.now().plusDays(dias));
			dias++;
		}
		comboFechas.setEditable(false);
		comboFechas.setSelectedItem(LocalDate.now());
		comboFechas.addActionListener(new ComboFechasValueChange());
		
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(comboFechas, BorderLayout.NORTH);
		centerPanel.add(mapa, BorderLayout.CENTER);
	    
	    
		
		
		//Añadiendo  los distintos paneles al Frame Principal
		
		add(barraBotones, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
	    add(panelWest, BorderLayout.WEST);
	    comboVuelos.addActionListener(new ComboVuelosValueChange());
	    listaTickets.addListSelectionListener(new ListaTicketsSelection());
	}
	
	//Metodos
	
	public void updateCombo (Flight selectedFlight, int selectedIndex) {
		comboVuelos.removeAllItems();

		this.selectedFlight = selectedFlight;
		listaVuelos = service.listFlights();
		int i=0;
		for (Flight f:listaVuelos) //=new JComboBox<Parking>(new Vector<Parking>(vParking));
		{			
			comboVuelos.addItem(f);
			if(f.equals(selectedFlight)) {
				selectedIndex=i;
			}
			i++;
		}
		comboVuelos.setSelectedIndex(selectedIndex);
		comboVuelos.setSelectedItem(this.selectedFlight);
		updateList(this.selectedFlight);
		
	}
	
	public void updateList( Flight updateFlight ) {
		listaTickets.setListData(new Vector<Ticket> (((Flight)comboVuelos.getSelectedItem()).getTickets()));
		//listaTickets.setSelectedIndex(0);
		mapa.updateMap(LocalDate.now(), ((Flight)comboVuelos.getSelectedItem()).getFlightNumber());

}
	class ComboVuelosValueChange implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(comboVuelos.getSelectedItem()!= null) {
				updateList((Flight)comboVuelos.getSelectedItem());
			}
		}
	}
	class ListaTicketsSelection implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent evt){
			if(listaTickets.getSelectedValue()!=null) {
				TicketDetailDialog infoReserva = new TicketDetailDialog(frame,listaTickets.getSelectedValue());
				infoReserva.setVisible(true);		
			}
		}
	}
	class IncFechaClick implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		simuladorFecha = simuladorFecha.plusDays(1);
 
		fechaLabel.setText(simuladorFecha.toString());;
		}}
	class DecFechaClick implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			simuladorFecha = simuladorFecha.minusDays(1);
			fechaLabel.setText(simuladorFecha.toString());
 }}
	class ComboFechasValueChange implements ActionListener { // Clase interna		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (comboFechas.getSelectedItem() != null) {
				mapa.updateMap((LocalDate)comboFechas.getSelectedItem(), ((Flight)comboVuelos.getSelectedItem()).getFlightNumber());
			}		
		}
	}
	
	public LocalDate getFechaSimulada() { return simuladorFecha;}
}
