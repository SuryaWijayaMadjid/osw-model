/*
 *  Copyright 2010 Vodafone Group Services Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *    
 */
package org.onesocialweb.model.atom;

import java.util.Date;
import java.util.List;

public interface AtomEntry extends AtomCommon {

	public void addAuthor(AtomPerson author);

	public void addCategory(AtomCategory category);

	public void addContributor(AtomPerson person);

	public void addLink(AtomLink link);
	
	public void addRecipient(AtomReplyTo to);
	
	public List<AtomPerson> getAuthors();
	
	public List<AtomCategory> getCategories();
	
	public AtomContent getContent();
	
	public List<AtomPerson> getContributors();
	
	public List<AtomReplyTo> getRecipients();
	
	public String getId();
	
	public List<AtomLink> getLinks();
	
	public Date getPublished();

	public AtomText getRights();

	public AtomSource getSource();
	
	public AtomText getTitle();
	
	public Date getUpdated();
	
	public AtomText getSummary();
	
	public boolean hasAuthors();
	
	public boolean hasCategories();
	
	public boolean hasContent();
	
	public boolean hasContributors();
	
	public boolean hasId();
	
	public boolean hasLinks();
	
	public boolean hasPublished();
	
	public boolean hasRights();
	
	public boolean hasSource();
	
	public boolean hasTitle();
	
	public boolean hasUpdated();
	
	public boolean hasRecipients();
	
	public boolean hasSummary();
	
	public void removeAuthor(AtomPerson author);

	public void removeCategory(AtomCategory category);
	
	public void removeContributor(AtomPerson person);
	
	public void removeLink(AtomLink link);
	
	public void removeRecipient(AtomReplyTo to);

	public void setAuthors(List<AtomPerson> authors);

	public void setCategories(List<AtomCategory> categories);

	public void setContributors(List<AtomPerson> contributors);

	public void setId(String id);
	
	public void setContent(AtomContent content);

	public void setLinks(List<AtomLink> links);

	public void setPublished(Date published);

	public void setRights(AtomText rights);

	public void setSource(AtomSource source);

	public void setTitle(AtomText title);

	public void setUpdated(Date updated);
	
	public void setRecipients(List<AtomReplyTo> resources);
	
	public void setSummary(AtomText summary);

}