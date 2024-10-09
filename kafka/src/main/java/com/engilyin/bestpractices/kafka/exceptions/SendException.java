package com.engilyin.bestpractices.kafka.exceptions;

public class SendException extends RuntimeException {

    private static final long serialVersionUID = -2343127568631675834L;

    public SendException(String errorMessage, Exception e) {
	super(errorMessage, e);
    }

}
