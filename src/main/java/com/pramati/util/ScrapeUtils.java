package com.pramati.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ScrapeUtils {
	/*
	 * @attribute String url fully qualified url for getting the html document
	 * @return Document 
	 */
	public static Document getDocument(String url) throws IOException{
		Document document = Jsoup.connect(url).get();
		return document;
	}
}
