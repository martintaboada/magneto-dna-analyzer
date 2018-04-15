package ar.com.mtaboada.magnetodnaanalyzer.core.analyzer;

import static ar.com.mtaboada.magnetodnaanalyzer.model.AnalysisResult.HUMAN;
import static ar.com.mtaboada.magnetodnaanalyzer.model.AnalysisResult.MUTANT;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ar.com.mtaboada.magnetodnaanalyzer.core.searcher.MutantDnaSearcher;
import ar.com.mtaboada.magnetodnaanalyzer.model.Dna;

/**
 * Coverage to {@link MutantDnaSearcher}. Guarantees the detection of DNA mutant
 * s
 *
 * @author mtaboada
 *
 */
@Ignore
public class GreedyDnaAnalyzerTest {
	private static final Dna NORMAL_DNA = new Dna(null, 0);
	private static final Dna NORMAL_DNA_WITH_ONE_SEQUENCE = new Dna(null, 0);
	private static final Dna MUTANT_DNA_HORIZONTAL_DETECT_ONLY = new Dna(null, 0);
	private static final Dna MUTANT_DNA_VERTICAL_DETECT_ONLY = new Dna(null, 0);
	private static final Dna MUTANT_DNA_DIAGONAL_DETECT_ONLY = new Dna(null, 0);
	private static final Dna MUTANT_DNA_MIXED_DETECT = new Dna(null, 0);

	private GreedyDnaAnalyzer sut;

	@Before
	public void setup() {
		sut = new GreedyDnaAnalyzer();
	}

	@Test
	public void canDetectNormalDna() {
		assertEquals(HUMAN, sut.analyze(NORMAL_DNA));
	}

	@Test
	public void whenHasDnaWithOneSequenceThenDnaIsNormal() {
		assertEquals(HUMAN, sut.analyze(NORMAL_DNA_WITH_ONE_SEQUENCE));
	}

	@Test
	public void whenHasDnaWithTwoMutantSequenceHorizontalThenDnaIsMutant() {
		assertEquals(MUTANT, sut.analyze(MUTANT_DNA_HORIZONTAL_DETECT_ONLY));
	}

	@Test
	public void whenHasDnaWithTwoMutantSequenceVerticalThenDnaIsMutant() {
		assertEquals(MUTANT, sut.analyze(MUTANT_DNA_VERTICAL_DETECT_ONLY));
	}

	@Test
	public void whenHasDnaWithTwoMutantSequenceDiagonalThenDnaIsMutant() {
		assertEquals(MUTANT, sut.analyze(MUTANT_DNA_DIAGONAL_DETECT_ONLY));
	}

	@Test
	public void whenHasDnaWithTwoMutantSequenceMixedThenDnaIsMutant() {
		assertEquals(MUTANT, sut.analyze(MUTANT_DNA_MIXED_DETECT));
	}

}
