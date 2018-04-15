package ar.com.mtaboada.magnetodnaanalyzer.core.analyzer;

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
		return null;
	}

}
