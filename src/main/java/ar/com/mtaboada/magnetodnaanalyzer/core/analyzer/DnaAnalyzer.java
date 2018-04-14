package ar.com.mtaboada.magnetodnaanalyzer.core.analyzer;

import ar.com.mtaboada.magnetodnaanalyzer.model.AnalysisResult;
import ar.com.mtaboada.magnetodnaanalyzer.model.Dna;

/**
 *
 * @author mtaboada
 *
 */
public interface DnaAnalyzer {

	AnalysisResult analyze(Dna dna);

}
