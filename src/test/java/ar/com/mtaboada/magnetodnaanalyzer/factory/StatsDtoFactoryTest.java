package ar.com.mtaboada.magnetodnaanalyzer.factory;

import static java.lang.Integer.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ar.com.mtaboada.magnetodnaanalyzer.api.dto.StatsDto;
import ar.com.mtaboada.magnetodnaanalyzer.core.exception.WithoutOperationToResumeException;
import ar.com.mtaboada.magnetodnaanalyzer.core.factory.StatsDtoFactory;
import ar.com.mtaboada.magnetodnaanalyzer.model.AnalysisResult;

/**
 *
 * @author mtaboada
 *
 */
public class StatsDtoFactoryTest {

	private StatsDtoFactory sut;

	@Before
	public void setup() {
		sut = new StatsDtoFactory();
	}

	@Test(expected = WithoutOperationToResumeException.class)
	public void whenNotHasInfoThenThrowException() {
		sut.build(null);
	}

	@Test(expected = WithoutOperationToResumeException.class)
	public void whenNotHasInfoIntoMapThenThrowException() {
		sut.build(new HashMap<>());
	}

	@Test
	public void whenHasDataThenCanGetReportOK() {
		// Setup
		Map<String, Integer> statsAsMap = new HashMap<>();
		statsAsMap.put(AnalysisResult.HUMAN.toString(), 100);
		statsAsMap.put(AnalysisResult.MUTANT.toString(), 40);

		// Exercise
		StatsDto result = sut.build(statsAsMap);

		// Verify
		assertNotNull(result);
		assertEquals(valueOf(100), result.getCountHumanDna());
		assertEquals(valueOf(40), result.getCountMutantDna());
	}

	@Test
	public void whenHasDataInBothCategoriesThenCanGetReportWithRatioOK() {
		// Setup
		Map<String, Integer> statsAsMap = new HashMap<>();
		statsAsMap.put(AnalysisResult.HUMAN.toString(), 100);
		statsAsMap.put(AnalysisResult.MUTANT.toString(), 40);

		// Exercise
		StatsDto result = sut.build(statsAsMap);

		// Verify
		assertEquals(valueOf(100), result.getCountHumanDna());
		assertEquals(valueOf(40), result.getCountMutantDna());
		assertTrue(result.getRatio() == 0.40);
	}

	@Test
	public void whenHasDataInHumanCategoryThenCanGetReportWithRatio0OnMutantOK() {
		// Setup
		Map<String, Integer> statsAsMap = new HashMap<>();
		statsAsMap.put(AnalysisResult.HUMAN.toString(), 100);
		statsAsMap.put(AnalysisResult.MUTANT.toString(), 0);

		// Exercise
		StatsDto result = sut.build(statsAsMap);

		// Verify
		assertEquals(valueOf(100), result.getCountHumanDna());
		assertEquals(valueOf(0), result.getCountMutantDna());
		assertTrue(result.getRatio() == 0.0);
	}

	@Test
	public void whenHasDataInMutantCategoryThenCanGetReportWithRatio1OnMutantOK() {
		// Setup
		Map<String, Integer> statsAsMap = new HashMap<>();
		statsAsMap.put(AnalysisResult.HUMAN.toString(), 0);
		statsAsMap.put(AnalysisResult.MUTANT.toString(), 200);

		// Exercise
		StatsDto result = sut.build(statsAsMap);

		// Verify
		assertEquals(valueOf(0), result.getCountHumanDna());
		assertEquals(valueOf(200), result.getCountMutantDna());
		assertTrue(result.getRatio() == 1.0);
	}

	/**
	 * functionally not possible
	 */
	@Test
	public void whenHasDataInBothtCategoriesButAreZeroThenCanGetReportWithRatio0OnOK() {
		// Setup
		Map<String, Integer> statsAsMap = new HashMap<>();
		statsAsMap.put(AnalysisResult.HUMAN.toString(), 0);
		statsAsMap.put(AnalysisResult.MUTANT.toString(), 0);

		// Exercise
		StatsDto result = sut.build(statsAsMap);

		// Verify
		assertEquals(valueOf(0), result.getCountHumanDna());
		assertEquals(valueOf(0), result.getCountMutantDna());
		assertTrue(result.getRatio() == 0);
	}
}
