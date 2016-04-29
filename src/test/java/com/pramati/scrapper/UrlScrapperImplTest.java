package com.pramati.scrapper;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pramati.util.ScrapeUtils;

public class UrlScrapperImplTest {
	public Map<String, Integer> expecteds = new HashMap<String, Integer>();

	@Before
	public void setUp() {
		expecteds.put("http://mail-archives.apache.org/mod_mbox/maven-users/201403.mbox", 3);
		expecteds.put("http://mail-archives.apache.org/mod_mbox/maven-users/201406.mbox", 6);
		expecteds.put("http://mail-archives.apache.org/mod_mbox/maven-users/201402.mbox", 2);
		expecteds.put("http://mail-archives.apache.org/mod_mbox/maven-users/201401.mbox", 1);
		expecteds.put("http://mail-archives.apache.org/mod_mbox/maven-users/201404.mbox", 4);
		expecteds.put("http://mail-archives.apache.org/mod_mbox/maven-users/201410.mbox", 10);
		expecteds.put("http://mail-archives.apache.org/mod_mbox/maven-users/201408.mbox", 8);
		expecteds.put("http://mail-archives.apache.org/mod_mbox/maven-users/201409.mbox", 9);
		expecteds.put("http://mail-archives.apache.org/mod_mbox/maven-users/201405.mbox", 5);
		expecteds.put("http://mail-archives.apache.org/mod_mbox/maven-users/201407.mbox", 7);
		expecteds.put("http://mail-archives.apache.org/mod_mbox/maven-users/201412.mbox", 12);
		expecteds.put("http://mail-archives.apache.org/mod_mbox/maven-users/201411.mbox", 11);
	}

	@Test
	public void testGetUrlsByYearNotNull() throws IOException{
		Document document  = ScrapeUtils.getDocument("http://mail-archives.apache.org/mod_mbox/maven-users/");
		UrlScrapperImpl urlScrapperImpl = new UrlScrapperImpl(document , "2014");
		Map<String,Integer> actuals = urlScrapperImpl.getUrlsByYear();
		Assert.assertNotNull(actuals);
	}
	
//	@Test
//	public void testGetUrlsByYearSame() throws Exception{
//		Document document  = ScrapeUtils.getDocument("http://mail-archives.apache.org/mod_mbox/maven-users/");
//		UrlScrapperImpl urlScrapperImpl = new UrlScrapperImpl(document , "2014");
//		Map<String,Integer> actuals = urlScrapperImpl.getUrlsByYear();
//		for (String url : actuals.keySet()) {
//			System.out.println(url+"="+actuals.get(url));
//		}
//		Assert.assertSame(expecteds, actuals);
//	}
}
