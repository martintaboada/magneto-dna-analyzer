package ar.com.mtaboada.magnetodnaanalyzer.core.analyzer;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

import ar.com.mtaboada.magnetodnaanalyzer.model.AnalysisResult;
import ar.com.mtaboada.magnetodnaanalyzer.model.Dna;

/**
 *
 * @author mtaboada
 *
 */
@Component
public class GreedyDnaAnalyzer implements DnaAnalyzer {

	@Override
	public AnalysisResult analyze(final Dna dna) {
		throw new NotImplementedException("Without implementation");
	}

}
