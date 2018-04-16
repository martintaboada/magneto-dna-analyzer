package ar.com.mtaboada.magnetodnaanalyzer.model;

import static ar.com.mtaboada.magnetodnaanalyzer.testutils.SequenceBuilderTestUtils.getValidDna;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import ar.com.mtaboada.magnetodnaanalyzer.testutils.SequenceBuilderTestUtils;

/**
 *
 * @author mtaboada
 *
 */
public class DnaTest {
	private static final Dna DNA = getValidDna();
	private static final String EXPECTED_ROWS = "[[ADENINE, THYMINE, ADENINE, THYMINE, ADENINE, THYMINE], [GUANINE, CYTOKINE, GUANINE, GUANINE, CYTOKINE, GUANINE], [ADENINE, THYMINE, ADENINE, THYMINE, ADENINE, THYMINE], [ADENINE, THYMINE, ADENINE, THYMINE, ADENINE, THYMINE], [CYTOKINE, GUANINE, CYTOKINE, GUANINE, CYTOKINE, GUANINE], [ADENINE, THYMINE, ADENINE, THYMINE, ADENINE, THYMINE]]";
	private static final String EXPECTED_COLUMNS = "[[ADENINE, GUANINE, ADENINE, ADENINE, CYTOKINE, ADENINE], [THYMINE, CYTOKINE, THYMINE, THYMINE, GUANINE, THYMINE], [ADENINE, GUANINE, ADENINE, ADENINE, CYTOKINE, ADENINE], [THYMINE, GUANINE, THYMINE, THYMINE, GUANINE, THYMINE], [ADENINE, CYTOKINE, ADENINE, ADENINE, CYTOKINE, ADENINE], [THYMINE, GUANINE, THYMINE, THYMINE, GUANINE, THYMINE]]";
	private static final String EXPECTED_DIAGONALS = "[[ADENINE, THYMINE, GUANINE, THYMINE], [CYTOKINE, THYMINE, ADENINE, GUANINE, ADENINE], [ADENINE, GUANINE, ADENINE, THYMINE, CYTOKINE, THYMINE], [THYMINE, CYTOKINE, THYMINE, ADENINE, GUANINE], [ADENINE, GUANINE, ADENINE, THYMINE], [THYMINE, ADENINE, GUANINE, ADENINE], [GUANINE, ADENINE, THYMINE, GUANINE, THYMINE], [THYMINE, CYTOKINE, THYMINE, ADENINE, CYTOKINE, ADENINE], [THYMINE, CYTOKINE, THYMINE, ADENINE], [ADENINE, GUANINE, ADENINE, THYMINE, GUANINE]]";

	@Test
	public void givenADnaCanGetInternalRepresentationOk() {
		// Setup

		assertNotNull(DNA.getSequence());
		assertArrayEquals(SequenceBuilderTestUtils.A_VALID_SEQUENCE, DNA.getSequence());

	}

	@Test
	public void givenADnaCanGetNSizeOfInternalRepresentationk() {
		assertNotNull(DNA.getMatrixSize());
		assertEquals(6, DNA.getMatrixSize());
	}

	@Test
	public void givenADnaCanGetInternalRepresentationAsListOfRowsOk() {
		// Exercise
		List<List<NitrogenBase>> rows = DNA.getRows();

		// Verify
		assertNotNull(rows);
		assertFalse(rows.isEmpty());
		assertEquals(6, rows.size());
		assertEquals(EXPECTED_ROWS, rows.toString());
	}

	@Test
	public void givenADnaCanGetInternalRepresentationAsListOfColumnsOk() {
		// Exercise
		List<List<NitrogenBase>> columns = DNA.getColumns();

		// Verify
		assertNotNull(columns);
		assertFalse(columns.isEmpty());
		assertEquals(6, columns.size());
		assertEquals(EXPECTED_COLUMNS, columns.toString());
	}

	@Test
	public void givenADnaCanGetInternalRepresentationAsListOfDiagonalsOk() {
		// Exercise
		List<List<NitrogenBase>> diagonals = DNA.getDiagonals(4);

		// Verify
		assertNotNull(diagonals);
		assertFalse(diagonals.isEmpty());
		assertEquals(10, diagonals.size());
		assertEquals(EXPECTED_DIAGONALS, diagonals.toString());
	}

}
