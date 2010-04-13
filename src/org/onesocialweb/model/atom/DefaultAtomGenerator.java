package org.onesocialweb.model.atom;

public class DefaultAtomGenerator extends DefaultAtomCommon implements AtomGenerator {

	private String name;
	
	private String uri;
	
	private String version;
	
	@Override
	public String getName() {
		return name;
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
	public boolean hasName() {
		return name != null;
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
	public void setName(String name) {
		this.name = name;
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
		if (name != null) {
			buffer.append("name:" + name + " ");
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
