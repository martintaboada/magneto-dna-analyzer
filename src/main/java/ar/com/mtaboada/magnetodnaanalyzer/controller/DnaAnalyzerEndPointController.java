package ar.com.mtaboada.magnetodnaanalyzer.controller;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mtaboada.magnetodnaanalyzer.api.dto.DnaDto;
import ar.com.mtaboada.magnetodnaanalyzer.api.dto.StatsDto;
import ar.com.mtaboada.magnetodnaanalyzer.core.exception.WithoutOperationToResumeException;
import ar.com.mtaboada.magnetodnaanalyzer.core.factory.DnaFactory;
import ar.com.mtaboada.magnetodnaanalyzer.core.service.AnalysisService;
import ar.com.mtaboada.magnetodnaanalyzer.model.Dna;
import ar.com.mtaboada.magnetodnaanalyzer.view.SimpleMessageView;

/**
 * Magneto's DNA Analyzer API endpoint
 *
 * @author mtaboada
 *
 */
@RestController
public class DnaAnalyzerEndPointController {

	private final AnalysisService analysisService;

	@Autowired
	public DnaAnalyzerEndPointController(final AnalysisService analysisService) {
		this.analysisService = analysisService;
	}

	/**
	 * Determines whether a DNA is mutant or not
	 */
	@RequestMapping(value = "/mutant", method = POST)
	public ResponseEntity<SimpleMessageView> analyze(@RequestBody final DnaDto dnaDto) {

		DnaFactory dnaFactory = new DnaFactory();
		Dna dnaMapped = dnaFactory.build(dnaDto.getDna());

		if (!analysisService.isMutant(dnaMapped)) {
			return new ResponseEntity<>(new SimpleMessageView(FORBIDDEN.value(), "Is a simple human DNA"), FORBIDDEN);
		}

		return new ResponseEntity<>(new SimpleMessageView(OK.value(), "Is a mutant DNA!"), OK);

	}

	/**
	 * Report number of analyzes performed
	 */
	@RequestMapping(value = "/stats", method = GET)
	public StatsDto getStats() {
		return analysisService.getStats();
	}

	/**
	 * Handle illegalArgumentException when dna parameter is not valid
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<SimpleMessageView> illegalDnaHandler(final IllegalArgumentException e) {
		return standardErrorReport(e);
	}

	/**
	 * Handle {@link WithoutOperationToResumeException}. Occurs when there is no
	 * analysis information
	 */
	@ExceptionHandler(WithoutOperationToResumeException.class)
	public ResponseEntity<SimpleMessageView> withoutOperationToResumeException(
			final WithoutOperationToResumeException e) {
		return new ResponseEntity<>(new SimpleMessageView(NOT_FOUND.value(), "Without statistic"), NOT_FOUND);
	}

	/**
	 * Handle unexpected error
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<SimpleMessageView> errorHandler(final Exception e) {
		return standardErrorReport(e);
	}

	private ResponseEntity<SimpleMessageView> standardErrorReport(final Exception e) {
		return new ResponseEntity<>(new SimpleMessageView(INTERNAL_SERVER_ERROR.value(), e.getMessage()),
				INTERNAL_SERVER_ERROR);
	}

}
