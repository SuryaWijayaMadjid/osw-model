package org.onesocialweb.model.atom;

import java.util.Date;
import java.util.List;

public interface AtomFeed {

	public void addEntry(AtomEntry entry);
	
	public List<AtomEntry> getEntries();
	
	public boolean hasEntries();
	
	public void removeEntry(AtomEntry entry);
	
	public void setEntries(List<AtomEntry> entries);

	public void addAuthor(AtomPerson author);

	public void addCategory(AtomCategory category);
	
	public void addContributor(AtomPerson contributor);

	public void addLink(AtomLink link);
	
	public List<AtomPerson> getAuthors();
	
	public List<AtomCategory> getCategories();
	
	public List<AtomPerson> getContributors();
	
	public String getId();
	
	public List<AtomLink> getLinks();

	public String getRights();
	
	public String getTitle();
	
	public String getSubtitle();
	
	public Date getUpdated();
	
	public AtomGenerator getGenerator();
	
	public String getIcon();
	
	public String getLogo();
	
	public boolean hasAuthors();
	
	public boolean hasCategories();
	
	public boolean hasContributors();
	
	public boolean hasId();
	
	public boolean hasLinks();
	
	public boolean hasRights();
	
	public boolean hasTitle();
	
	public boolean hasSubtitle();
	
	public boolean hasUpdated();
	
	public boolean hasGenerator();
	
	public boolean hasIcon();
	
	public boolean hasLogo();
	
	public void removeAuthor(AtomPerson author);

	public void removeCategory(AtomCategory category);
	
	public void removeContributor(AtomPerson contributor);
	
	public void removeLink(AtomLink link);
	
	public void setAuthors(List<AtomPerson> authors);

	public void setCategories(List<AtomCategory> categories);

	public void setContributors(List<AtomPerson> contributors);

	public void setId(String id);

	public void setLinks(List<AtomLink> links);

	public void setRights(String rights);

	public void setTitle(String title);
	
	public void setSubtitle(String subtitle);

	public void setUpdated(Date updated);
	
	public void setGenerator(AtomGenerator generator);
	
	public void setIcon(String icon);
	
	public void setLogo(String logo);
	
}
