package paa.airline.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import paa.airline.model.AircraftType;
import paa.airline.model.Airport;
import paa.airline.model.Flight;
import paa.airline.model.Ticket;

import paa.airline.util.AirportQuery;

public class RemoteAirlineService implements AirlineService {
	
	private static final String PERSISTENCE_UNIT_NAME = "paa-jpa";
	private static final double LONGITUD_MAX = 180.0;
	private static final double LONGITUD_MIN = -180.0;
	private static final double LATITUD_MAX = 90.0;
	private static final double LATITUD_MIN = -90.0;
	
	private String url;
	private int statusCode;
	
	public RemoteAirlineService () {
		
		url = "http://localhost:8080/p4-servidor/AirlineServer";
		
	}

	@Override
	public Airport createAirport(String iataCode, String cityName, String airportName, double longitude, double latitude) throws AirlineServiceException {
		
		String str = "";
		
		str = url+ "?action=createAirportiataCode="+iataCode+"&cityName="+cityName+"&airportName="+airportName+"&longitude="+longitude+"&latitude="+latitude;		
		str = str.replaceAll(" ", "%20");
		
		//Enviar peticion
		String jsonStr = enviarPeticion(url);
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.findAndRegisterModules();
		
		Airport airport = null;
		
		
		if(statusCode == 200) {
	
			try {
				airport = objectMapper.readValue(jsonStr, Airport.class);
		}
		catch (JsonProcessingException e) {
			
		
			e.printStackTrace();
		}
		}else if(statusCode == 400) {
			throw new AirlineServiceException(jsonStr);
		}
		return airport;
	}

	@Override
	public List<Airport> listAirports() {
     
List <Airport> airports = new ArrayList <Airport>();
		
		String str = "";
		
		str = url + "?action=listAirports";
		
		//enviamos la peticion
		String jsonStr = enviarPeticion(str);
		
		ObjectMapper objM = new ObjectMapper ();
		objM.registerModule(new JavaTimeModule());
		objM.findAndRegisterModules();
		
		try {
			airports = objM.readValue(jsonStr, new TypeReference <List<Airport>>() {});
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		
		return airports;
	}

	@Override
	public AircraftType createAircraft(String manufacturer, String model, int seatRows, int seatColumns)
			throws AirlineServiceException {
		
		AircraftType  at= null;
		
		url+= "?action=createAircraft&manufacturer="+manufacturer+"&model="+model+"&seatRows="+seatRows+"&seatColumns="+seatColumns;
		url = url.replaceAll(" ", "%20");
		
        String jsonStr = enviarPeticion(url);
		
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.findAndRegisterModules();
			
		if(statusCode == 200) {
			
			at = objectMapper.readValue(jsonStr, AircraftType.class);
			
		}else if (statusCode == 400) {
			throw new AirlineServiceException (jsonStr);
			
		}else {
			throw new AirlineServiceException ("Error");
		}
		}catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return at;
	}

	@Override
	public List<AircraftType> listAircraftTypes() {
		  List<AircraftType> lista = null;
		     url += "?action=listAircraftTypes";
		     
		     String jsonStr = enviarPeticion(url);
		     
		     try{
		     
		        ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				objectMapper.findAndRegisterModules();
				lista = objectMapper.readValue(jsonStr, new TypeReference<List<AircraftType>>() {});
				

			}catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		     return lista;
			}

