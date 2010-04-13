package org.onesocialweb.model.atom;

public interface AtomGenerator {

	public String getName();
	
	public String getUri();
	
	public String getVersion();
	
	public void setName(String name);
	
	public void setUri(String uri);
	 
	public void setVersion(String version);
	
	public boolean hasName();
	
	public boolean hasUri();
	
	public boolean hasVersion();
	
}
