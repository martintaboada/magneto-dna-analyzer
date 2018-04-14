package ar.com.mtaboada.magnetodnaanalyzer.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author mtaboada
 *
 */
public class AnalysisResultTest {

	@Test
	public void whenDetectHumanThenNotMutant() {
		assertFalse(AnalysisResult.HUMAN.isMutant());
	}

	@Test
	public void whenDetectMutantThenMutantFlagIsTrue() {
		assertTrue(AnalysisResult.MUTANT.isMutant());
	}
}