	@Override
	public Flight createFlight(String originAirportCode, String destinationAirportCode, Long aircraftTypeCode)
			throws AirlineServiceException {
		
		Flight f = null;
		
		url+= "?action=createFlight&originAirportCode="+originAirportCode+"&destinationAirportCode="+destinationAirportCode+"&AircraftTypeCode="+aircraftTypeCode;
		url = url.replaceAll(" ", "%20");
		
        String jsonStr = enviarPeticion(url);
		
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.findAndRegisterModules();
			
		if(statusCode == 200) {
			
			f = objectMapper.readValue(jsonStr, Flight.class);
			
		}else if (statusCode == 400) {
			throw new AirlineServiceException (jsonStr);
			
		}else {
			throw new AirlineServiceException ("Error");
		}
		}catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return f;
	}

	@Override
	public Flight findFlight(Long flightNumber) {
		
        Flight f = null;
		
		url+= "?action=findFlight&flightNumber="+flightNumber;
		url = url.replaceAll(" ", "%20");
		
        String jsonStr = enviarPeticion(url);
		
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.findAndRegisterModules();
			
		if(statusCode == 200) {
			
			f = objectMapper.readValue(jsonStr, Flight.class);
			
		}
		}catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return f;
	}

	@Override
	public List<Flight> listFlights() {
		 List<Flight> lista = null;
	     url += "?action=listFlights";
	     
	     String jsonStr = enviarPeticion(url);
	     
	     try{
	     
	        ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.findAndRegisterModules();
			lista = objectMapper.readValue(jsonStr, new TypeReference<List<Flight>>() {});
			

		}catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	     return lista;
		}
		
	
	@Override
	public Ticket purchaseTicket(String firstName, String lastName, Long flightNumber, LocalDate purchaseDate,
			LocalDate flightDate) throws AirlineServiceException {
		
        Ticket  t= null;
		
		url+= "?action=purchaseTicket&firstName="+firstName+"&lastName="+lastName+"&flightNumber="+flightNumber+"&purchaseDate="+purchaseDate+"&flightDate="+flightDate;
		url = url.replaceAll(" ", "%20");
		
        String jsonStr = enviarPeticion(url);
		
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.findAndRegisterModules();
			
		if(statusCode == 200) {
			
			t = objectMapper.readValue(jsonStr, Ticket.class);
			
		}else if (statusCode == 400) {
			throw new AirlineServiceException (jsonStr);
			
		}else {
			throw new AirlineServiceException ("Error");
		}
		}catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return t;
	}


	@Override
	public int availableSeats(Long flightNumber, LocalDate flightDate) throws AirlineServiceException {
		
		    int  disponibles= -1;
			
			url+="?action=availableSeats&flightNumber="+flightNumber+"&flightDate="+flightDate.toString();
			url = url.replaceAll(" ", "%20");
			
	        String jsonStr = enviarPeticion(url);
			
			
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				objectMapper.findAndRegisterModules();
				
			if(statusCode == 200) {
				
				disponibles = objectMapper.readValue(jsonStr, Integer.class);
				
			}else if (statusCode == 400) {
				throw new AirlineServiceException (jsonStr);
				
			}else {
				throw new AirlineServiceException ("Error");
			}
			}catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
			return disponibles;
		}
	
	@Override
	public void cancelTicket(Long ticketNumber, LocalDate cancelDate) throws AirlineServiceException {
	
			String completeUrl = "";
			completeUrl = url +"?action=cancelTicket&ticketNumber="+ticketNumber+"&flightDate="+cancelDate;
			completeUrl = completeUrl.replaceAll(" ", "%20");
			
	        String jsonStr = enviarPeticion(completeUrl);
			
				
			if(statusCode == 400) {
				
				throw new AirlineServiceException (jsonStr);
				
			}else {
				throw new AirlineServiceException ("Error");
			}
		}
	

	private  String enviarPeticion (String url){
	      String entrada, respuesta ="";
	      URLConnection connection = null;
	      InputStream is = null;
	      HttpURLConnection httpConn=null;
	     
	      try {
	    	  URL urlEnvio = new URL(url);
	    	  connection = urlEnvio.openConnection();
	    	  is = connection.getInputStream();
		      httpConn = (HttpURLConnection) connection;
		      statusCode = httpConn.getResponseCode();

		       // BufferedReader in = new BufferedReader(new InputStreamReader(urlEnvio.openStream()));
			   BufferedReader in = new BufferedReader(new InputStreamReader(is));

		    	while ((entrada = in.readLine()) != null)
		          respuesta = respuesta + entrada;
		        in.close();
		 
	      }catch (IOException ioe) {
	    	  
			try{
				if (connection instanceof HttpURLConnection) {
				    httpConn = (HttpURLConnection) connection;
				    statusCode = httpConn.getResponseCode();
					respuesta = httpConn.getHeaderField("error");
				}
		      }catch(IOException e1) {
		    	  e1.printStackTrace();
		      }
			 if (statusCode != 200 ){/* or statusCode >= 200 && statusCode < 300 *///) {
			     is = httpConn.getErrorStream();
			   }  
	      }
	      return respuesta;
	
	}
	
}