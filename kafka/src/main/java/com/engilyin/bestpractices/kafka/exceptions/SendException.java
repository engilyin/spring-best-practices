package com.engilyin.bestpractices.kafka.exceptions;

public class SendException extends RuntimeException {

    public SendException(String errorMessage, Exception e) {
	super(errorMessage, e);
    }

}
