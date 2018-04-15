package ar.com.mtaboada.magnetodnaanalyzer.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author mtaboada
 *
 */
public class ApiInfoControllerTest {

	@Test
	public void canGetHeartBeatFromApplicationOk() {
		ApiInfoController sut = new ApiInfoController();
		ResponseEntity<Date> result = sut.healthCheck();
		assertNotNull(result);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

}
