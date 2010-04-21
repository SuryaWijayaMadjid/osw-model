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
            //xpp.setInput(getClass().getClassLoader().getResourceAsStream("atom-activity-martin-atkins.xml"), "UTF-8");
            xpp.setInput(getClass().getClassLoader().getResourceAsStream("atom-activity-the4general.xml"), "UTF-8");
            DefaultXppActivityReader reader = new DefaultXppActivityReader();
            xpp.next();
            feed = reader.parse(xpp);
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull("Feed is null", feed);
		
		// To test using atom-activity-martin-atkins.xml
		/*assertEquals("Entry#1's title is not parsed as expected", 
				new String("Martin Atkins posted 'a face'"), 
				feed.getEntries().get(0).getTitle().getValue());
		assertEquals("Entry#2's title is not parsed as expected", 
				new String("Martin Atkins added 'Oh... And did I mention that he was married?' as a favorite"), 
				feed.getEntries().get(1).getTitle().getValue());
		assertEquals("Entry#3's title is not parsed as expected", 
				new String("Martin Atkins posted an entry"), 
				feed.getEntries().get(2).getTitle().getValue());
		assertEquals("Entry#4's title is not parsed as expected", 
				new String("Martin Atkins added 'Wasabit Kit Kat! Sakura/Green Tea Kit Kat! Thank you @kimmi8!' as a favorite"), 
				feed.getEntries().get(3).getTitle().getValue());*/
		
		// To test using atom-activity-martin-the4general.xml
		assertEquals("Entry#1's title is not parsed as expected", 
				new String("the4general: @ekoadipg\nha??"), 
				feed.getEntries().get(0).getTitle().getValue());
		assertEquals("Entry#2's title is not parsed as expected", 
				new String("the4general: the new status..."), 
				feed.getEntries().get(1).getTitle().getValue());
		assertEquals("Entry#3's title is not parsed as expected", 
				new String("the4general: posting status..."), 
				feed.getEntries().get(2).getTitle().getValue());
		assertEquals("Entry#4's title is not parsed as expected", 
				new String("the4general: from my twitter~"), 
				feed.getEntries().get(3).getTitle().getValue());
		System.out.println("Entry#1: " + feed.getEntries().get(0).toString());
	}
	
}
