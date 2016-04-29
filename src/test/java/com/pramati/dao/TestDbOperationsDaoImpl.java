package com.pramati.dao;

import java.sql.SQLException;

import org.junit.Test;

public class TestDbOperationsDaoImpl {

	@Test(expected=NullPointerException.class)
	public void testSaveData() throws ClassNotFoundException, SQLException{
		DbOperationsDaoImpl dbOperationsDaoImpl = new DbOperationsDaoImpl(null);
	}
}
