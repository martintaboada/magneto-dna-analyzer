package ar.com.mtaboada.magnetodnaanalyzer.view;

import java.io.Serializable;

/**
 * @author mtaboada
 */
public class SimpleMessageView implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer code;
	private String message;

	public SimpleMessageView() {
	}

	public SimpleMessageView(final Integer code, final String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(final Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

}
