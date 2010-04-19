package org.onesocialweb.model.activity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.onesocialweb.model.atom.AtomFeed;
import org.onesocialweb.xml.xpp.imp.DefaultXppActivityReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class XppActivityReaderTest_AMC_BV {

	@Test
	public void testParse() {

        AtomFeed feed = null;
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(getClass().getClassLoader().getResourceAsStream("atom-activity-martin-atkins.xml"), "UTF-8");
            DefaultXppActivityReader reader = new DefaultXppActivityReader();
            xpp.next();
            feed = reader.parse(xpp, 10);
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull("Feed is null", feed);
		assertEquals("Entry#1's title is not parsed as expected", 
				new String("Martin Atkins posted 'a face'"), 
				feed.getEntries().get(0).getTitle());
		assertEquals("Entry#2's title is not parsed as expected", 
				new String("Martin Atkins added 'Oh... And did I mention that he was married?' as a favorite"), 
				feed.getEntries().get(1).getTitle());
		assertEquals("Entry#3's title is not parsed as expected", 
				new String("Martin Atkins posted an entry"), 
				feed.getEntries().get(2).getTitle());
		assertEquals("Entry#4's title is not parsed as expected", 
				new String("Martin Atkins added 'Wasabit Kit Kat! Sakura/Green Tea Kit Kat! Thank you @kimmi8!' as a favorite"), 
				feed.getEntries().get(3).getTitle());
		System.out.println("Entry#1: " + feed.getEntries().get(2).toString());
	}
	
}
