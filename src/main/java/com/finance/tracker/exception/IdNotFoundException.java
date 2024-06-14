package com.finance.tracker.exception;

public class IdNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String msg;
	public IdNotFoundException(String msg)
	{
		this.msg = msg;
	}

}
