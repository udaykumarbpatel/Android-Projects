package com.example.inclass8;

import java.util.ArrayList;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NewsDao {
	private SQLiteDatabase db;

	public NewsDao(SQLiteDatabase db) {
		this.db = db;
	}

	public long save(News item) {
		ContentValues values = new ContentValues();
		values.put(NewsTable.News_TITLE, item.getTitle());
		values.put(NewsTable.News_URL, item.getUrl_link());
		return db.insert(NewsTable.TABLE_NAME, null, values);
	}

	public boolean delete() {
		return db.delete(NewsTable.TABLE_NAME, null, null) > 0;
	}

	public ArrayList<News> getAll() {
		ArrayList<News> list = new ArrayList<News>();
		Cursor c = db.query(NewsTable.TABLE_NAME, new String[] {
				NewsTable.News_TITLE, NewsTable.News_URL }, null, null, null,
				null, null);
		if (c != null && c.moveToFirst()) {
			do {
				News note = this.buildNoteFromCursor(c);
				if (note != null) {
					list.add(note);
				}
			} while (c.moveToNext());
			if (!c.isClosed()) {
				c.close();
			}
		}
		return list;
	}

	private News buildNoteFromCursor(Cursor c) {
		News item = null;
		if (c != null) {
			item = new News();
			item.setTitle(c.getString(0));
			item.setUrl_link(c.getString(1));

		}
		return item;
	}
}
