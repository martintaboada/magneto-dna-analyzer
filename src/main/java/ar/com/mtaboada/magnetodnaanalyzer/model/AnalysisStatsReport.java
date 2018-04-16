package ar.com.mtaboada.magnetodnaanalyzer.model;

/**
 *
 * @author mtaboada
 *
 */
public class AnalysisStatsReport {

	private final int humanQuantity;
	private final int mutantQUantity;

	public AnalysisStatsReport(final int humanQuantity, final int mutantQUantity) {
		this.humanQuantity = humanQuantity;
		this.mutantQUantity = mutantQUantity;
	}

	public int getHumanQuantity() {
		return humanQuantity;
	}

	public int getMutantQUantity() {
		return mutantQUantity;
	}

}
