package com.pramati.bean;

public class DataBean {
	/*
	 * @attribute Month of the content downloading
	 */
	public int month;
	/*
	 * @attribute Year of the content downloading
	 */
	public int year;
	/*
	 * @attribute Content in byte stream
	 */
	public byte[] content;
	/*
	 * @attribute Type of content downloading
	 */
	public String mailbox_type;

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getMailbox_type() {
		return mailbox_type;
	}

	public void setMailbox_type(String mailbox_type) {
		this.mailbox_type = mailbox_type;
	}

}
