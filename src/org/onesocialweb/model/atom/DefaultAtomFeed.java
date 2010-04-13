package org.onesocialweb.model.atom;

import java.util.ArrayList;
import java.util.List;

public class DefaultAtomFeed extends DefaultAtomFeedNoEntry implements AtomFeed {

	public List<AtomEntry> entries = new ArrayList<AtomEntry>();
	
	@Override
	public void addEntry(AtomEntry entry) {
		this.entries.add(entry);
	}

	@Override
	public List<AtomEntry> getEntries() {
		return entries;
	}

	@Override
	public boolean hasEntries() {
		return (entries != null && entries.size() > 0);
	}

	@Override
	public void removeEntry(AtomEntry entry) {
		entries.remove(entry);
	}

	@Override
	public void setEntries(List<AtomEntry> entries) {
		this.entries = entries;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[AtomFeed ");
		buffer.append(super.toString());	
		for (AtomEntry entry : entries) {
			buffer.append(entry.toString());
		}
		buffer.append("]");
		return buffer.toString();
	}

}
