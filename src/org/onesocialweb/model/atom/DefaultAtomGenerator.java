package org.onesocialweb.model.atom;

public class DefaultAtomGenerator extends DefaultAtomCommon implements AtomGenerator {

	private String generator;
	
	private String uri;
	
	private String version;
	
	@Override
	public String getGenerator() {
		return generator;
	}

	@Override
	public String getUri() {
		return uri;
	}

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public boolean hasGenerator() {
		return generator != null;
	}

	@Override
	public boolean hasUri() {
		return uri != null;
	}

	@Override
	public boolean hasVersion() {
		return version != null;
	}

	@Override
	public void setGenerator(String generator) {
		this.generator = generator;
	}

	@Override
	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public void setVersion(String version) {
		this.version = version;	
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[AtomPerson ");
		if (generator != null) {
			buffer.append("generator:" + generator + " ");
		}
		if (uri != null) {
			buffer.append("uri:" + uri + " ");
		}
		if (version != null) {
			buffer.append("version:" + version + " ");
		}
		buffer.append("]");
		return buffer.toString();
	}
	
}
