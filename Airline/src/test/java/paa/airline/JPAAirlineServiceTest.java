 package paa.airline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import paa.airline.business.AirlineServiceException;
import paa.airline.business.RemoteAirlineService;
import paa.airline.model.AircraftType;
import paa.airline.model.Airport;
import paa.airline.model.Flight;
import paa.airline.model.Ticket;




@TestMethodOrder(OrderAnnotation.class) 
class JPAAirlineServiceTest {

/*	@Test
	 @Order(1)
	void A_testCreateAirportOK() {
		JPAAirlineService service = new JPAAirlineService();
		try {
			service.createAirport("MAD", "Madrid", "Barajas", 0, 0);
		} catch (AirlineServiceException e) {
			fail("No deberia saltar la excepcion.");
		}

		List<Airport> listaAeropuertos = service.listAirports();
		assertSame(listaAeropuertos.size(), 1);
	}

	@Test
	 @Order(2)
	void B_testCreateAirportNOK() {
		JPAAirlineService service = new JPAAirlineService();
		// Check longitude
		try {
			service.createAirport("MAD", "Madrid", "Barajas", 500, 0);
			fail("No deberia saltar la excepcion.");
		} catch (AirlineServiceException e) {
		}
		// Check latitude
		try {
			service.createAirport("MAD", "Madrid", "Barajas", 0, 5000);
			fail("No deberia saltar la excepcion.");
		} catch (AirlineServiceException e) {
		}

		
		try {
			service.createAirport("MAD", null, "Barajas", 0, 0);
			fail("No deberia saltar la excepcion.");
		} catch (AirlineServiceException e) {
		}

		try {
			service.createAirport("MAD", "        ", "Barajas", 0, 0);
			fail("No deberia saltar la excepcion.");
		} catch (AirlineServiceException e) {
		}

		try {
			service.createAirport("MAD", "", "Barajas", 0, 0);
			fail("No deberia saltar la excepcion.");
		} catch (AirlineServiceException e) {
		}

		
		try {
			service.createAirport("MAD", "Madrid", null, 0, 0);
			fail("No deberia saltar la excepcion.");
		} catch (AirlineServiceException e) {
		}

		try {
			service.createAirport("MAD", "Madrid", "       ", 0, 0);
			fail("No deberia saltar la excepcion.");
		} catch (AirlineServiceException e) {
		}

		try {
			service.createAirport("MAD", "Madrid", "", 0, 0);
			fail("No deberia saltar la excepcion.");
		} catch (AirlineServiceException e) {
		}

		
		try {
			service.createAirport("madrid", "Madrid", "Barajas", 0, 0);
			fail("Deberia saltar la excepcion ya que el codigo es mad");
		} catch (AirlineServiceException e) {
		}

		try {
			service.createAirport("M3D", "Madrid", "Barajas", 0, 0);
			fail("Deberia saltar la excepcion ya que el codigo es M3D");
		} catch (AirlineServiceException e) {
		}
	}

	@Test
	 @Order(3)
	void C_testListAirports() {
		JPAAirlineService service = new JPAAirlineService();
		try {
			service.createAirport("MAD", "Madrid", "Barajas", 0, 0);
			service.createAirport("BCN", "Barcelona", "Prat", 10, 10);

		} catch (AirlineServiceException e) {
			fail("No deberia saltar la excepcion.");
		}

		List<Airport> listaAeropuertos = service.listAirports();
		assertSame(listaAeropuertos.size(), 2);
	}

	@Test
	 @Order(4)
	void D_testCreateAircraftNOK() {
		JPAAirlineService service = new JPAAirlineService();
		
		try {
			service.createAircraft(null, "Modelo1", 1, 1);
		} catch (AirlineServiceException e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "[ERROR] manufacturer no valido");
		}
		try {
			service.createAircraft("", "Modelo1", 1, 1);
		} catch (AirlineServiceException e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "[ERROR] manufacturer no valido");
		}
		try {
			service.createAircraft("      ", "Modelo1", 1, 1);
		} catch (AirlineServiceException e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "[ERROR] manufacturer no valido");
		}

		// Model
		try {
			service.createAircraft("Fabricante1", null, 1, 1);
		} catch (AirlineServiceException e) {
			assertEquals(e.getMessage(), "[ERROR] model no valido");
		}
		try {
			service.createAircraft("Fabricante1", "", 1, 1);
		} catch (AirlineServiceException e) {
			assertEquals(e.getMessage(), "[ERROR] model no valido");
		}
		try {
			service.createAircraft("Fabricante1", "    ", 1, 1);
		} catch (AirlineServiceException e) {
			assertEquals(e.getMessage(), "[ERROR] model no valido");
		}

		// SeatRow
		try {
			service.createAircraft("Fabricante1", "Modelo1", -1, 1);
		} catch (AirlineServiceException e) {
			assertEquals(e.getMessage(), "[ERROR] seatRows no valido");
		}
		try {
			service.createAircraft("Fabricante1", "Modelo1", 6, 20);
		} catch (AirlineServiceException e) {
			assertEquals(e.getMessage(), "[ERROR] seatRows no valido");
		}

		// SeatColumns
		try {
			service.createAircraft("Fabricante1", "Modelo1", 1, -1);
		} catch (AirlineServiceException e) {
			assertEquals(e.getMessage(), "[ERROR] seatColumns no valido");
		}
	}

	@Test
	 @Order(5)
	void E_testCreateAircraftOK() {
		JPAAirlineService service = new JPAAirlineService();
		AircraftType at = null;
		try {
			at = service.createAircraft("Fabricante1", "Modelo1", 20, 6);
			assertNotNull(at);
			assertNotNull(at.getId());
		} catch (AirlineServiceException e) {
			// TODO Auto-generated catch block
			fail("'Create Aircraft' no deberia saltar la exepcion");
		}
	}

	@Test
	 @Order(6)
	void F_testListAircraftTypes() {
		JPAAirlineService service = new JPAAirlineService();
		AircraftType at = null;

		try {
			at = service.createAircraft("Fabricante1", "Modelo1", 20, 6);
			assertNotNull(at);
			assertNotNull(at.getId());

			at = service.createAircraft("Fabricante1", "Modelo1", 20, 6);
			assertNotNull(at);
			assertNotNull(at.getId());

			List<AircraftType> listaAviones = service.listAircraftTypes();
			assertSame(listaAviones.size(), 2);

		} catch (AirlineServiceException e) {
			// TODO Auto-generated catch block
			fail("'Create Aircraft' no deberia saltar la exepcion");
		}
	}

	@Test
	 @Order(7)
	void G_testCreateFlightOK() {
		JPAAirlineService service = new JPAAirlineService();
		
		try {
			service.createFlight("MAD", "BCN", 1L);
		} catch (AirlineServiceException e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "[ERROR] originAirportCode no exite");
		}

		
		AircraftType at = null;
		try {
			at = service.createAircraft("Fabricante1", "Modelo1", 20, 6);
			assertNotNull(at);
			assertNotNull(at.getId());
			service.createFlight("MAD", "BCN", at.getId());
		} catch (AirlineServiceException e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "[ERROR] originAirportCode no existe");
		}

		
		Airport origen;
		try {
			origen = service.createAirport("MAD", "Madrid", "Barajas", 0, 0);
			assertNotNull(origen);
			service.createFlight("MAD", "BCN", at.getId());
		} catch (AirlineServiceException e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "[ERROR] destinationAirportCode no exite");
		}

		
		try {
			service.createFlight("MAD", "MAD", at.getId());
		} catch (AirlineServiceException e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "[ERROR] El aeropuerto de origen  debe ser distinto al de destino");
		}
	}

	@Test
	 @Order(8)
	void H_testCreateFlightOK() {

		JPAAirlineService service = new JPAAirlineService();
		Airport origen = null;
		Airport destino = null;
		AircraftType at = null;
		try {

			origen = service.createAirport("MAD", "Madrid", "Barajas", 0, 0);
			assertNotNull(origen);

			destino = service.createAirport("BCN", "Barcelona", "Prat", 0, 0);
			assertNotNull(destino);

			at = service.createAircraft("Fabricante1", "Modelo1", 20, 6);
			assertNotNull(at);
			assertNotNull(at.getId());

			service.createFlight(origen.getIataCode(), destino.getIataCode(), at.getId());

		} catch (AirlineServiceException e) {
			fail("No deberia saltar la excepcion");
		}
	}

	@Test
	 @Order(9)
	void I_testFindFlight() {

		JPAAirlineService service = new JPAAirlineService();

		Flight f = service.findFlight(1L);
		assertNull(f);

		Airport origen = null;
		Airport destino = null;
		AircraftType at = null;
		try {

			origen = service.createAirport("MAD", "Madrid", "Barajas", 0, 0);
			assertNotNull(origen);

			destino = service.createAirport("BCN", "Barcelona", "Prat", 10, 10);
			assertNotNull(destino);

			at = service.createAircraft("Fabricante1", "Modelo1", 4, 2);
			assertNotNull(at);
			assertNotNull(at.getId());

			f = service.createFlight(origen.getIataCode(), destino.getIataCode(), at.getId());
			assertNotNull(f);

		} catch (AirlineServiceException e) {
			fail("No deberia saltar la excepcion");
		}

		f = service.findFlight(f.getFlightNumber());
		assertNotNull(f);
	}

	@Test
	 @Order(10)
	void J_testListFlights() {

		JPAAirlineService service = new JPAAirlineService();

		Flight f = service.findFlight(1L);
		assertNull(f);

		Airport origen = null;
		Airport destino = null;
		AircraftType at = null;
		try {

			origen = service.createAirport("MAD", "Madrid", "Barajas", 0, 0);
			assertNotNull(origen);

			destino = service.createAirport("BCN", "Barcelona", "Prat", 10, 10);
			assertNotNull(destino);

			at = service.createAircraft("Fabricante2", "Modelo2", 4, 2);
			assertNotNull(at);
			assertNotNull(at.getId());

			f = service.createFlight(origen.getIataCode(), destino.getIataCode(), at.getId());
			assertNotNull(f);

			List<Flight> listaVuelos = service.listFlights();
			assertSame(listaVuelos.size(), 1);

		} catch (AirlineServiceException e) {
			fail("No deberia saltar la excepcion");
		}
	}

	@Test
	 @Order(11)
	void K_purchaseTicketTestNOK() {

		JPAAirlineService service = new JPAAirlineService();

		Airport origen = null;
		Airport destino = null;
		AircraftType at = null;
		Flight f = null;

		try {

			origen = service.createAirport("MAD", "Madrid", "Barajas", 0, 0);
			assertNotNull(origen);

			destino = service.createAirport("BCN", "Barcelona", "Prat", 10, 10);
			assertNotNull(destino);

			at = service.createAircraft("Fabricante2", "Modelo2", 2, 1);
			assertNotNull(at);
			assertNotNull(at.getId());

			f = service.createFlight(origen.getIataCode(), destino.getIataCode(), at.getId());
			assertNotNull(f);

			Ticket t = service.purchaseTicket(null, "Bermejo", f.getFlightNumber(), LocalDate.now(),
					LocalDate.now().plusDays(15));

		} catch (AirlineServiceException e) {
			assertEquals(e.getMessage(), "[ERROR] Nombre no valido");
		}

		try {
			Ticket t = service.purchaseTicket("", "Bermejo", f.getFlightNumber(), LocalDate.now(),
					LocalDate.now().plusDays(15));

		} catch (AirlineServiceException e) {
			assertEquals(e.getMessage(), "[ERROR] Nombre no valido");
		}

		try {
			Ticket t = service.purchaseTicket("    ", "Bermejo", f.getFlightNumber(), LocalDate.now(),
					LocalDate.now().plusDays(15));

		} catch (AirlineServiceException e) {
			assertEquals(e.getMessage(), "[ERROR] Nombre no valido");
		}

		try {
			Ticket t = service.purchaseTicket("Iker", null, f.getFlightNumber(), LocalDate.now(),
					LocalDate.now().plusDays(15));

		} catch (AirlineServiceException e) {
			assertEquals(e.getMessage(), "[ERROR] Apellido no valido");
		}

		try {
			Ticket t = service.purchaseTicket("Iker", "", f.getFlightNumber(), LocalDate.now(),
					LocalDate.now().plusDays(15));

		} catch (AirlineServiceException e) {
			assertEquals(e.getMessage(), "[ERROR] Apellido no valido");
		}

		try {
			Ticket t = service.purchaseTicket("Iker", "    ", f.getFlightNumber(), LocalDate.now(),
					LocalDate.now().plusDays(15));

		} catch (AirlineServiceException e) {
			assertEquals(e.getMessage(), "[ERROR] Apellido no valido");
		}

		try {
			Ticket t = service.purchaseTicket("Iker", "Bermejo", 500L, LocalDate.now(),
					LocalDate.now().plusDays(15));

		} catch (AirlineServiceException e) {
			assertEquals(e.getMessage(), "[ERROR] El vuelo seleccionado no existe.");
		}

		try {
			Ticket t = service.purchaseTicket("Iker", "Bermejo", f.getFlightNumber(), LocalDate.now(), null);

		} catch (AirlineServiceException e) {
			assertEquals(e.getMessage(), "[ERROR] flightDate no valido.");
		}

		try {
			Ticket t = service.purchaseTicket("Iker", "Bermejo", f.getFlightNumber(), LocalDate.now(),
					LocalDate.now().minusDays(1));

		} catch (AirlineServiceException e) {
			assertEquals(e.getMessage(), "[ERROR] La fecha de compra debe ser anterior a la del vuelo seleccionado.");
		}

		try {
			Ticket t = service.purchaseTicket("Iker", "Bermejo", f.getFlightNumber(), LocalDate.now(),
					LocalDate.now().plusDays(1));

			t = service.purchaseTicket("Iker", "Bermejo", f.getFlightNumber(), LocalDate.now(),
					LocalDate.now().plusDays(1));

			t = service.purchaseTicket("Iker", "Bermejo", f.getFlightNumber(), LocalDate.now(),
					LocalDate.now().plusDays(1));
			fail("Deberia saltar la excepcion");
		} catch (AirlineServiceException e) {
			assertEquals(e.getMessage(), "[ERROR] No hay asientos disponibles.");
		}
	}

	@Test
	 @Order(12)
	void L_availableSeats() {

		JPAAirlineService service = new JPAAirlineService();

		Flight f = service.findFlight(1L);
		assertNull(f);

		Airport origen = null;
		Airport destino = null;
		AircraftType at = null;
		try {

			origen = service.createAirport("MAD", "Madrid", "Barajas", 0, 0);
			assertNotNull(origen);

			destino = service.createAirport("BCN", "Barcelona", "Prat", 10, 10);
			assertNotNull(destino);

			at = service.createAircraft("Fabricante2", "Modelo2", 4, 2);
			assertNotNull(at);
			assertNotNull(at.getId());

			f = service.createFlight(origen.getIataCode(), destino.getIataCode(), at.getId());
			assertNotNull(f);

			List<Flight> listaVuelos = service.listFlights();
			assertSame(listaVuelos.size(), 1);

			Ticket t = service.purchaseTicket("Iker", "Bermejo", f.getFlightNumber(), LocalDate.now(),
					LocalDate.now().plusDays(15));
			assertNotNull(t);

			
			int numAsientosDisponibles = service.availableSeats(f.getFlightNumber(), LocalDate.now().plusDays(15));
			assertSame(numAsientosDisponibles, 7);

		} catch (AirlineServiceException e) {
			fail("No deberia saltar la excepcion");
		}
	}

	@Test
	 @Order(13)
	void L_cancelTicket() {

		JPAAirlineService service = new JPAAirlineService();

		Flight f = service.findFlight(1L);
		assertNull(f);

		Airport origen = null;
		Airport destino = null;
		AircraftType at = null;
		try {

			origen = service.createAirport("MAD", "Madrid", "Barajas", 0, 0);
			assertNotNull(origen);

			destino = service.createAirport("BCN", "BARCELONA", "El Prat", 10, 10);
			assertNotNull(destino);

			at = service.createAircraft("Frabricante2", "modelo2", 4, 2);
			assertNotNull(at);
			assertNotNull(at.getId());

			f = service.createFlight(origen.getIataCode(), destino.getIataCode(), at.getId());
			assertNotNull(f);
			
			Ticket t = service.purchaseTicket("Iker", "Bermejo", f.getFlightNumber(), LocalDate.now(),
					LocalDate.of(2023, 4, 4));
			service.cancelTicket(t.getTicketNumber(), LocalDate.of(2023, 4, 3));
			int sitiosDisponibles = service.availableSeats(f.getFlightNumber(), LocalDate.of(2023, 4, 4));
			assertSame(sitiosDisponibles, 8);

		} catch (AirlineServiceException e) {
			fail("No deberia saltar la excepcion");
		}

	}
*/
	@Test
	@Order(14)
	void L_purchase() {
		
		RemoteAirlineService service = new RemoteAirlineService();
		
		Flight f = service.findFlight(1L);
		assertNull(f);
		Flight f2 =null;
		Airport origen = null;
		Airport destino = null;
		Airport destino2 = null;

		AircraftType at = null;
		AircraftType at2 = null;
		try {
			
			//Aeopuertos				
			origen = service.createAirport("MAD", "Madrid", "Barajas",-3.555484, 40.494138);
			assertNotNull(origen);
			
			destino = service.createAirport("BCN", "BARCELONA", "El Prat", 2.076929, 41.305838);
			assertNotNull(destino);
			
			destino2 = service.createAirport("FIJ", "FIJI", "San Pablo", 178.220479, -17.706920);//-17.706920, 178.220479

			//aviones
			at = service.createAircraft("Frabricante2", "modelo2", 4, 2);
			assertNotNull(at);
			assertNotNull(at.getId());
			at2 = service.createAircraft("Frabricante3", "modelo3", 4, 2);
			assertNotNull(at2);
			assertNotNull(at2.getId());
			
			//vuelo
			f= service.createFlight(origen.getIataCode(), destino.getIataCode(), at.getId());//mad-BCN
			assertNotNull(f);
			f2=service.createFlight(origen.getIataCode(), destino2.getIataCode(), at2.getId());//MAD-FIJ
			assertNotNull(f2);
			
			//Crear ticket
			Ticket t= service.purchaseTicket("Maria", "Juarranz", f.getFlightNumber(), LocalDate.now(),LocalDate.of(2023, 5, 16)) ;//hoy-16mayo (mad-bcn)
			t= service.purchaseTicket("Pepito", "Perez", f.getFlightNumber(), LocalDate.now(),LocalDate.of(2023, 5, 17)) ;//hoy-16mayo (mad-bcn)

			 t= service.purchaseTicket("Maria", "Juarranz", f2.getFlightNumber(), LocalDate.now(),LocalDate.of(2023, 4, 4)) ;//hoy-4abril (mad-bcn)
			assertNotNull(t);
		//	t= service.purchaseTicket("Maria", "Juarranz", f.getFlightNumber(), LocalDate.now(),LocalDate.of(2023, 5, 16)) ;//hoy-16mayo (mad-bcn)
			assertNotNull(t);
		//	t= service.purchaseTicket("Maria", "Juarranz", f.getFlightNumber(), LocalDate.now(),LocalDate.of(2023, 5, 16)) ;//hoy-16mayo (mad-bcn)

		} catch (AirlineServiceException e) {
			fail("No deberia saltar la excepcion");
		}
		
	}
}