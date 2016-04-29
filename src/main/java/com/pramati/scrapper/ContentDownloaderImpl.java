package com.pramati.scrapper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ContentDownloaderImpl implements Downloader {
	
	
	public byte[] downloadData(String url) throws IOException {
		URL webUrl = new URL(url);
		URLConnection connection = webUrl.openConnection();
		connection.connect();
		InputStream is = new BufferedInputStream(connection.getInputStream());
		int length = connection.getContentLength();
		byte[] streamContent = new byte[length];
		int offset = 0;
		int bytes = 0;
		while( offset < length){
			bytes = is.read(streamContent, offset, length-offset);
			if(bytes == -1 )
				break;
			offset += bytes;
		}
		is.close();
		return streamContent;
	}

	

}
