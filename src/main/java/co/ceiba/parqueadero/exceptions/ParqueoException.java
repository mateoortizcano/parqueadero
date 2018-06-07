package co.ceiba.parqueadero.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParqueoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ParqueoException(String message) {
		super(message);
	}
	
}
