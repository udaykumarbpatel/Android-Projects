package com.example.inclass8;

import java.util.ArrayList;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataManager {
	Context mContext;
	DatabaseHelper dbOpenHelper;
	SQLiteDatabase db;
	NewsDao newsDao;

	public DataManager(Context mContext) {
		this.mContext = mContext;
		dbOpenHelper = new DatabaseHelper(mContext);
		db = dbOpenHelper.getWritableDatabase();
		newsDao = new NewsDao(db);
	}

	public void close() {
		db.close();
	}

	public long saveNote(News note) {
		return newsDao.save(note);
	}

	public boolean deleteNote() {
		return newsDao.delete();
	}

	public ArrayList<News> getAllNotes() {
		return newsDao.getAll();
	}
}