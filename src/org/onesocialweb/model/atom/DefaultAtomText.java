package org.onesocialweb.model.atom;

public class DefaultAtomText extends DefaultAtomCommon implements AtomText {

	private String type = "text";

	private String value;

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setType(final String type) {
		this.type = type;
	}

	@Override
	public void setValue(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[AtomText ");
		if (type != null) {
			buffer.append("type:" + type + "\n");
		}
		if (value != null) {
			buffer.append("value:" + value + " ");
		}
		buffer.append("]\n");

		return buffer.toString();
	}

	@Override
	public boolean hasType() {
		return (type != null);
	}

	@Override
	public boolean hasValue() {
		return (value != null);
	}
}
