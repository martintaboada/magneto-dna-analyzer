package ar.com.mtaboada.magnetodnaanalyzer.store.dao;

import java.util.List;

import ar.com.mtaboada.magnetodnaanalyzer.model.CountProjection;
import ar.com.mtaboada.magnetodnaanalyzer.store.model.AnalysisReport;

/**
 *
 * @author mtaboada
 *
 */
public interface AnalysisReportDao {

	void save(AnalysisReport analysisReport);

	void saveNotDuplicate(AnalysisReport analysisReport);

	List<CountProjection> getStatsReport();

}
