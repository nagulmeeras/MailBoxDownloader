package com.pramati.scrapper;

public interface Downloader {
	/*
	 * @attribute String url fully qualified url for downloading the content
	 * @return byte[] stream of content which is downloaded
	 * 
	 * It will download the any type of data i.e .txt .mp3 , .mp4 ..etc return it 
	 * Stream format
	 */
	public byte[] downloadData(String url)throws Exception;
}
