package ar.com.mtaboada.magnetodnaanalyzer.core.analyzer;

import static ar.com.mtaboada.magnetodnaanalyzer.model.AnalysisResult.HUMAN;
import static ar.com.mtaboada.magnetodnaanalyzer.model.AnalysisResult.MUTANT;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import ar.com.mtaboada.magnetodnaanalyzer.model.AnalysisResult;
import ar.com.mtaboada.magnetodnaanalyzer.model.Dna;
import ar.com.mtaboada.magnetodnaanalyzer.model.NitrogenBase;

/**
 *
 * @author mtaboada
 *
 */
@Primary
@Component
public class SimpleRangeDnaAnalyzer implements DnaAnalyzer {

	private static final String GUANINE_PATTERN = "GGGG";
	private static final String CYTOKINE_PATTERN = "CCCC";
	private static final String THYMINE_PATTERN = "TTTT";
	private static final String ADENINE_PATTERN = "AAAA";
	private static final int MUTANT_PATTERN_SIZE = 4;
	private static final int MIN_MUTANT_PATTERN_QUANTITY = 2;

	@Override
	public AnalysisResult analyze(final Dna dna) {

		int mutantFlag = 0;

		mutantFlag += searchPattern(dna.getRows());
		if (mutantFlag < MIN_MUTANT_PATTERN_QUANTITY) {
			mutantFlag += searchPattern(dna.getColumns());
		}
		if (mutantFlag < MIN_MUTANT_PATTERN_QUANTITY) {
			mutantFlag += searchPattern(dna.getDiagonals(MUTANT_PATTERN_SIZE));
		}

		return mutantFlag >= MIN_MUTANT_PATTERN_QUANTITY ? MUTANT : HUMAN;
	}

	private int searchPattern(final List<List<NitrogenBase>> nitrogenBases) {

		int mutantPatternOccurencies = 0;

		for (int i = 0; (i < nitrogenBases.size()) && (mutantPatternOccurencies < MIN_MUTANT_PATTERN_QUANTITY); i++) {
			StringBuilder sb = new StringBuilder();
			for (NitrogenBase nitrogenBase : nitrogenBases.get(i)) {
				sb.append(nitrogenBase.getCharacter());
			}
			if (isPattern(sb.toString())) {
				mutantPatternOccurencies++;
			}
		}

		return mutantPatternOccurencies;

	}

	private boolean isPattern(final String nitrogenBasesAsString) {
		return (nitrogenBasesAsString.contains(ADENINE_PATTERN) || nitrogenBasesAsString.contains(THYMINE_PATTERN)
				|| nitrogenBasesAsString.contains(CYTOKINE_PATTERN) || nitrogenBasesAsString.contains(GUANINE_PATTERN));
	}
}
