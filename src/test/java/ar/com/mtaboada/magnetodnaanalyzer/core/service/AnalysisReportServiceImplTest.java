package ar.com.mtaboada.magnetodnaanalyzer.core.service;

import static ar.com.mtaboada.magnetodnaanalyzer.model.AnalysisResult.HUMAN;
import static ar.com.mtaboada.magnetodnaanalyzer.model.AnalysisResult.MUTANT;
import static java.lang.Integer.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import ar.com.mtaboada.magnetodnaanalyzer.api.dto.StatsDto;
import ar.com.mtaboada.magnetodnaanalyzer.core.analyzer.DnaAnalyzer;
import ar.com.mtaboada.magnetodnaanalyzer.model.CountProjection;
import ar.com.mtaboada.magnetodnaanalyzer.model.Dna;
import ar.com.mtaboada.magnetodnaanalyzer.store.dao.AnalysisReportDao;
import ar.com.mtaboada.magnetodnaanalyzer.store.model.AnalysisReport;
import ar.com.mtaboada.magnetodnaanalyzer.testutils.SequenceBuilderTestUtils;

/**
 *
 * @author mtaboada
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AnalysisReportServiceImplTest {

	@Mock
	private AnalysisReportDao analysisReportDao;
	@Mock
	private DnaAnalyzer dnaAnalyzer;
	private AnalysisReportServiceImpl sut;

	@Before
	public void setup() {
		sut = new AnalysisReportServiceImpl(dnaAnalyzer, analysisReportDao);
	}

	/**
	 * Functional Test. Requirement test. Test isMutant(...). Mutant Case
	 */
	@Test
	public void whenHasDnaMutantThenCanGetTrueFromServices() {
		when(dnaAnalyzer.analyze(ArgumentMatchers.any(Dna.class))).thenReturn(MUTANT);
		assertTrue(sut.isMutant(SequenceBuilderTestUtils.getValidDna()));
	}

	/**
	 * Functional Test. Requirement test. Test isMutant(...). Human Case
	 */
	@Test
	public void whenHasDnaHumanThenCanGetTrueFromServices() {
		when(dnaAnalyzer.analyze(ArgumentMatchers.any(Dna.class))).thenReturn(HUMAN);
		assertFalse(sut.isMutant(SequenceBuilderTestUtils.getValidDna()));
	}

	@Test
	public void whenHasDnaChecklThemAndStoreResult() {
		when(dnaAnalyzer.analyze(ArgumentMatchers.any(Dna.class))).thenReturn(MUTANT);
		assertTrue(sut.isMutant(SequenceBuilderTestUtils.getValidDna()));
		verify(analysisReportDao, times(1)).saveNotDuplicate(ArgumentMatchers.any(AnalysisReport.class));
	}

	@Test
	public void serviceCanGetAReport() {
		// Setup
		List<CountProjection> listOfProjection = new LinkedList<>();
		CountProjection countProjectionHuman = new CountProjection();
		countProjectionHuman.set_id(HUMAN.toString());
		countProjectionHuman.setCount(10);
		CountProjection countProjectionMutant = new CountProjection();
		countProjectionMutant.set_id(MUTANT.toString());
		countProjectionMutant.setCount(5);
		listOfProjection.add(countProjectionHuman);
		listOfProjection.add(countProjectionMutant);
		when(analysisReportDao.getStatsReport()).thenReturn(listOfProjection);
		// Exercise
		StatsDto result = sut.getStats();
		// Verify
		Assert.assertNotNull(result);
		assertEquals(valueOf(10), result.getCountHumanDna());
		assertEquals(valueOf(5), result.getCountMutantDna());
		assertTrue(result.getRatio() == 0.5);

	}

}
