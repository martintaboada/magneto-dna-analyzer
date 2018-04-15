package ar.com.mtaboada.magnetodnaanalyzer.api.endpoint;

import java.util.Date;

import org.springframework.http.ResponseEntity;

/**
 * @author mtaboada
 */
public interface HeartbeatEndPoint {
	/**
	 * Salud de la API. Muestra que la misma se encuentra viva
	 */
	ResponseEntity<Date> healthCheck();
}
