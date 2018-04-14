package ar.com.mtaboada.magnetodnaanalyzer.model;

/**
 *
 * @author mtaboada
 *
 */
public enum AnalysisResult {
	HUMAN, MUTANT;

	public boolean isMutant() {
		return equals(MUTANT);
	}
}
