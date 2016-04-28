package com.pramati.dao;

import com.pramati.bean.DataBean;

public interface OperationsDao {
	/*
	 * @attribute Bean with stream content , month , year , type 
	 * @attribute String fileName
	 * @method Stores the stream content in the bean in respective form
	 * Throws the exceptions
	 */
	public void saveData( DataBean bean, String fileName)throws Exception;
	/*
	 * @method for closing the connection if it has data base connection open
	 * 
	 */
	public void closeConnection()throws Exception;
}
