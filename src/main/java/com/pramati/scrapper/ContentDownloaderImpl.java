package com.pramati.scrapper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class ContentDownloaderImpl implements Downloader {
	/*
	 * @attribute String url fully qualified downloadable url
	 */
	public String url;
	/*
	 * @attribute Map Shared object of maintaining all the 
	 * urls downloaded data as url and stream content
	 */
	public Map<String , byte[]> sharedContent;
	/*
	 * @attribute CountDownLatch 
	 */
	public CountDownLatch latch;
	public ContentDownloaderImpl(String url , Map<String , byte[]> sharedContent , CountDownLatch latch){
		this.url = url;
		this.sharedContent = sharedContent;
		this.latch = latch;
	}
	
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

	public void run() {
		try {
			
			sharedContent.put(url, downloadData(url));
			/*
			 * Decrementing the latch count of threads
			 */
			latch.countDown();
		} catch (IOException e) {
			latch.countDown();
			e.printStackTrace();
		}
	}

}
