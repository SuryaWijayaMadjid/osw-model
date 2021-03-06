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
package org.onesocialweb.xml.writer;

import org.onesocialweb.model.acl.AclAction;
import org.onesocialweb.model.acl.AclRule;
import org.onesocialweb.model.acl.AclSubject;
import org.onesocialweb.model.activity.ActivityEntry;
import org.onesocialweb.model.activity.ActivityObject;
import org.onesocialweb.model.activity.ActivityVerb;
import org.onesocialweb.model.atom.AtomCategory;
import org.onesocialweb.model.atom.AtomContent;
import org.onesocialweb.model.atom.AtomEntry;
import org.onesocialweb.model.atom.AtomLink;
import org.onesocialweb.model.atom.AtomPerson;
import org.onesocialweb.model.atom.AtomText;
import org.onesocialweb.model.atom.DefaultAtomHelper;
import org.onesocialweb.xml.namespace.Activitystreams;
import org.onesocialweb.xml.namespace.Atom;
import org.onesocialweb.xml.namespace.Onesocialweb;

public class ActivityXmlWriter extends XmlWriter {
	
	public String toXml(ActivityEntry entry) {
		toXml(entry, new StringBuffer());
		return buffer.toString();
	}
	
	public void toXml(ActivityEntry entry, StringBuffer buffer) {
		// Prepare for writing
		this.buffer = buffer;
		
		// Open the activity tag
		startTag(Atom.ENTRY_ELEMENT);
		attribute("xmlns", Atom.NAMESPACE);
		attribute("xmlns:activity", Activitystreams.NAMESPACE);
		//attribute("xmlns:thr", AtomThreading.NAMESPACE);
		endOpen();
		
		// Dump the atom stuff
		dumpBody(entry);
		
		// Dump the activity related stuff
		if (entry.hasActor()) {
			openTag("activity:" + Activitystreams.ACTOR_ELEMENT);
			dumpBody(entry.getActor());
			closeTag("activity:" + Activitystreams.ACTOR_ELEMENT);
		}
		if (entry.hasVerbs()) {
			for (ActivityVerb verb : entry.getVerbs()) {
				text("activity:" + Activitystreams.VERB_ELEMENT, verb.getValue());
			}
		}
		if (entry.hasObjects()) {
			for (ActivityObject object : entry.getObjects()) {
				openTag("activity:" + Activitystreams.OBJECT_ELEMENT);
				text("activity:" + Activitystreams.OBJECT_TYPE_ELEMENT, object.getType());
				dumpBody(object);
				closeTag("activity:" + Activitystreams.OBJECT_ELEMENT);
			}
		}
		if (entry.hasAclRules()) {
			for (AclRule rule : entry.getAclRules()) {
				openTag("osw:" + Onesocialweb.ACL_RULE_ELEMENT);
				if (rule.hasActions()) {
					for (AclAction action : rule.getActions()) {
						startTag("osw:" + Onesocialweb.ACL_ACTION_ELEMENT);
						attribute(Onesocialweb.PERMISSION_ATTRIBUTE, action.getPermission());
						endOpen();
						if (action.hasName()) buffer.append(action.getName());
						closeTag("osw:" + Onesocialweb.ACL_ACTION_ELEMENT);
					}
				}
				if (rule.hasSubjects()) {
					for (AclSubject subject : rule.getSubjects()) {
						startTag("osw:" + Onesocialweb.ACL_SUBJECT_ELEMENT);
						attribute(Onesocialweb.TYPE_ATTRIBUTE, subject.getType());
						endOpen();
						if (subject.hasName()) buffer.append(subject.getName());
						closeTag("osw:" + Onesocialweb.ACL_SUBJECT_ELEMENT);
					}
				}
				closeTag("osw:" + Onesocialweb.ACL_RULE_ELEMENT);
			}
		}
		// Close
		closeTag(Atom.ENTRY_ELEMENT);
	}
	
