package com.example.inclass8;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class NewsTable {
	static final String TABLE_NAME = "news";
	static final String News_TITLE = "title";
	static final String News_URL = "note";

	static public void onCreate(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + NewsTable.TABLE_NAME + " (");
		sb.append(NewsTable.News_TITLE + " text not null, ");
		sb.append(NewsTable.News_URL + " text primary key");
		
		sb.append(");");
		try {
			db.execSQL(sb.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static public void onUpgrade(SQLiteDatabase db, int oldVersion,
			int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + NewsTable.TABLE_NAME);
		NewsTable.onCreate(db);
	}
}