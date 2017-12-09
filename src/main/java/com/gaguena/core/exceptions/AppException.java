package com.gaguena.core.exceptions;

import java.io.Serializable;

public class AppException extends RuntimeException implements Serializable{

	private static final long serialVersionUID = 1L;

	public AppException(String msg) {
		super(msg);
	}
}
