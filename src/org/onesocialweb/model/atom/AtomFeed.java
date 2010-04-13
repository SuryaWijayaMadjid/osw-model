package org.onesocialweb.model.atom;

import java.util.List;

public interface AtomFeed extends AtomFeedNoEntry {

	public void addEntry(AtomEntry entry);
	
	public List<AtomEntry> getEntries();
	
	public boolean hasEntries();
	
	public void removeEntry(AtomEntry entry);
	
	public void setEntries(List<AtomEntry> entries);

}
