package org.onesocialweb.model.atom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DefaultAtomFeedNoEntry  extends DefaultAtomCommon implements AtomFeedNoEntry {

	public List<AtomPerson> authors = new ArrayList<AtomPerson>();
	
	public List<AtomCategory> categories = new ArrayList<AtomCategory>();
	
	public List<AtomPerson> contributors = new ArrayList<AtomPerson>();
	
	public String id;
	
	public List<AtomLink> links = new ArrayList<AtomLink>();

	public String rights;
	
	public String title;
	
	public String subtitle;
	
	public Date updated;
	
	public AtomGenerator generator;
	
	public String icon;
	
	public String logo;
	
	@Override
	public void addAuthor(AtomPerson author) {
		this.authors.add(author);
	}

	@Override
	public void addCategory(AtomCategory category) {
		this.categories.add(category);
	}

	@Override
	public void addContributor(AtomPerson contributor) {
		this.contributors.add(contributor);
	}

	@Override
	public void addLink(AtomLink link) {
		this.links.add(link);
	}

	@Override
	public List<AtomPerson> getAuthors() {
		return authors;
	}

	@Override
	public List<AtomCategory> getCategories() {
		return categories;
	}

	@Override
	public List<AtomPerson> getContributors() {
		return contributors;
	}

	@Override
	public AtomGenerator getGenerator() {
		return generator;
	}

	@Override
	public String getIcon() {
		return icon;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public List<AtomLink> getLinks() {
		return links;
	}

	@Override
	public String getLogo() {
		return logo;
	}

	@Override
	public String getRights() {
		return rights;
	}

	@Override
	public String getSubtitle() {
		return subtitle;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Date getUpdated() {
		return updated;
	}

	@Override
	public boolean hasAuthors() {
		return (authors != null && authors.size() > 0);
	}

	@Override
	public boolean hasCategories() {
		return (categories != null && categories.size() > 0);
	}

	@Override
	public boolean hasContributors() {
		return (contributors != null && contributors.size() > 0);
	}

	@Override
	public boolean hasGenerator() {
		return generator != null;
	}

	@Override
	public boolean hasIcon() {
		return icon != null;
	}

	@Override
	public boolean hasId() {
		return id != null;
	}

	@Override
	public boolean hasLinks() {
		return (links != null && links.size() > 0);
	}

	@Override
	public boolean hasLogo() {
		return logo != null;
	}

	@Override
	public boolean hasRights() {
		return rights != null;
	}

	@Override
	public boolean hasSubtitle() {
		return subtitle != null;
	}

	@Override
	public boolean hasTitle() {
		return title != null;
	}

	@Override
	public boolean hasUpdated() {
		return updated != null;
	}

	@Override
	public void removeAuthor(AtomPerson author) {
		authors.remove(author);
	}

	@Override
	public void removeCategory(AtomCategory category) {
		categories.remove(category);
	}

	@Override
	public void removeContributor(AtomPerson contributor) {
		contributors.remove(contributor);
	}

	@Override
	public void removeLink(AtomLink link) {
		links.remove(link);
	}

	@Override
	public void setAuthors(List<AtomPerson> authors) {
		this.authors = authors;
	}

	@Override
	public void setCategories(List<AtomCategory> categories) {
		this.categories = categories;
	}

	@Override
	public void setContributors(List<AtomPerson> contributors) {
		this.contributors = contributors;
	}

	@Override
	public void setGenerator(AtomGenerator generator) {
		this.generator = generator;
	}

	@Override
	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void setLinks(List<AtomLink> links) {
		this.links = links;
	}

	@Override
	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Override
	public void setRights(String rights) {
		this.rights = rights;
	}

	@Override
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		if (id != null) {
			buffer.append("id:" + id + " ");
		}
		if (updated != null) {
			buffer.append("updated:" + updated + " ");
		}
		if (title != null) {
			buffer.append("title:" + title + " ");
		}
		if (rights != null) {
			buffer.append("rights:" + rights + " ");
		}
		if (subtitle != null) {
			buffer.append("subtitle:" + subtitle + " ");
		}
		if (generator != null) {
			buffer.append("generator:" + generator + " ");
		}
		if (icon != null) {
			buffer.append("icon:" + icon + " ");
		}
		if (logo != null) {
			buffer.append("logo:" + logo + " ");
		}
		for (AtomPerson author : authors) {
			buffer.append(author.toString());
		}
		for (AtomPerson contributor : contributors) {
			buffer.append(contributor.toString());
		}
		for (AtomCategory category : categories) {
			buffer.append(category.toString());
		}
		for (AtomLink link : links) {
			buffer.append(link.toString());
		}
		return buffer.toString();
	}

}