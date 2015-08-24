package jonss.moipchallenge.model;

public class LogValue {

	private Integer quantity;
	private String value;

	public LogValue(String value, Integer quantity) {
		this.value = value;
		this.quantity = quantity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return getValue() + " - " + getQuantity();
	}
}
