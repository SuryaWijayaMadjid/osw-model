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
package org.onesocialweb.xml.xpp;

import java.io.IOException;
import java.util.Date;

import org.onesocialweb.model.activity.ActivityActor;
import org.onesocialweb.model.activity.ActivityEntry;
import org.onesocialweb.model.activity.ActivityFactory;
import org.onesocialweb.model.activity.ActivityObject;
import org.onesocialweb.model.atom.AtomCategory;
import org.onesocialweb.model.atom.AtomContent;
import org.onesocialweb.model.atom.AtomEntry;
import org.onesocialweb.model.atom.AtomFactory;
import org.onesocialweb.model.atom.AtomFeed;
import org.onesocialweb.model.atom.AtomGenerator;
import org.onesocialweb.model.atom.AtomLink;
import org.onesocialweb.model.atom.AtomPerson;
import org.onesocialweb.model.atom.AtomReplyTo;
import org.onesocialweb.model.atom.AtomSource;
import org.onesocialweb.xml.namespace.Activitystreams;
import org.onesocialweb.xml.namespace.Atom;
import org.onesocialweb.xml.namespace.AtomThreading;
import org.onesocialweb.xml.namespace.Onesocialweb;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public abstract class XppActivityReader implements XppReader<AtomFeed> {

	private final ActivityFactory activityFactory;
	
	private final AtomFactory atomFactory;
	
	private final XppAclReader aclReader;

	public XppActivityReader() {
		this.aclReader = getXppAclReader();
		this.activityFactory = getActivityFactory();
		this.atomFactory = getAtomFactory();
	}
	
	
	@Override
	public AtomFeed parse(XmlPullParser parser) throws XmlPullParserException, IOException {

		// Verify that we are on the right token
		if (!(parser.getNamespace().equals(Atom.NAMESPACE) 
				&& parser.getName().equals(Atom.FEED_ELEMENT))) {
			throw new XmlPullParserException("Unexpected token " + parser);
		}

		//Proceed with parsing
		boolean done = false;
		AtomFeed feed = atomFactory.feed();
		
		while (!done) {
			int eventType = parser.next();
			String name = parser.getName();
			String namespace = parser.getNamespace();
			if (eventType == XmlPullParser.START_TAG) {
				if (namespace.equals(Atom.NAMESPACE)) {
					if (name.equals(Atom.ENTRY_ELEMENT)) {
						feed.addEntry(parseEntry(parser));
					} else
						readAtomFeedMetadata(feed, parser);
				}
			} else if (eventType == XmlPullParser.END_TAG) {
				if (namespace.equals(Atom.NAMESPACE)
						&& name.equals(Atom.FEED_ELEMENT)) {
					done = true;
				}
			}
		}
		return feed;
	}

	protected AtomEntry parseEntry(XmlPullParser parser) throws XmlPullParserException, IOException {

		// Verify that we are on the right token
		if (!(parser.getNamespace().equals(Atom.NAMESPACE) 
				&& parser.getName().equals(Atom.ENTRY_ELEMENT))) {
			throw new XmlPullParserException("Unexpected token " + parser);
		}

		//Proceed with parsing
		final ActivityEntry activityEntry = activityFactory.entry();
		final AtomEntry atomEntry = atomFactory.entry();
		boolean done = false;
		boolean isActivityEntry = false;
		
		while (!done) {
			int eventType = parser.next();
			String name = parser.getName();
			String namespace = parser.getNamespace();
			if (eventType == XmlPullParser.START_TAG) {
				if (namespace.equals(Activitystreams.NAMESPACE)) {
					isActivityEntry = true;
					if (name.equals(Activitystreams.ACTOR_ELEMENT)) {
						activityEntry.setActor(parseActor(parser));
					} else if (name.equals(Activitystreams.VERB_ELEMENT)) {
						activityEntry.addVerb(activityFactory.verb(parser.nextText()));
					} else if (name.equals(Activitystreams.OBJECT_ELEMENT)) {
						activityEntry.addObject(parseObject(parser));
					}
				} else if (namespace.equals(Atom.NAMESPACE)) {
					readAtomEntryElement(atomEntry, parser);
				} else if (namespace.equals(Onesocialweb.NAMESPACE)) {
					if (name.equals(Onesocialweb.ACL_RULE_ELEMENT)) {
						activityEntry.addAclRule(aclReader.parse(parser));
					}
				}
			} else if (eventType == XmlPullParser.END_TAG) {
				if (namespace.equals(Atom.NAMESPACE)
						&& name.equals(Atom.ENTRY_ELEMENT)) {
					done = true;
				}
			}
		}
		
		if (isActivityEntry) {
			mergeActivityAndAtomEntry(activityEntry, atomEntry);
			return activityEntry;
		}
		else return atomEntry;
	}
	
	protected ActivityActor parseActor(XmlPullParser parser) throws XmlPullParserException, IOException {	
		final ActivityActor actor = activityFactory.actor();
		readAtomPersonElement(actor, parser);
		return actor;
	}
	
	protected AtomPerson parsePerson(XmlPullParser parser) throws XmlPullParserException, IOException {	
		final AtomPerson person = atomFactory.person();
		readAtomPersonElement(person, parser);
		return person;
	}
	
	protected void readAtomPersonElement(AtomPerson person, XmlPullParser parser) throws XmlPullParserException, IOException {
		final String tagName = parser.getName();
		final String tagNamespace = parser.getNamespace();
		boolean done = false;
		while (!done) {
			int eventType = parser.next();
			String name = parser.getName();
			String namespace = parser.getNamespace();
			if (eventType == XmlPullParser.START_TAG) {
				if (namespace.equals(Atom.NAMESPACE)) {
					if (name.equals(Atom.NAME_ELEMENT)) {
						person.setName(parser.nextText().trim());
					} else if (name.equals(Atom.EMAIL_ELEMENT)) {
						person.setEmail(parser.nextText().trim());
					}  else if (name.equals(Atom.URI_ELEMENT)) {
						person.setUri(parser.nextText().trim());
					}
				}
			} else if (eventType == XmlPullParser.END_TAG) {
				if (namespace.equals(tagNamespace) && name.equals(tagName)) {
					done = true;
				}
			}
		}
	}
	
	protected AtomContent parseContent(XmlPullParser parser) throws XmlPullParserException, IOException {
		final AtomContent content = atomFactory.content();
		for (int i=0; i<parser.getAttributeCount(); i++) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i).trim();
			if (name.equals(Atom.SRC_ATTRIBUTE)) {
				content.setSrc(value);
			} else if (name.equals(Atom.TYPE_ATTRIBUTE)) {
				content.setType(value);
			}
		}
		
		String text = parser.nextText().trim();
		if (text.length() > 0) {
			content.setValue(text);
		}
		
		return content;
	}
	
	protected AtomCategory parseCategory(XmlPullParser parser) throws XmlPullParserException, IOException {	
		final AtomCategory category = atomFactory.category();
		for (int i=0; i<parser.getAttributeCount(); i++) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i).trim();
			if (name.equals(Atom.LABEL_ATTRIBUTE)) {
				category.setLabel(value);
			} else if (name.equals(Atom.SCHEME_ATTRIBUTE)) {
				category.setScheme(value);
			} else if (name.equals(Atom.TERM_ATTRIBUTE)) {
				category.setTerm(value);
			}
		}
		return category;
	}
	
	protected AtomSource parseSource(XmlPullParser parser) throws XmlPullParserException, IOException {	
		final AtomSource source = atomFactory.source();
		// TODO
		return source;
	}
	
	protected AtomGenerator parseGenerator(XmlPullParser parser) throws XmlPullParserException, IOException {
		final AtomGenerator generator = atomFactory.generator();

		for (int i=0; i<parser.getAttributeCount(); i++) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i).trim();
			if (name.equals(Atom.URI_ATTRIBUTE)) {
				generator.setUri(value);
			} else if (name.equals(Atom.VERSION_ATTRIBUTE)) {
				generator.setVersion(value);
			}
		}
		
		String text = parser.nextText().trim();
		if (text.length() > 0) {
			generator.setName(text);
		}
		
		return generator;
	}
	
	protected AtomLink parseLink(XmlPullParser parser) throws XmlPullParserException, IOException {	
		final AtomLink link = atomFactory.link();
		for (int i=0; i<parser.getAttributeCount(); i++) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i).trim();
			if (name.equals(Atom.HREF_ATTRIBUTE)) {
				link.setHref(value);
			} else if (name.equals(Atom.HREFLANG_ATTRIBUTE)) {
				link.setHreflang(value);
			} else if (name.equals(Atom.LENGTH_ATTRIBUTE)) {
				link.setLength(value);
			}  else if (name.equals(Atom.REL_ATTRIBUTE)) {
				link.setRel(value);
			}  else if (name.equals(Atom.TITLE_ATTRIBUTE)) {
				link.setTitle(value);
			}  else if (name.equals(Atom.TYPE_ATTRIBUTE)) {
				link.setType(value);
			}
		}
		return link;
	}
	
	protected AtomReplyTo parseRecipient(XmlPullParser parser) throws XmlPullParserException, IOException {	
		final AtomReplyTo recipient = atomFactory.reply();
		for (int i=0; i<parser.getAttributeCount(); i++) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i).trim();
			if (name.equals(AtomThreading.HREF_ATTRIBUTE)) {
				recipient.setHref(value);
			}  else if (name.equals(AtomThreading.TYPE_ATTRIBUTE)) {
				recipient.setType(value);
			}  else if (name.equals(AtomThreading.SOURCE_ATTRIBUTE)) {
				recipient.setSource(value);
			}  else if (name.equals(AtomThreading.REF_ATTRIBUTE)) {
				recipient.setRef(value);
			}
		}
		return recipient;
	}
	
	protected void readAtomEntryElement(AtomEntry entry, XmlPullParser parser) throws XmlPullParserException, IOException {
		String name = parser.getName();
		
		if (name.equals(Atom.AUTHOR_ELEMENT)) {
			entry.addAuthor(parsePerson(parser));
		} else if (name.equals(Atom.CONTRIBUTOR_ELEMENT)) {
			entry.addContributor(parsePerson(parser));
		} else if (name.equals(Atom.CONTENT_ELEMENT)) {
			entry.setContent(parseContent(parser));
		} else if (name.equals(Atom.LINK_ELEMENT)) {
			entry.addLink(parseLink(parser));
/*		} else if (name.equals(AtomThreading.IN_REPLY_TO_ELEMENT)) {
			entry.addRecipient(parseRecipient(parser));*/
		} else if (name.equals(Atom.CATEGORY_ELEMENT)) {
			entry.addCategory(parseCategory(parser));
		} else if (name.equals(Atom.ID_ELEMENT)) {
			entry.setId(parser.nextText().trim());
		} else if (name.equals(Atom.PUBLISHED_ELEMENT)) {
			entry.setPublished(parseDate(parser.nextText().trim()));
		} else if (name.equals(Atom.UPDATED_ELEMENT)) {
			entry.setUpdated(parseDate(parser.nextText().trim()));
		} else if (name.equals(Atom.TITLE_ELEMENT)) {
			entry.setTitle(parser.nextText().trim());
		}
	}
	
	protected void readAtomFeedMetadata(AtomFeed feed, XmlPullParser parser) throws XmlPullParserException, IOException {
		String name = parser.getName();
		
		if (name.equals(Atom.AUTHOR_ELEMENT)) {
			feed.addAuthor(parsePerson(parser));
		} else if (name.equals(Atom.CATEGORY_ELEMENT)) {
			feed.addCategory(parseCategory(parser));
		} else if (name.equals(Atom.CONTRIBUTOR_ELEMENT)) {
			feed.addContributor(parsePerson(parser));
		} else if (name.equals(Atom.GENERATOR_ELEMENT)) {
			feed.setGenerator(parseGenerator(parser));
		} else if (name.equals(Atom.ICON_ELEMENT)) {
			feed.setIcon(parser.nextText().trim());
		} else if (name.equals(Atom.ID_ELEMENT)) {
			feed.setId(parser.nextText().trim());
		} else if (name.equals(Atom.LINK_ELEMENT)) {
			feed.addLink(parseLink(parser));
		} else if (name.equals(Atom.LOGO_ELEMENT)) {
			feed.setLogo(parser.nextText().trim());
		} else if (name.equals(Atom.RIGHTS_ELEMENT)) {
			feed.setRights(parser.nextText().trim());
		} else if (name.equals(Atom.SUBTITLE_ELEMENT)) {
			feed.setSubtitle(parser.nextText().trim());
		} else if (name.equals(Atom.TITLE_ELEMENT)) {
			feed.setTitle(parser.nextText().trim());
		} else if (name.equals(Atom.UPDATED_ELEMENT)) {
			feed.setUpdated(parseDate(parser.nextText().trim()));
		} 
	}
	
	protected ActivityObject parseObject(XmlPullParser parser) throws XmlPullParserException, IOException {
		final ActivityObject object = activityFactory.object();
		boolean done = false;
		
		while (!done) {
			int eventType = parser.next();
			String name = parser.getName();
			String namespace = parser.getNamespace();
			if (eventType == XmlPullParser.START_TAG) {
				if (namespace.equals(Activitystreams.NAMESPACE)) {
					if (name.equals(Activitystreams.OBJECT_TYPE_ELEMENT)) {
						object.setType(parser.nextText().trim());
					}
				} else if (namespace.equals(Atom.NAMESPACE)) {
					readAtomEntryElement(object, parser);
				} else if (namespace.equals(Onesocialweb.NAMESPACE)) {
					//
				}
			} else if (eventType == XmlPullParser.END_TAG) {
				if (namespace.equals(Activitystreams.NAMESPACE)
						&& name.equals(Activitystreams.OBJECT_ELEMENT)) {
					done = true;
				}
			}
		}
		return object;
	}
	
	protected void mergeActivityAndAtomEntry(ActivityEntry activityEntry, AtomEntry atomEntry) {
		
		if (atomEntry.hasAuthors()) activityEntry.setAuthors(atomEntry.getAuthors());
		if (atomEntry.hasCategories()) activityEntry.setCategories(atomEntry.getCategories());
		if (atomEntry.hasContent()) activityEntry.setContent(atomEntry.getContent());
		if (atomEntry.hasContributors()) activityEntry.setContributors(atomEntry.getContributors());
		if (atomEntry.hasId()) activityEntry.setId(atomEntry.getId());
		if (atomEntry.hasLinks()) activityEntry.setLinks(atomEntry.getLinks());
		if (atomEntry.hasPublished()) activityEntry.setPublished(atomEntry.getPublished());
		if (atomEntry.hasRights()) activityEntry.setRights(atomEntry.getRights());
		if (atomEntry.hasSource()) activityEntry.setSource(atomEntry.getSource());
		if (atomEntry.hasTitle()) activityEntry.setTitle(atomEntry.getTitle());
		if (atomEntry.hasUpdated()) activityEntry.setUpdated(atomEntry.getUpdated());
		
	}

	abstract protected ActivityFactory getActivityFactory();
	
	abstract protected AtomFactory getAtomFactory();
	
	abstract protected XppAclReader getXppAclReader();
	
	abstract protected Date parseDate(String atomDate);
	
}
