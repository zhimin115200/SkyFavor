package com.zhimin115200.test.SkyFavor.common.exception;

public class MailException extends RuntimeException{
    private static final long serialVersionUID = 6630423109000758648L;

    public MailException() {
        super();
    }

    public MailException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public MailException(String message) {
        super(message);
    }

    public MailException(Throwable throwable) {
        super(throwable);
    }
}
