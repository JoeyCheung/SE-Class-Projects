package edu.qc.seclass;

public class MyIndexOutOfBoundsException extends RuntimeException {
	private static final long serialVersionUID = 8226094121089030034L;

	public MyIndexOutOfBoundsException(String message) {
		super(message);
	}

	public MyIndexOutOfBoundsException() {
		super();
	}
}
