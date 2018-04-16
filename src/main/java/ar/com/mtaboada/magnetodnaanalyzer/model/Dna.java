package ar.com.mtaboada.magnetodnaanalyzer.model;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

/**
 * Business Model representation of DNA Has internal representation (sequence)
 * of {@link NitrogenBase} Matrix
 *
 * @author mtaboada
 *
 */
public class Dna {
	private final NitrogenBase[][] sequence;
	private final int matrixSize;

	public Dna(final NitrogenBase[][] sequence, final int matrixSize) {
		this.sequence = sequence;
		this.matrixSize = matrixSize;
	}

	/**
	 * Get internal representation of dna sequence
	 */
	public NitrogenBase[][] getSequence() {
		return sequence;
	}

	/**
	 * Determine N of matrix of NxN
	 *
	 */
	public int getMatrixSize() {
		return matrixSize;
	}

	/**
	 * Get rows of dna sequence. Is original input
	 */
	public List<List<NitrogenBase>> getRows() {
		List<List<NitrogenBase>> rows = new ArrayList<>();
		for (int i = 0; i < matrixSize; i++) {
			NitrogenBase[] nitrogenBasesRow = sequence[i];
			rows.add(asList(nitrogenBasesRow));
		}
		return rows;
	}

	/**
	 * Get columns of dna sequence
	 */
	public List<List<NitrogenBase>> getColumns() {
		List<List<NitrogenBase>> columns = new ArrayList<>();
		for (int i = 0; i < matrixSize; i++) {
			final int col = i;
			columns.add(stream(sequence).map(o -> o[col]).collect(toList()));
		}
		return columns;
	}

	/**
	 * Get diagonals with size has defined by param. Example, minSize is mutant
	 * pattern quantity.
	 */
	public List<List<NitrogenBase>> getDiagonals(final int minSize) {
		List<List<NitrogenBase>> diagonals = new ArrayList<>();

		// LEFT TO RIGHT - Column moving (/)
		for (int s = 0; s < matrixSize; s++) {
			if (s >= ((matrixSize - minSize) + 1)) {
				List<NitrogenBase> diagonal = new ArrayList<>();
				for (int i = s; i > -1; i--) {
					diagonal.add(sequence[i][s - i]);
				}
				diagonals.add(diagonal);
			}
		}

		// LEFT TO RIGHT - Row moving (/)
		for (int s = 1; s < matrixSize; s++) {
			if (s < ((matrixSize - minSize) + 1)) {
				List<NitrogenBase> diagonal = new ArrayList<>();
				for (int i = matrixSize - 1; i >= s; i--) {
					diagonal.add(sequence[i][(s + matrixSize) - 1 - i]);
				}
				diagonals.add(diagonal);
			}
		}

		// RIGHT TO LEFT - Column moving (\)
		for (int i = 0; i < matrixSize; i++) {
			if ((matrixSize - i) < minSize) {
				List<NitrogenBase> diagonal = new ArrayList<>();
				for (int s = matrixSize - 1; s >= (matrixSize - 1 - i); s--) {
					diagonal.add(sequence[i - (matrixSize - 1 - s)][s]);
				}
				diagonals.add(diagonal);
			}
		}

		// RIGHT TO LEFT - Row moving (\)
		for (int s = 0; s < (matrixSize - 1); s++) {
			if (s > (matrixSize - minSize)) {
				List<NitrogenBase> diagonal = new ArrayList<>();
				for (int i = matrixSize - 1; i >= (matrixSize - s - 1); i--) {
					diagonal.add(sequence[i][s - (matrixSize - 1 - i)]);
				}
				diagonals.add(diagonal);
			}
		}

		return diagonals;
	}
}
