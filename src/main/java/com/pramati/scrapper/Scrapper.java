package com.pramati.scrapper;

import java.util.Map;

public interface Scrapper {
	/*
	 * @attribute Base url for getting the other urls
	 * @attribute YearToFetch 
	 * @return Map of url and month 
	 * 
	 * It will parse the html content and get the urls based on the
	 * year mentioned in the YearToFetch 
	 * and will return the Map object of url to downlaod and month number
	 */
	public Map<String , Integer> getUrlsByYear();
}
