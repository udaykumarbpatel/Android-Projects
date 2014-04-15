/*
a. Assignment #.   In Class Assignment 7
b. File Name.      EntryUtil.java
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
 */
package com.example.inclass8;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class NewsUtil {

	static public class NewsXMLPullParser {
		static ArrayList<News> parseEntries(InputStream xmlIn)
				throws XmlPullParserException, NumberFormatException,
				IOException {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(xmlIn, "UTF-8");
			factory.setNamespaceAware(true);
			ArrayList<News> news = new ArrayList<News>();
			News item = null;
			boolean object_title = false;
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {
				switch (event) {
				case XmlPullParser.START_TAG:
					if (parser.getName().equals("item")) {
						item = new News();
						object_title = true;
					} else if (parser.getName().equals("title") && object_title) {
//						person.setTitle(parser.nextText().trim());
						item.title = parser.nextText().trim();
						object_title = false;
					} else if (parser.getName().equals("guid")) {
//						person.setTitle(parser.nextText().trim());
						item.url_link = parser.nextText().trim();
					}
					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("item")) {
						news.add(item);
					}
					break;
				default:
					break;
				}
				event = parser.next();
			}
			return news;
		}
	}
}