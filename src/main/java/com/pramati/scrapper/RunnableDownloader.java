package com.pramati.scrapper;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class RunnableDownloader implements Runnable {
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
	public RunnableDownloader(String url , Map<String , byte[]> sharedContent , CountDownLatch latch) {
		this.url = url;
		this.sharedContent = sharedContent;
		this.latch = latch;
	}
	public void run() {
		try {
			Downloader downloader = new ContentDownloaderImpl();
			sharedContent.put(url, downloader.downloadData(url));
			/*
			 * Decrementing the latch count of threads
			 */
			latch.countDown();
		} catch (Exception e) {
			latch.countDown();
			e.printStackTrace();
		}
	}
}
