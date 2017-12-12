package com.zhimin115200.test.SkyFavor.common.exception;

public class UserException extends RuntimeException{
    private static final long serialVersionUID = 6630423109000758648L;

    public UserException() {
        super();
    }

    public UserException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(Throwable throwable) {
        super(throwable);
    }
}
