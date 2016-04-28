package com.pramati.scrapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class UrlScrapperImpl implements Scrapper{
	public Document document;
	public String yearToFetch; 
	final static Logger logger = Logger.getLogger(UrlScrapperImpl.class);
	public UrlScrapperImpl(Document document , String yearToFetch){
		this.document = document;
		this.yearToFetch = yearToFetch;
	}
	public Map<String , Integer> getUrlsByYear() {
		
		Map<String , Integer> urlsToDownload = new HashMap<String , Integer>();
		Elements urls = document.select("a[href]");
		for (Element url : urls) {
			/*
			 * Only urls with pattern matched with the year
			 */
			if(url.attr("href").contains(yearToFetch)){
				String link = url.absUrl("href");
				String month = link.substring(link.indexOf(yearToFetch)+4, link.indexOf(yearToFetch)+6);
				logger.info("month:"+month);
				String downloadableUrl = link.substring(0,link.lastIndexOf('/'));
				urlsToDownload.put(downloadableUrl, Integer.parseInt(month));
			}
		}
		return urlsToDownload;
	}

}
