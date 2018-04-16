package ar.com.mtaboada.magnetodnaanalyzer.core.factory;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import com.google.common.hash.Hashing;

import ar.com.mtaboada.magnetodnaanalyzer.model.AnalysisResult;
import ar.com.mtaboada.magnetodnaanalyzer.model.Dna;
import ar.com.mtaboada.magnetodnaanalyzer.store.model.AnalysisReport;

/**
 * Creates {@link AnalysisReport}
 *
 * @author mtaboada
 *
 */
public class AnalysisReportFactory {

	public AnalysisReport build(final Dna dna, final AnalysisResult result) {
		String sha256hex = Hashing.sha256().hashString(dna.getRows().toString(), StandardCharsets.UTF_8).toString();
		AnalysisReport AnalysisReport = new AnalysisReport(sha256hex, new Date(), dna.getRows(), dna.getMatrixSize(),
				result);
		return AnalysisReport;

	}

}
