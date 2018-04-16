package ar.com.mtaboada.magnetodnaanalyzer.store.dao;

import static org.mongodb.morphia.aggregation.Accumulator.accumulator;
import static org.mongodb.morphia.aggregation.Group.grouping;
import static org.mongodb.morphia.aggregation.Projection.projection;

import java.util.LinkedList;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.aggregation.AggregationPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.mtaboada.magnetodnaanalyzer.model.CountProjection;
import ar.com.mtaboada.magnetodnaanalyzer.store.model.AnalysisReport;

/**
 *
 * @author mtaboada
 *
 */
@Component
public class AnalysisReportDaoImpl implements AnalysisReportDao {
	private final Datastore datastore;

	@Autowired
	public AnalysisReportDaoImpl(final Datastore datastore) {
		this.datastore = datastore;
	}

	@Override
	public void save(final AnalysisReport analysisReport) {
		datastore.save(analysisReport);
	}

	@Override
	public void saveNotDuplicate(final AnalysisReport analysisReport) {
		AnalysisReport storedReport = datastore.createQuery(AnalysisReport.class).field("dnaHash")
				.equal(analysisReport.getDnaHash()).get();
		if (storedReport == null) {
			save(analysisReport);
		}
	}

	@Override
	public List<CountProjection> getStatsReport() {

		AggregationPipeline pipCount = datastore.createAggregation(AnalysisReport.class)
				.group("reportStatus", grouping("count", accumulator("$sum", 1))).project(projection("count"));
		List<CountProjection> results = new LinkedList<>();
		pipCount.aggregate(CountProjection.class).forEachRemaining(results::add);
		return results;

	}

}
