package com.pramati.exceptions;

public class NullContentFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NullContentFoundException(){
		
	}
	public NullContentFoundException(String message){
		super(message);
	}
	
}
