package ar.com.mtaboada.magnetodnaanalyzer.core.service;

import ar.com.mtaboada.magnetodnaanalyzer.api.dto.StatsDto;
import ar.com.mtaboada.magnetodnaanalyzer.model.Dna;

/**
 *
 * @author mtaboada
 *
 */
public interface AnalysisService {

	boolean isMutant(Dna dna);

	StatsDto getStats();

}
