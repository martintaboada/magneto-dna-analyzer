package ar.com.mtaboada.magnetodnaanalyzer.store.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import ar.com.mtaboada.magnetodnaanalyzer.model.AnalysisResult;
import ar.com.mtaboada.magnetodnaanalyzer.model.NitrogenBase;

/**
 * Store class to a dna analysis
 *
 * @author mtaboada
 *
 */
@Entity(value = "log", noClassnameStored = true)
public class AnalysisReport implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private ObjectId id;
	private String dnaHash;
	private Date timestamp;
	private List<List<NitrogenBase>> dna;
	private Integer size;
	private AnalysisResult reportStatus;

	public AnalysisReport() {
	}

	public AnalysisReport(final String dnaHash, final Date timestamp, final List<List<NitrogenBase>> dna,
			final Integer size, final AnalysisResult reportStatus) {
		this.dnaHash = dnaHash;
		this.timestamp = timestamp;
		this.dna = dna;
		this.size = size;
		this.reportStatus = reportStatus;
	}

	public String getDnaHash() {
		return dnaHash;
	}

	public void setDnaHash(final String dnaHash) {
		this.dnaHash = dnaHash;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(final Date timestamp) {
		this.timestamp = timestamp;
	}

	public List<List<NitrogenBase>> getDna() {
		return dna;
	}

	public void setDna(final List<List<NitrogenBase>> dna) {
		this.dna = dna;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(final Integer size) {
		this.size = size;
	}

	public AnalysisResult getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(final AnalysisResult reportStatus) {
		this.reportStatus = reportStatus;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(final ObjectId id) {
		this.id = id;
	}

}
