package ar.com.mtaboada.magnetodnaanalyzer.core.factory;

import static org.apache.commons.lang3.math.NumberUtils.DOUBLE_ZERO;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.util.Map;

import ar.com.mtaboada.magnetodnaanalyzer.api.dto.StatsDto;
import ar.com.mtaboada.magnetodnaanalyzer.core.exception.WithoutOperationToResumeException;
import ar.com.mtaboada.magnetodnaanalyzer.model.AnalysisResult;

/**
 *
 * @author mtaboada
 *
 */
public class StatsDtoFactory {

	public StatsDto build(final Map<String, Integer> stats) {
		if ((stats == null) || stats.isEmpty()) {
			throw new WithoutOperationToResumeException();
		}
		StatsDto statsDto = new StatsDto();
		statsDto.setCountHumanDna(stats.get(AnalysisResult.HUMAN.toString()));
		statsDto.setCountMutantDna(stats.get(AnalysisResult.MUTANT.toString()));
		if (statsDto.getCountHumanDna().equals(INTEGER_ZERO) && statsDto.getCountMutantDna().equals(INTEGER_ZERO)) {
			statsDto.setRatio(DOUBLE_ZERO);
		}
		if (statsDto.getCountHumanDna().equals(INTEGER_ZERO) && !statsDto.getCountMutantDna().equals(INTEGER_ZERO)) {
			statsDto.setRatio(1);
		}
		if (!statsDto.getCountHumanDna().equals(INTEGER_ZERO) && !statsDto.getCountMutantDna().equals(INTEGER_ZERO)) {
			statsDto.setRatio((double) statsDto.getCountMutantDna() / (double) statsDto.getCountHumanDna());

		}
		return statsDto;
	}
}
