package paa.airline.business;
import java.time.LocalDate;
import java.util.List;


@SuppressWarnings("serial")
public class AirlineServiceException extends Exception {

	public AirlineServiceException(String format) {
        super(format);
    }
}
