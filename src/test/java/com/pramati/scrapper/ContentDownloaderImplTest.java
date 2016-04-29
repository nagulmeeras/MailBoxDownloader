package com.pramati.scrapper;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class ContentDownloaderImplTest {
	public ContentDownloaderImpl cdi = new ContentDownloaderImpl();

	@Test
	public void testdownloadData() throws IOException {

		byte[] stream = cdi.downloadData("http://mail-archives.apache.org/mod_mbox/maven-users/201411.mbox");
		Assert.assertNotNull(stream);
	}

	@Test(expected = IOException.class)
	public void testdownloadDataException() throws IOException {
		byte[] stream = cdi.downloadData("");
	}
	
}
