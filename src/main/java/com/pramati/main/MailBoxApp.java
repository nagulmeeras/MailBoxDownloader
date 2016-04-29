package com.pramati.main;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import com.pramati.bean.DataBean;
import com.pramati.dao.DbOperationsDaoImpl;
import com.pramati.dao.FileOperationsDaoImpl;
import com.pramati.dao.OperationsDao;
import com.pramati.scrapper.ContentDownloaderImpl;
import com.pramati.scrapper.Downloader;
import com.pramati.scrapper.Scrapper;
import com.pramati.scrapper.UrlScrapperImpl;
import com.pramati.util.FileUtils;
import com.pramati.util.ScrapeUtils;

public class MailBoxApp {
	/*
	 * Log4j api for logging the information logger is made final to restrict of
	 * changing and static for accessing any where
	 */
	final static Logger logger = Logger.getLogger(MailBoxApp.class);

	/*
	 * This is the main class for initializing the application
	 * 
	 * For this we need to give the url from which it has
	 * 
	 * to fetch the mail content and we need to mention the year to fetch in the
	 * properties file
	 * 
	 * It will just download the content from the web based on the url and save
	 * it in the required storage model
	 */
	public static void main(String args[]) {
		try {
			Properties properties = FileUtils.getProperties("src/main/resources/MailBox.properties");
			logger.info("Base Url :" + properties.getProperty("BaseUrl"));
			Document document = ScrapeUtils.getDocument(properties.getProperty("BaseUrl"));
			String yearToFetch = properties.getProperty("YearToFetch");
			Scrapper scrapper = new UrlScrapperImpl(document, yearToFetch);
			logger.info("getting urls...");
			Map<String, Integer> urlsToDownload = scrapper.getUrlsByYear();
			OperationsDao fileDaoObject = new FileOperationsDaoImpl();
			//Properties mysqlProperties = FileUtils.getProperties("src/main/resources/mysql.properties");
			//OperationsDao dbDaoObject = new DbOperationsDaoImpl(mysqlProperties);
			Map<String, byte[]> sharedContent = new ConcurrentHashMap<String, byte[]>();
			ExecutorPool executorPool = new ExecutorPool(urlsToDownload , sharedContent);
			executorPool.executePool();
			logger.info("SIZE:" + sharedContent.size());
			for (String url : sharedContent.keySet()) {
				String mailbox_type = url.substring(url.indexOf("mod_mbox") + 9, url.indexOf(yearToFetch) - 1);
				DataBean bean = new DataBean(urlsToDownload.get(url) , Integer.parseInt(yearToFetch) , sharedContent.get(url),mailbox_type);;
				logger.info(bean.getMonth() + "\n" + bean.getYear());
				String fileName = url.substring(url.lastIndexOf('/') + 1, url.length());
				fileDaoObject.saveData(bean, fileName);
				// dbDaoObject.saveData(bean, "");
			}
			//dbDaoObject.closeConnection();
			logger.info("Downloading done...");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
