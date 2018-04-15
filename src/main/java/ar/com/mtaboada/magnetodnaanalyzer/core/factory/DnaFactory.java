package ar.com.mtaboada.magnetodnaanalyzer.core.factory;

import ar.com.mtaboada.magnetodnaanalyzer.model.Dna;
import ar.com.mtaboada.magnetodnaanalyzer.model.NitrogenBase;

/**
 *
 * @author mtaboada
 *
 */
public class DnaFactory {

	private static final int MIN_TO_DETECT = 4;
	private static final int FIRST = 0;

	public Dna build(final String[] sequence) {

		if (sequence.length == 0) {
			throw new IllegalArgumentException("Invalid Sequence");
		}

		int size = determineSize(sequence);

		NitrogenBase[][] newsequence = new NitrogenBase[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				String base = String.valueOf(sequence[i].charAt(j));
				newsequence[i][j] = NitrogenBase.findByShortcutName(base);
			}
		}

		return new Dna(newsequence, size);

	}

	private int determineSize(final String[] sequence) {
		int size = sequence[FIRST].length();

		validSize(sequence, size);

		return size;
	}

	private void validSize(final String[] sequence, final int size) {
		if (size < MIN_TO_DETECT) {
			throw new IllegalArgumentException("Invalid Sequence");
		}

		for (String row : sequence) {
			if (row.length() != size) {
				throw new IllegalArgumentException("Invalid Sequence");
			}
		}

		if (sequence.length != size) {
			throw new IllegalArgumentException("Invalid Sequence. not n x n");
		}
	}

}
