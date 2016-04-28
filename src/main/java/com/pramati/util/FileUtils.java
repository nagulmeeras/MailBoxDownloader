package com.pramati.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtils {

	/*
	 * @attribute String fileName fully qualified name from class path to get
	 * the properties
	 * 
	 * @return Properties
	 * 
	 */
	public static Properties getProperties(String fileName) throws IOException {
		Properties properties = null;
		File file = new File(fileName);
		if (!file.exists()) {
			file = new File(fileName.replace("src/main", "Crawler"));
		}

		FileInputStream fis = new FileInputStream(file);
		properties = new Properties();
		properties.load(fis);
		fis.close();
		return properties;
	}
}
