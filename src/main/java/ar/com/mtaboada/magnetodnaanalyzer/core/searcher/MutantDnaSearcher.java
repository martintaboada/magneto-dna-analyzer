package ar.com.mtaboada.magnetodnaanalyzer.core.searcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.mtaboada.magnetodnaanalyzer.core.analyzer.DnaAnalyzer;
import ar.com.mtaboada.magnetodnaanalyzer.model.Dna;

/**
 *
 * @author mtaboada
 *
 */
@Component
public class MutantDnaSearcher {

	private final DnaAnalyzer dnaAnalyzer;

	@Autowired
	public MutantDnaSearcher(final DnaAnalyzer dnaAnalyzer) {
		this.dnaAnalyzer = dnaAnalyzer;
	}

	public boolean isMutant(final Dna dna) {
		return dnaAnalyzer.analyze(dna).isMutant();
	}

}
