package ar.com.mtaboada.magnetodnaanalyzer.controller;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mtaboada.magnetodnaanalyzer.api.dto.DnaDto;
import ar.com.mtaboada.magnetodnaanalyzer.core.analyzer.DnaAnalyzer;
import ar.com.mtaboada.magnetodnaanalyzer.core.factory.DnaFactory;
import ar.com.mtaboada.magnetodnaanalyzer.model.AnalysisResult;
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

	private final DnaAnalyzer dnaAnalyzer;

	@Autowired
	public DnaAnalyzerEndPointController(@Qualifier("simpleRangeDnaAnalyzer") final DnaAnalyzer dnaAnalyzer) {
		this.dnaAnalyzer = dnaAnalyzer;
	}

	/**
	 * Determines whether a DNA is mutant or not
	 */
	@RequestMapping(value = "/mutant", method = POST)
	public ResponseEntity<SimpleMessageView> analyze(@RequestBody final DnaDto dnaDto) {

		DnaFactory dnaFactory = new DnaFactory();
		Dna dnaMapped = dnaFactory.build(dnaDto.getDna());

		AnalysisResult analysisResult = dnaAnalyzer.analyze(dnaMapped);
		if (!analysisResult.isMutant()) {
			return new ResponseEntity<>(new SimpleMessageView(FORBIDDEN.value(), "Is a simple human DNA"), FORBIDDEN);
		}

		return new ResponseEntity<>(new SimpleMessageView(OK.value(), "Is a mutant DNA!"), OK);

	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<SimpleMessageView> illegalDnaHandle(final IllegalArgumentException e) {
		return new ResponseEntity<>(new SimpleMessageView(INTERNAL_SERVER_ERROR.value(), e.getMessage()),
				INTERNAL_SERVER_ERROR);
	}

}
