package com.pramati.dao;

import java.io.IOException;

import org.junit.Test;

import com.pramati.bean.DataBean;

public class TestFileOperationsDaoImpl {
	public FileOperationsDaoImpl fod = new FileOperationsDaoImpl();
	
	@Test(expected = IOException.class)
	public void testSaveData() throws IOException{
		
		DataBean bean = new DataBean(1, 12, null, "maven");
		fod.saveData(bean, "");
	}
	
	@Test(expected = NullPointerException.class)
	public void testSaveData1() throws IOException{
		fod.saveData(null , "sisiss.txt");
	}
}
