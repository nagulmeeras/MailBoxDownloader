package com.pramati.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.pramati.bean.DataBean;

public class FileOperationsDaoImpl implements OperationsDao {

	public void saveData(DataBean bean, String fileName) throws IOException {

		File file = new File(fileName);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(bean.getContent());
		fos.close();
	}

	public void closeConnection() {

	}

}
