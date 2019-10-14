package bussiness;

import java.util.ArrayList;

import models.Record;

public class MockDatabase {
	static ArrayList<Record> records = new ArrayList<Record>();

	public synchronized void add(Record record) {
		records.add(record);
	}

	public synchronized ArrayList<Record> getRecords() {
		return records;
	}
}
