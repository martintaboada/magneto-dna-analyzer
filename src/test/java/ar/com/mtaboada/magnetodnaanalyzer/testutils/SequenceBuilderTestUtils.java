package ar.com.mtaboada.magnetodnaanalyzer.testutils;

import static ar.com.mtaboada.magnetodnaanalyzer.model.NitrogenBase.ADENINE;
import static ar.com.mtaboada.magnetodnaanalyzer.model.NitrogenBase.CYTOKINE;
import static ar.com.mtaboada.magnetodnaanalyzer.model.NitrogenBase.GUANINE;
import static ar.com.mtaboada.magnetodnaanalyzer.model.NitrogenBase.THYMINE;

import ar.com.mtaboada.magnetodnaanalyzer.api.dto.DnaDto;
import ar.com.mtaboada.magnetodnaanalyzer.model.Dna;
import ar.com.mtaboada.magnetodnaanalyzer.model.NitrogenBase;

/**
 * Only test class. Build standard input to dna sequence
 *
 * @author mtaboada
 *
 */
public class SequenceBuilderTestUtils {

	public final static String[] VALID_HUMAN_SEQUENCE_N6 = { "ATATAT", "GCGGCG", "ATATAT", "ATATAT", "CGCGCG",
			"ATATAT" };
	public final static String[] VALID_MUTANT_SEQUENCE_N6 = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA",
			"TCACTG" };
	public final static String[] VALID_HUMAN_SEQUENCE_N8 = { "ATATATAT", "GCGGCGGC", "ATATATAT", "ATATATAT", "CGCGCGGC",
			"ATATATAT", "GCGGCGGC", "ATATATAT" };
	public final static String[] VALID_MUTANT_SEQUENCE_N8 = { "ATGCGACC", "CAGTGCCC", "TTATGTCC", "AGAAGGCC",
			"CCCCTACC", "TCACTGCC" };
	public final static String[] VALID_MUTANT_SEQUENCE_HORIZONTAL = { "ATCGCC", "CTCGCA", "ATATCA", "ATCGCC", "CTCGCA",
			"ACATCA" };
	public final static String[] VALID_MUTANT_SEQUENCE_VERTICAL = { "ATGCGA", "AAGTGC", "ATACGT", "AGACGG", "CCCCTA",
			"TCACTG" };
	public final static String[] VALID_MUTANT_SEQUENCE_DIAGONAL = { "ACGTGA", "CAGTGC", "TCATGT", "AGCAGG", "CACCTA",
			"TCACTG" };
	public final static String[] VALID_MUTANT_SEQUENCE_MIXED = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA",
			"TCACTG" };
	public final static String[] INVALID_SEQUENCE_ROW_INVALID = { "AAAAA", "CGATTT", "AACG", "GTAAAA", "ATCGCA",
			"CAACTG" };
	public final static String[] INVALID_SEQUENCE_COLUMN_INVALID = { "AAAAA", "CGATTT", "AACGAC", "GTAAAA", "ATCGCA" };
	public final static String[] INVALID_SEQUENCE_CHAR_INVALID = { "AAAAA", "CGATTT", "AACA2G", "GTAAAA", "ATCBCA",
			"CAACTG" };
	public final static String[] INVALID_EMPTY_SEQUENCE = {};

	public final static NitrogenBase[][] A_VALID_SEQUENCE = { { ADENINE, THYMINE, ADENINE, THYMINE, ADENINE, THYMINE },
			{ GUANINE, CYTOKINE, GUANINE, GUANINE, CYTOKINE, GUANINE },
			{ ADENINE, THYMINE, ADENINE, THYMINE, ADENINE, THYMINE },
			{ ADENINE, THYMINE, ADENINE, THYMINE, ADENINE, THYMINE },
			{ CYTOKINE, GUANINE, CYTOKINE, GUANINE, CYTOKINE, GUANINE },
			{ ADENINE, THYMINE, ADENINE, THYMINE, ADENINE, THYMINE } };

	public static DnaDto getValidMutantDnaDto() {
		DnaDto result = new DnaDto();
		result.setDna(VALID_MUTANT_SEQUENCE_N6);
		return result;
	}

	public static DnaDto getValidHumanDnaDto() {
		DnaDto result = new DnaDto();
		result.setDna(VALID_HUMAN_SEQUENCE_N6);
		return result;
	}

	public static Dna getValidDna() {
		return new Dna(A_VALID_SEQUENCE, 6);
	}

}