	private void dumpBody(AtomEntry entry) {
		if (entry.hasId()) text(Atom.ID_ELEMENT, entry.getId());
		if (entry.hasPublished()) text(Atom.PUBLISHED_ELEMENT, DefaultAtomHelper.format(entry.getPublished()));
		if (entry.hasUpdated()) text(Atom.UPDATED_ELEMENT, DefaultAtomHelper.format(entry.getUpdated()));
		//if (entry.hasTitle()) text(Atom.TITLE_ELEMENT, entry.getTitle().getValue());
		if (entry.hasTitle()) {
			AtomText title = entry.getTitle() ;
			startTag(Atom.TITLE_ELEMENT);
			if (title.hasType()) attribute(Atom.TYPE_ATTRIBUTE, title.getType());
			endOpen();
			if (title.hasValue()) buffer.append(title.getValue());
			closeTag(Atom.TITLE_ELEMENT);
		}
		if (entry.hasAuthors()) {
			for (AtomPerson person : entry.getAuthors()) {
				openTag(Atom.AUTHOR_ELEMENT);
				dumpBody(person);
				closeTag(Atom.AUTHOR_ELEMENT);
			}
		}
		if (entry.hasContributors()) {
			for (AtomPerson person : entry.getAuthors()) {
				openTag(Atom.CONTRIBUTOR_ELEMENT);
				dumpBody(person);
				closeTag(Atom.CONTRIBUTOR_ELEMENT);
			}
		}
		if (entry.hasCategories()) {
			for (AtomCategory category : entry.getCategories()) {
				startTag(Atom.CATEGORY_ELEMENT);
				if (category.hasLabel()) attribute(Atom.LABEL_ATTRIBUTE, category.getLabel());
				if (category.hasScheme()) attribute(Atom.SCHEME_ATTRIBUTE, category.getScheme());
				if (category.hasTerm()) attribute(Atom.TERM_ATTRIBUTE, category.getTerm());
				endClosed();
			}
		}
		if (entry.hasContent()) {
			AtomContent content = entry.getContent() ;
			startTag(Atom.CONTENT_ELEMENT);
			if (content.hasType()) attribute(Atom.TYPE_ATTRIBUTE, content.getType());
			if (content.hasSrc()) attribute(Atom.SRC_ATTRIBUTE, content.getSrc());
			endOpen();
			if (content.hasValue()) buffer.append(content.getValue());
			closeTag(Atom.CONTENT_ELEMENT);
		}
		if (entry.hasSummary()) {
			AtomText summary = entry.getSummary() ;
			startTag(Atom.SUMMARY_ELEMENT);
			if (summary.hasType()) attribute(Atom.TYPE_ATTRIBUTE, summary.getType());
			endOpen();
			if (summary.hasValue()) buffer.append(summary.getValue());
			closeTag(Atom.SUMMARY_ELEMENT);
		}
		if (entry.hasLinks()) {
			for (AtomLink link : entry.getLinks()) {
				startTag(Atom.LINK_ELEMENT);
				if (link.hasHref()) attribute(Atom.HREF_ATTRIBUTE, link.getHref());
				if (link.hasHreflang()) attribute(Atom.HREFLANG_ATTRIBUTE, link.getHreflang());
				if (link.hasLength()) attribute(Atom.LENGTH_ATTRIBUTE, link.getLength());
				if (link.hasRel()) attribute(Atom.REL_ATTRIBUTE, link.getRel());
				if (link.hasTitle()) attribute(Atom.TITLE_ATTRIBUTE, link.getTitle());
				if (link.hasType()) attribute(Atom.TYPE_ATTRIBUTE, link.getType());
				endClosed();
			}
		}
	}
	
	private void dumpBody(AtomPerson person) {
		if (person.hasName()) text(Atom.NAME_ATTRIBUTE, person.getName());
		if (person.hasEmail()) text(Atom.EMAIL_ELEMENT, person.getEmail());
		if (person.hasUri()) text(Atom.URI_ELEMENT, person.getUri());
	}
	
}
