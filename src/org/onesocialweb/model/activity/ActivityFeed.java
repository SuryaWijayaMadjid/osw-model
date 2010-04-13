package org.onesocialweb.model.activity;

import java.util.List;

import org.onesocialweb.model.atom.AtomFeedNoEntry;

public interface ActivityFeed extends AtomFeedNoEntry {

	public void addEntry(ActivityEntry entry);
	
	public List<ActivityEntry> getEntries();
	
	public boolean hasEntries();
	
	public void removeEntry(ActivityEntry entry);
	
	public void setEntries(List<ActivityEntry> entries);
	
}
