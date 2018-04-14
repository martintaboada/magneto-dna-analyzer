package ar.com.mtaboada.magnetodnaanalyzer.model;

import static ar.com.mtaboada.magnetodnaanalyzer.model.NitrogenBase.ADENINE;
import static ar.com.mtaboada.magnetodnaanalyzer.model.NitrogenBase.CYTOKINE;
import static ar.com.mtaboada.magnetodnaanalyzer.model.NitrogenBase.GUANINE;
import static ar.com.mtaboada.magnetodnaanalyzer.model.NitrogenBase.THYMINE;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author mtaboada
 *
 */
public class NitrogenBaseTest {
	private static final String ADENINE_AS_STRING = "A";
	private static final String GUANINE_AS_STRING = "G";
	private static final String THYMINE_AS_STRING = "T";
	private static final String CYTOKINE_AS_STRING = "C";
	private static final String VALID_SHORTCUT_NAME = CYTOKINE_AS_STRING;
	private static final String INVALID_FILTER_NAME = "Z";

	@Test(expected = IllegalArgumentException.class)
	public void whenNameParamIsNullThenDontDetermineNitrogenBaseAndThrowException() {
		NitrogenBase.findByShortcutName(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenNameParamIsEmptyThenDontDetermineNitrogenBaseAndThrowException() {
		NitrogenBase.findByShortcutName(EMPTY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenGetStringAsNameParamButDontMatchToValidNitrogenBaseThenDontDetermineNitrogenBaseAndThrowException() {
		NitrogenBase.findByShortcutName(INVALID_FILTER_NAME);
	}

	@Test
	public void whenGetStringAsNameParamAndMatchThenCanDetermineValidNitrogenBaseOk() {
		NitrogenBase selectedNitrogenBase = NitrogenBase.findByShortcutName(VALID_SHORTCUT_NAME);
		assertNotNull(selectedNitrogenBase);
	}

	@Test
	public void whenSelectACAsValidNameThenCanDetermineValidNitrogenBaseOk() {
		NitrogenBase selectedNitrogenBase = NitrogenBase.findByShortcutName(CYTOKINE_AS_STRING);
		assertEquals(CYTOKINE, selectedNitrogenBase);
	}

	@Test
	public void whenSelectATAsByValidNameThenCanDetermineValidNitrogenBaseOk() {
		NitrogenBase selectedNitrogenBase = NitrogenBase.findByShortcutName(THYMINE_AS_STRING);
		assertEquals(THYMINE, selectedNitrogenBase);
	}

	@Test
	public void whenSelectACAsByValidNameThenCanDetermineValidNitrogenBaseOk() {
		NitrogenBase selectedNitrogenBase = NitrogenBase.findByShortcutName(GUANINE_AS_STRING);
		assertEquals(GUANINE, selectedNitrogenBase);
	}

	@Test
	public void whenSelectAAAsByValidNameThenCanDetermineValidNitrogenBaseOk() {
		NitrogenBase selectedNitrogenBase = NitrogenBase.findByShortcutName(ADENINE_AS_STRING);
		assertEquals(ADENINE, selectedNitrogenBase);
	}

	@Test
	public void whenHasNitrogenBaseThenCanGetCharacterOk() {
		String result = NitrogenBase.ADENINE.getCharacter();
		assertTrue(isNotBlank(result));
	}

	@Test
	public void whenHasCNitrogenBaseThenCanGetCharacterOk() {
		String result = NitrogenBase.CYTOKINE.getCharacter();
		assertEquals(CYTOKINE_AS_STRING, result);
	}

	@Test
	public void whenHasANitrogenBaseThenCanGetCharacterOk() {
		String result = NitrogenBase.ADENINE.getCharacter();
		assertEquals(ADENINE_AS_STRING, result);
	}

	@Test
	public void whenHasGNitrogenBaseThenCanGetCharacterOk() {
		String result = NitrogenBase.GUANINE.getCharacter();
		assertEquals(GUANINE_AS_STRING, result);
	}

	@Test
	public void whenHasTNitrogenBaseThenCanGetCharacterOk() {
		String result = NitrogenBase.THYMINE.getCharacter();
		assertEquals(THYMINE_AS_STRING, result);
	}

}
