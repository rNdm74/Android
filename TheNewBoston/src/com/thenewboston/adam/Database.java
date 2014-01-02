package com.thenewboston.adam;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends Activity{
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "name";
	public static final String KEY_RATING = "rating";
	public static final String KEY_AGE = "age";
	
	private static final String DATABASE_NAME = "android";	
	private static final String DATABASE_TABLE = "person";	
	private static final int DATABASE_VERSION = 1;
	
	private DbHelper helper;
	private final Context context;
	private SQLiteDatabase database;
	
	public Database(Context c){
		context = c;
	}
		
	public Database open() throws SQLException{
		helper = new DbHelper(context);
		database = helper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		helper.close();
		
	}
	
	public String getData() {
		// Start columns from database table
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_RATING, KEY_AGE};
		
		Cursor reader = database.query(DATABASE_TABLE, columns, null, null, null, null, null, null);
		String result = "| ";
		
		int iRow = reader.getColumnIndex(KEY_ROWID);
		int iName = reader.getColumnIndex(KEY_NAME);
		int iRating = reader.getColumnIndex(KEY_RATING);
		int iAge = reader.getColumnIndex(KEY_AGE);
		
		for (reader.moveToFirst(); !reader.isAfterLast(); reader.moveToNext()) {
			result =  reader + 
					  reader.getString(iRow) 	+ " | " +
					  reader.getString(iName) 	+ " | " + 
					  reader.getString(iRating) + " | " +
					  reader.getString(iAge) 	+ " |\n";
		}
		
		return result;
	}
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(
			
				"CREATE TABLE " + DATABASE_TABLE + " (" +
				KEY_ROWID 	+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				KEY_NAME 	+ " TEXT NOT NULL, " +
				KEY_RATING 	+ " INTEGER NOT NULL, " +
				KEY_AGE 	+ " INTEGER NOT NULL);"	
			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_NAME);
			onCreate(db);
		}		
	}

	public long createEntry(String sqlName, int sqlRating, int sqlAge) {
			ContentValues contentValues = new ContentValues();
			
			contentValues.put(KEY_NAME, sqlName);
			contentValues.put(KEY_RATING, sqlRating);
			contentValues.put(KEY_AGE, sqlAge);
			
			// Insert all data into table
			return database.insert(DATABASE_TABLE, null, contentValues);		
	}
}
