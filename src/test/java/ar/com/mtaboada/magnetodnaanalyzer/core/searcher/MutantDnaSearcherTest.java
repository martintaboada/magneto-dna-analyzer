package ar.com.mtaboada.magnetodnaanalyzer.core.searcher;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import ar.com.mtaboada.magnetodnaanalyzer.core.analyzer.DnaAnalyzer;
import ar.com.mtaboada.magnetodnaanalyzer.model.AnalysisResult;
import ar.com.mtaboada.magnetodnaanalyzer.model.Dna;

/**
 * Coverage to {@link MutantDnaSearcher}. Guarantees the detection of DNA mutant
 *
 * @author mtaboada
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class MutantDnaSearcherTest {

	private static final Dna NORMAL_DNA = new Dna(null, 0);
	private static final Dna NORMAL_DNA_WITH_ONE_SEQUENCE = new Dna(null, 0);
	private static final Dna MUTANT_DNA_HORIZONTAL_DETECT_ONLY = new Dna(null, 0);
	private static final Dna MUTANT_DNA_VERTICAL_DETECT_ONLY = new Dna(null, 0);
	private static final Dna MUTANT_DNA_DIAGONAL_DETECT_ONLY = new Dna(null, 0);
	private static final Dna MUTANT_DNA_MIXED_DETECT = new Dna(null, 0);

	private MutantDnaSearcher sut;

	@Mock
	private DnaAnalyzer dnaAnalyzer;

	@Before
	public void setup() {
		sut = new MutantDnaSearcher(dnaAnalyzer);
	}

	@Test
	public void canDetectNormalDna() {
		when(dnaAnalyzer.analyze(any(Dna.class))).thenReturn(AnalysisResult.HUMAN);
		assertFalse(sut.isMutant(NORMAL_DNA));
	}

	@Test
	public void whenHasDnaWithOneSequenceThenDnaIsNormal() {
		when(dnaAnalyzer.analyze(any(Dna.class))).thenReturn(AnalysisResult.HUMAN);
		assertFalse(sut.isMutant(NORMAL_DNA_WITH_ONE_SEQUENCE));
	}

	@Test
	public void whenHasDnaWithTwoMutantSequenceHorizontalThenDnaIsMutant() {
		when(dnaAnalyzer.analyze(any(Dna.class))).thenReturn(AnalysisResult.MUTANT);
		assertTrue(sut.isMutant(MUTANT_DNA_HORIZONTAL_DETECT_ONLY));
	}

	@Test
	public void whenHasDnaWithTwoMutantSequenceVerticalThenDnaIsMutant() {
		when(dnaAnalyzer.analyze(any(Dna.class))).thenReturn(AnalysisResult.MUTANT);
		assertTrue(sut.isMutant(MUTANT_DNA_VERTICAL_DETECT_ONLY));
	}

	@Test
	public void whenHasDnaWithTwoMutantSequenceDiagonalThenDnaIsMutant() {
		when(dnaAnalyzer.analyze(any(Dna.class))).thenReturn(AnalysisResult.MUTANT);
		assertTrue(sut.isMutant(MUTANT_DNA_DIAGONAL_DETECT_ONLY));
	}

	@Test
	public void whenHasDnaWithTwoMutantSequenceMixedThenDnaIsMutant() {
		when(dnaAnalyzer.analyze(any(Dna.class))).thenReturn(AnalysisResult.MUTANT);
		assertTrue(sut.isMutant(MUTANT_DNA_MIXED_DETECT));
	}

}
