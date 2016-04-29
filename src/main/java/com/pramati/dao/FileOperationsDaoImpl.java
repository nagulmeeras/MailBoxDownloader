package com.pramati.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.pramati.bean.DataBean;
import com.pramati.exceptions.NullContentFoundException;

public class FileOperationsDaoImpl implements OperationsDao {

	public void saveData(DataBean bean, String fileName) throws IOException,FileNotFoundException , NullContentFoundException{

		File file = new File(fileName);
		if(!file.exists()){
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file);
		if(bean != null){
			fos.write(bean.getContent());
		}else{
			fos.close();
			throw new NullContentFoundException("Stream content was found null");
		}
		fos.close();
	}

	public void closeConnection() {

	}

}
