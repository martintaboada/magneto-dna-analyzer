package ar.com.mtaboada.magnetodnaanalyzer.factory;

import static ar.com.mtaboada.magnetodnaanalyzer.model.AnalysisResult.HUMAN;
import static ar.com.mtaboada.magnetodnaanalyzer.model.AnalysisResult.MUTANT;
import static java.lang.Integer.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import ar.com.mtaboada.magnetodnaanalyzer.core.factory.AnalysisReportFactory;
import ar.com.mtaboada.magnetodnaanalyzer.store.model.AnalysisReport;
import ar.com.mtaboada.magnetodnaanalyzer.testutils.SequenceBuilderTestUtils;

/**
 *
 * @author mtaboada
 *
 */
public class AnalysisReportFactoryTest {

	private static final String EXPECTED_HASH = "bc00d003c7e40177b93784377853b46ae5ad6fd6f8af470e733114d050962f34";
	private AnalysisReportFactory sut;

	@Before
	public void setup() {
		sut = new AnalysisReportFactory();
	}

	@Test
	public void whenHasADnaThenCanBuildReport() {
		AnalysisReport report = sut.build(SequenceBuilderTestUtils.getValidDna(), HUMAN);
		assertNotNull(report);
		assertNotNull(report.getTimestamp());
		assertNotNull(report.getSize());
		assertEquals(valueOf(6), report.getSize());
	}

	@Test
	public void whenHasAHumanDnaThenReportShowThisOk() {
		AnalysisReport report = sut.build(SequenceBuilderTestUtils.getValidDna(), HUMAN);
		assertEquals(HUMAN, report.getReportStatus());
	}

	@Test
	public void whenHasAMutantnDnaThenReportShowThisOk() {
		AnalysisReport report = sut.build(SequenceBuilderTestUtils.getValidDna(), MUTANT);
		assertEquals(MUTANT, report.getReportStatus());
	}

	@Test
	public void whenHasDnaOkThenCanGetHash() {
		AnalysisReport report = sut.build(SequenceBuilderTestUtils.getValidDna(), MUTANT);
		assertTrue(StringUtils.isNotBlank(report.getDnaHash()));
		assertEquals(EXPECTED_HASH, report.getDnaHash());
	}

}
