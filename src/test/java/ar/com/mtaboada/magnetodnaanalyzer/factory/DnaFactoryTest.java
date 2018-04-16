package ar.com.mtaboada.magnetodnaanalyzer.factory;

import static ar.com.mtaboada.magnetodnaanalyzer.model.NitrogenBase.ADENINE;
import static ar.com.mtaboada.magnetodnaanalyzer.model.NitrogenBase.CYTOKINE;
import static ar.com.mtaboada.magnetodnaanalyzer.model.NitrogenBase.GUANINE;
import static ar.com.mtaboada.magnetodnaanalyzer.model.NitrogenBase.THYMINE;
import static ar.com.mtaboada.magnetodnaanalyzer.testutils.SequenceBuilderTestUtils.VALID_HUMAN_SEQUENCE_N6;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ar.com.mtaboada.magnetodnaanalyzer.core.factory.DnaFactory;
import ar.com.mtaboada.magnetodnaanalyzer.model.Dna;
import ar.com.mtaboada.magnetodnaanalyzer.model.NitrogenBase;
import ar.com.mtaboada.magnetodnaanalyzer.testutils.SequenceBuilderTestUtils;

/**
 *
 * @author mtaboada
 *
 */
public class DnaFactoryTest {

	private static final int MIN_SIZE = 4;
	private static final String[] A_VALID_SEQUENCE = VALID_HUMAN_SEQUENCE_N6;
	private DnaFactory sut;

	@Before
	public void setup() {
		sut = new DnaFactory();
	}

	@Test
	public void whenHasAValidSequenceThenCanDetermineSizeAndBuildDna() {
		int expectedSize = 6;
		Dna result = sut.build(A_VALID_SEQUENCE);
		assertNotNull(result);
		assertNotNull(result.getSequence());
		assertTrue(result.getMatrixSize() > MIN_SIZE);
		assertEquals(expectedSize, result.getSequence().length);
	}

	@Test
	public void whenHasAValidSequenceOfN6ThenCanBuildDnaOk() {
		Dna result = sut.build(VALID_HUMAN_SEQUENCE_N6);
		int expectedSize = 6;
		assertEquals(expectedSize, result.getSequence().length);
	}

	@Test
	public void whenHasAValidSequenceOfN8ThenCanBuildDnaOk() {
		Dna result = sut.build(SequenceBuilderTestUtils.VALID_HUMAN_SEQUENCE_N8);
		int expectedSize = 8;
		assertEquals(expectedSize, result.getSequence().length);
	}

	@Test
	public void whenHasAValidSequenceFromMutantThenCanBuildDnaOk() {
		Dna result = sut.build(SequenceBuilderTestUtils.VALID_MUTANT_SEQUENCE_DIAGONAL);
		int expectedSize = 6;
		assertEquals(expectedSize, result.getSequence().length);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenHasAEmptySequenceCantBuildDna() {
		sut.build(SequenceBuilderTestUtils.INVALID_EMPTY_SEQUENCE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenHasAInvalidSequenceWithDiferentRowSizeCantBuildDna() {
		sut.build(SequenceBuilderTestUtils.INVALID_SEQUENCE_ROW_INVALID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenHasAInvalidSequenceWithDiferentColumnSizeCantBuildDna() {
		sut.build(SequenceBuilderTestUtils.INVALID_SEQUENCE_COLUMN_INVALID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenHasAInvalidSequenceWithInvalidCharCantBuildDna() {
		sut.build(SequenceBuilderTestUtils.INVALID_SEQUENCE_CHAR_INVALID);
	}

	@Test
	public void integralTest_whenHasValidDataThenCanGetDnaInCorrectOrder() {
		// Setup
		NitrogenBase[][] expectedSequence = { { ADENINE, THYMINE, ADENINE, THYMINE, ADENINE, THYMINE },
				{ GUANINE, CYTOKINE, GUANINE, GUANINE, CYTOKINE, GUANINE },
				{ ADENINE, THYMINE, ADENINE, THYMINE, ADENINE, THYMINE },
				{ ADENINE, THYMINE, ADENINE, THYMINE, ADENINE, THYMINE },
				{ CYTOKINE, GUANINE, CYTOKINE, GUANINE, CYTOKINE, GUANINE },
				{ ADENINE, THYMINE, ADENINE, THYMINE, ADENINE, THYMINE } };

		// Exercise
		Dna result = sut.build(VALID_HUMAN_SEQUENCE_N6);
		NitrogenBase[][] sequence = result.getSequence();

		// Verify
		assertArrayEquals(expectedSequence, sequence);
	}
}
