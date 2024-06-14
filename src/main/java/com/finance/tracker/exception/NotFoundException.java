package com.finance.tracker.exception;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = -4389602573233046484L;

	public NotFoundException(String msg) {
		super(msg);
	}
}
