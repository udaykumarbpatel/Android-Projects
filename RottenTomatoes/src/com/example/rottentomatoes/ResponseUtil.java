/*
a. Midterm
b. ResponseUtil.java
c. Udaykumar Bhupendra Kumar
 */

package com.example.rottentomatoes;

import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class ResponseUtil {

	static public class ResponseXMLPullParser {
		static Response parseResponse(InputStream xmlIn)
				throws XmlPullParserException, NumberFormatException,
				IOException {
			XmlPullParser parser = XmlPullParserFactory.newInstance()
					.newPullParser();
			parser.setInput(xmlIn, "UTF-8");
			Response response = null;
			boolean error_flag = false;
			boolean user_flag = false;
			boolean favorite_flag = false;
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {
				switch (event) {
				case XmlPullParser.START_TAG:
					if (parser.getName().equals("message")) {
						response.error_msg = parser.nextText().trim();
						if (response.error_msg.contains("adding")) {
							response.isFavorite = "true";
						}
					} else if (parser.getName().equals("response")) {
						response = new Response();
					} else if (parser.getName().equals("error")) {
						error_flag = true;
					} else if (parser.getName().equals("id") && error_flag) {
						error_flag = false;
						response.error_id = parser.nextText().trim();
					} else if (parser.getName().equals("user")) {
						user_flag = true;
					} else if (parser.getName().equals("id") && user_flag) {
						user_flag = false;
						response.user_id = parser.nextText().trim();
					} else if (parser.getName().equals("favorites")) {
						favorite_flag = true;
					} else if (parser.getName().equals("isFavorite")
							&& favorite_flag) {
						response.isFavorite = parser.nextText().trim();
						favorite_flag = false;
					} else if (parser.getName().equals("id") && favorite_flag) {
						//String temp = parser.nextText().trim();
//						response.movie_ids = response.movie_ids + temp + ",";
						response.movie_ids = response.movie_ids + parser.nextText().trim() + ",";
					}
					break;
				default:
					break;
				}
				event = parser.next();
			}
			return response;
		}
	}
}