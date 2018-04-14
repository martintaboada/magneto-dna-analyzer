package ar.com.mtaboada.magnetodnaanalyzer.model;

/**
 *
 * @author mtaboada
 *
 */
public class Dna {

	private final NitrogenBase[][] sequence;

	public Dna(final NitrogenBase[][] sequence) {
		this.sequence = sequence;
	}

	public NitrogenBase[][] getSequence() {
		return sequence;
	}

}
