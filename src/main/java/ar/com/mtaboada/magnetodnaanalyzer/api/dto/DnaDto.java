package ar.com.mtaboada.magnetodnaanalyzer.api.dto;

import java.io.Serializable;

/**
 *
 * @author mtaboada
 *
 */
public class DnaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String[] dna;

	public String[] getDna() {
		return dna;
	}

	public void setDna(final String[] dna) {
		this.dna = dna;
	}

}
