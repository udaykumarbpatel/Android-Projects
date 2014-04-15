/*
a. Assignment #.   In Class Assignment 7
b. File Name.      EntryUtil.java
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
 */
package com.example.inclass7a;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class EntryUtil {

	static public class EntriesXMLPullParser {
		static ArrayList<EntryInfo> parseEntries(InputStream xmlIn)
				throws XmlPullParserException, NumberFormatException,
				IOException {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(xmlIn, "UTF-8");
			factory.setNamespaceAware(true);
			ArrayList<EntryInfo> persons = new ArrayList<EntryInfo>();
			EntryInfo person = null;
			int event = parser.getEventType();
			boolean object_title = false;
			boolean object_link = false;
			while (event != XmlPullParser.END_DOCUMENT) {
				switch (event) {
				case XmlPullParser.START_TAG:
					if (parser.getName().equals("entry")) {
						person = new EntryInfo();
						object_title = true;
					} else if (parser.getName().equals("title") && object_title) {
						person.setTitle(parser.nextText().trim());
						object_title = false;
					} else if (parser.getName().equals("category")) {
						person.setCategory(parser.getAttributeValue(null,
								"label").trim());
						object_link = true;
					} else if (parser.getName().equals("im:price")) {
						person.setPrice(parser.nextText().trim());
					} else if (parser.getName().equals("im:artist")) {
						person.setA_name(parser.nextText().trim());
					} else if (parser.getName().equals("im:releaseDate")) {
						person.setR_date(parser
								.getAttributeValue(null, "label").trim());
					} else if (parser.getName().equals("im:image")) {
						if (parser.getAttributeValue(null, "height").trim()
								.equals("75")) {
							person.setUrl_image(parser.nextText());
						}
					} else if (parser.getName().equals("link") && object_link) {
						person.setUrl_preview(parser.getAttributeValue(null,
								"href").trim());
						object_link = false;
					}
					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("entry")) {
						persons.add(person);
					}
					break;
				default:
					break;
				}
				event = parser.next();
			}
			return persons;
		}
	}
}