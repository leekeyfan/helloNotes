package cn.com.test.hellonotes;

import android.R.string;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesDB extends SQLiteOpenHelper {

	public static final String TABLE_NAME = "notes";
	public static final String CONTENT = "content";
	public static final String ID = "_id";
	public static final String TIME = "time";
	public static final String PATH = "path";
	public static final String VIDEO = "video";

	public NotesDB(Context context) {
		super(context, "Notes", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);  
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + CONTENT
				+ " TEXT NOT NULL, " + TIME + " TEXT NOT NULL, " + PATH
				+ " TEXT, " + VIDEO + " TEXT)");
//		db.execSQL("CREATE TABLE IF NOT EXISTS students (_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, sex VARCHAR,grade INTEGER, info VARCHAR)");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
