package org.onesocialweb.model.atom;

public interface AtomGenerator {

	public String getGenerator();
	
	public String getUri();
	
	public String getVersion();
	
	public void setGenerator(String generator);
	
	public void setUri(String uri);
	 
	public void setVersion(String version);
	
	public boolean hasGenerator();
	
	public boolean hasUri();
	
	public boolean hasVersion();
	
}
