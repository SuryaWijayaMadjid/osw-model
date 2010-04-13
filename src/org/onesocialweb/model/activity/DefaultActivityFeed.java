package org.onesocialweb.model.activity;

import java.util.ArrayList;
import java.util.List;

import org.onesocialweb.model.atom.DefaultAtomFeedNoEntry;

public class DefaultActivityFeed extends DefaultAtomFeedNoEntry implements ActivityFeed {

	public List<ActivityEntry> entries = new ArrayList<ActivityEntry>();
	
	@Override
	public void addEntry(ActivityEntry entry) {
		this.entries.add(entry);
	}

	@Override
	public List<ActivityEntry> getEntries() {
		return entries;
	}

	@Override
	public boolean hasEntries() {
		return (entries != null && entries.size() > 0);
	}

	@Override
	public void removeEntry(ActivityEntry entry) {
		entries.remove(entry);
	}

	@Override
	public void setEntries(List<ActivityEntry> entries) {
		this.entries = entries;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[ActivityFeed ");
		buffer.append(super.toString());		
		for (ActivityEntry entry : entries) {
			buffer.append(entry.toString());
		}
		buffer.append("]");
		return buffer.toString();
	}

}
