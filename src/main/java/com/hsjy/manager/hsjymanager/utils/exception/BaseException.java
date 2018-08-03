package com.hsjy.manager.hsjymanager.utils.exception;

/**
 * @author XX
 */
public class BaseException extends RuntimeException {
    private int states = 200;

    public int getStates() {
        return states;
    }

    public void setStates(int states) {
        this.states = states;
    }

    public BaseException() {
    }

    public BaseException(String message, int states) {
        super(message);
        this.states = states;
    }
    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
