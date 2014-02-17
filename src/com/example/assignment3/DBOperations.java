package com.example.assignment3;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBOperations {

	private DBWrapper dbWrapper;
	private String[] PERSON_TABLE_COLUMNS = {DBWrapper.ID, DBWrapper.FIRST_NAME, 
			DBWrapper.LAST_NAME, DBWrapper.ADDRESS, DBWrapper.CREDIT_CARD};
	private SQLiteDatabase database;
	
	public DBOperations(Context context) {
		dbWrapper = new DBWrapper(context);
	}
	
	public void open() throws SQLException {
		database = dbWrapper.getWritableDatabase();
	}
	
	public void close() {
		dbWrapper.close();
	}
	
	public void addPerson(String firstName, String lastName, String address, 
			String creditCard ) {
		System.out.println(firstName+lastName+address+creditCard);
		ContentValues values = new ContentValues();
		values.put(DBWrapper.FIRST_NAME, firstName);
		values.put(DBWrapper.LAST_NAME, lastName);
		values.put(DBWrapper.ADDRESS, address);
		values.put(DBWrapper.CREDIT_CARD, creditCard);
		
		System.out.println(values.toString());
		
		long id = database.insert(DBWrapper.PERSONS, null, values);
		
		System.out.println("ID is"+id);
		
	}
	
	public List<Person> getAllPersons() {
		List<Person> persons = new ArrayList<Person>();
		Cursor cursor = database.query(DBWrapper.PERSONS, PERSON_TABLE_COLUMNS, null, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Person person = parsePerson(cursor);
			persons.add(person);
			cursor.moveToNext();
		}
		
		cursor.close();
		return persons;
		
	}
	
	private Person parsePerson(Cursor cursor) {
		System.out.println(cursor.toString());
		Person person = new Person();
		person.setId(cursor.getInt(0));
		person.setFirstName(cursor.getString(1));
		person.setLastName(cursor.getString(2));
		person.setAddress(cursor.getString(3));
		person.setCreditCard(cursor.getString(4));
		return person;
		
	}
	
}
