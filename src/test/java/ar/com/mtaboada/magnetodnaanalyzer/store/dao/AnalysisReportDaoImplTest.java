package ar.com.mtaboada.magnetodnaanalyzer.store.dao;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mongodb.morphia.Datastore;

import ar.com.mtaboada.magnetodnaanalyzer.store.model.AnalysisReport;

/**
 *
 * @author mtaboada
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AnalysisReportDaoImplTest {

	@Mock
	private Datastore datastore;

	@Test
	public void whenSaveAAnalysisReportThenConnectToDataBaseOK() {
		AnalysisReportDaoImpl sut = new AnalysisReportDaoImpl(datastore);
		sut.save(new AnalysisReport());
		verify(datastore, times(1)).save(any(AnalysisReport.class));
	}

}
