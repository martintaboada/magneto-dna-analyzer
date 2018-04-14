package ar.com.mtaboada.magnetodnaanalyzer.model;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 *
 * @author mtaboada
 *
 */
public enum NitrogenBase {
	ADENINE("A"), THYMINE("T"), CYTOKINE("C"), GUANINE("G");

	private String character;

	private NitrogenBase(final String character) {
		this.character = character;
	}

	public String getCharacter() {
		return character;
	}

	public static NitrogenBase findByShortcutName(final String baseCharacter) {
		if (isBlank(baseCharacter)) {
			throw new IllegalArgumentException("Shortcut name is required to determine element");
		}
		for (NitrogenBase value : values()) {
			if (value.character.equals(baseCharacter)) {
				return value;
			}
		}
		throw new IllegalArgumentException(
				"The parameter name \"" + baseCharacter + "\" is not a valid as shortcut name");
	}
}
