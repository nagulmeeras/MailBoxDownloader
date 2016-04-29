package com.pramati.main;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.pramati.scrapper.RunnableDownloader;

public class ExecutorPool {
	public Map<String , byte[]> sharedContent;
	public Map<String , Integer> mapOfTasks;
	
	public ExecutorPool(Map<String, Integer> urlsToDownload , Map<String , byte[]> sharedContent){
		this.mapOfTasks = urlsToDownload;
		this.sharedContent = sharedContent;
	}
	public void executePool() throws InterruptedException{
		ExecutorService executorService = Executors.newFixedThreadPool(6);
		CountDownLatch latch = new CountDownLatch(mapOfTasks.size());
		for (String url : mapOfTasks.keySet()) {
			RunnableDownloader runnableDownloader = new RunnableDownloader(url, sharedContent, latch);
			executorService.execute(runnableDownloader);
		}
		executorService.shutdown();
		latch.await();
	}
}
