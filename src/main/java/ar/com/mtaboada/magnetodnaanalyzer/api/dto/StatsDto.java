package ar.com.mtaboada.magnetodnaanalyzer.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents resumes to current status from DNA validations For example,
 * serialize JSON as: {count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}
 *
 *
 * @author mtaboada
 *
 */
public class StatsDto implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("count_mutant_dna")
	private Integer countMutantDna;
	@JsonProperty("count_human_dna")
	private Integer countHumanDna;
	private double ratio;

	public StatsDto() {
	}

	public StatsDto(final Integer countMutantDna, final Integer countHumanDna, final double ratio) {
		this.countMutantDna = countMutantDna;
		this.countHumanDna = countHumanDna;
		this.ratio = ratio;
	}

	public Integer getCountMutantDna() {
		return countMutantDna;
	}

	public void setCountMutantDna(final Integer countMutantDna) {
		this.countMutantDna = countMutantDna;
	}

	public Integer getCountHumanDna() {
		return countHumanDna;
	}

	public void setCountHumanDna(final Integer countHumanDna) {
		this.countHumanDna = countHumanDna;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(final double ratio) {
		this.ratio = ratio;
	}

}