package ar.com.mtaboada.magnetodnaanalyzer.controller;

import static ar.com.mtaboada.magnetodnaanalyzer.model.AnalysisResult.HUMAN;
import static ar.com.mtaboada.magnetodnaanalyzer.model.AnalysisResult.MUTANT;
import static ar.com.mtaboada.magnetodnaanalyzer.testutils.SequenceBuilderTestUtils.getValidHumanDnaDto;
import static ar.com.mtaboada.magnetodnaanalyzer.testutils.SequenceBuilderTestUtils.getValidMutantDnaDto;
import static java.lang.Integer.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ar.com.mtaboada.magnetodnaanalyzer.core.analyzer.DnaAnalyzer;
import ar.com.mtaboada.magnetodnaanalyzer.model.Dna;
import ar.com.mtaboada.magnetodnaanalyzer.view.SimpleMessageView;

/**
 *
 * @author mtaboada
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class DnaAnalyzerEndPointControllerTest {

	private static final String EXPECTED_MESSAGE_TO_HUMAN = "Is a simple human DNA";
	private static final String EXPECTED_MESSAGE_TO_MUTANT = "Is a mutant DNA!";
	private DnaAnalyzerEndPointController sut;
	@Mock
	private DnaAnalyzer dnaAnalyzer;

	@Before
	public void setup() {
		sut = new DnaAnalyzerEndPointController(dnaAnalyzer);
	}

	/**
	 * Test functional specification about message code. Positive case
	 */
	@Test
	public void whenHasAMutantDnaThenReturnHttpStatus200() {
		// Setup
		when(dnaAnalyzer.analyze(any(Dna.class))).thenReturn(MUTANT);

		// Exercise
		ResponseEntity<SimpleMessageView> result = sut.analyze(getValidMutantDnaDto());

		// Verify
		assertEquals(HttpStatus.OK, result.getStatusCode());
		// Validate extras
		assertNotNull(result.getBody());
		assertEquals(EXPECTED_MESSAGE_TO_MUTANT, result.getBody().getMessage());
		assertEquals(Integer.valueOf(HttpStatus.OK.value()), result.getBody().getCode());
	}

	/**
	 * Test functional specification about message code. Negative case
	 */
	@Test
	public void whenHasAHumanDnaThenReturnHttpStatus403() {
		// Setup
		when(dnaAnalyzer.analyze(any(Dna.class))).thenReturn(HUMAN);

		// Exercise
		ResponseEntity<SimpleMessageView> result = sut.analyze(getValidHumanDnaDto());

		// Verify
		assertEquals(FORBIDDEN, result.getStatusCode());
		// Validate extras
		assertNotNull(result.getBody());
		assertEquals(EXPECTED_MESSAGE_TO_HUMAN, result.getBody().getMessage());
		assertEquals(valueOf(FORBIDDEN.value()), result.getBody().getCode());
	}

	@Test
	public void whenHasErroThenReturnAMessageWithStatusCode500() {
		// Exercise
		ResponseEntity<SimpleMessageView> result = sut.illegalDnaHandle(new IllegalArgumentException("any message"));
		// Verify
		assertEquals(INTERNAL_SERVER_ERROR, result.getStatusCode());
		// Validate extras
		assertNotNull(result.getBody());
		assertEquals("any message", result.getBody().getMessage());
		assertEquals(valueOf(INTERNAL_SERVER_ERROR.value()), result.getBody().getCode());
	}

}
