package ar.com.mtaboada.magnetodnaanalyzer.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.mtaboada.magnetodnaanalyzer.api.dto.StatsDto;
import ar.com.mtaboada.magnetodnaanalyzer.core.analyzer.DnaAnalyzer;
import ar.com.mtaboada.magnetodnaanalyzer.core.factory.AnalysisReportFactory;
import ar.com.mtaboada.magnetodnaanalyzer.core.factory.StatsDtoFactory;
import ar.com.mtaboada.magnetodnaanalyzer.model.AnalysisResult;
import ar.com.mtaboada.magnetodnaanalyzer.model.CountProjection;
import ar.com.mtaboada.magnetodnaanalyzer.model.Dna;
import ar.com.mtaboada.magnetodnaanalyzer.store.dao.AnalysisReportDao;

/**
 *
 * @author mtaboada
 *
 */
@Component
public class AnalysisReportServiceImpl implements AnalysisService {
	private final DnaAnalyzer dnaAnalyzer;
	private final AnalysisReportDao analysisReportDao;

	@Autowired
	public AnalysisReportServiceImpl(@Qualifier("simpleRangeDnaAnalyzer") final DnaAnalyzer dnaAnalyzer,
			final AnalysisReportDao analysisReportDao) {
		this.dnaAnalyzer = dnaAnalyzer;
		this.analysisReportDao = analysisReportDao;
	}

	@Override
	public boolean isMutant(final Dna dna) {
		AnalysisResult analysisResult = dnaAnalyzer.analyze(dna);
		analysisReportDao.saveNotDuplicate(new AnalysisReportFactory().build(dna, analysisResult));
		return analysisResult.isMutant();
	}

	@Override
	public StatsDto getStats() {
		List<CountProjection> counterReport = analysisReportDao.getStatsReport();
		Map<String, Integer> stats = interpretProjection(counterReport);
		return new StatsDtoFactory().build(stats);
	}

	private Map<String, Integer> interpretProjection(List<CountProjection> counterReport) {
		Map<String, Integer> stats = new HashMap<>();
		for (CountProjection countProjection : counterReport) {
			if (!stats.containsKey(countProjection.get_id())) {
				stats.put(countProjection.get_id(), 0);
			}
			stats.put(countProjection.get_id(), stats.get(countProjection.get_id()) + countProjection.getCount());
		}
		return stats;
	}

}
