package com.example.assignment3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBWrapper extends SQLiteOpenHelper {
	
	public static final String PERSONS = "Persons";
	public static final String ID = "_id";
	public static final String FIRST_NAME = "_firstname";
	public static final String LAST_NAME = "_lastname";
	public static final String ADDRESS = "_address";
	public static final String CREDIT_CARD = "_creditcard";
	
	private static final String DATABASE_NAME = "Persons.db";
	private static final int DATABASE_VERSION = 2;
	
	public static final String DATABASE_CREATE = "create table " + PERSONS + 
				"(" + ID + " PRIMARY KEY, " + FIRST_NAME + " TEXT, " + LAST_NAME +
				" TEXT, " + ADDRESS +" TEXT, " + CREDIT_CARD + " TEXT "+")";
	
	public DBWrapper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		System.out.println(DATABASE_CREATE);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + PERSONS);
		onCreate(db);
	}

}
