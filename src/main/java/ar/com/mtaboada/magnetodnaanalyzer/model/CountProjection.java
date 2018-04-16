package ar.com.mtaboada.magnetodnaanalyzer.model;

/**
 * @author mtaboada
 */
public class CountProjection {
	private String _id;
	private Integer count;

	public Integer getCount() {
		return count;
	}

	public void setCount(final Integer count) {
		this.count = count;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(final String _id) {
		this._id = _id;
	}
}
