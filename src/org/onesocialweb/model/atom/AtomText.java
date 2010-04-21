package org.onesocialweb.model.atom;

public interface AtomText extends AtomCommon {

	public String getType();

	public String getValue();

	public void setType(final String type);

	public void setValue(final String value);
	
	public boolean hasType();
	
	public boolean hasValue();
	
}
