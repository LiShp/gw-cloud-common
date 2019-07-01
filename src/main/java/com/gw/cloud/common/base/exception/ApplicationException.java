package com.gw.cloud.common.base.exception;

/**
 * 程序异常
 *
 * @author cgb
 */
public class ApplicationException extends RuntimeException {

    public ApplicationException() {
        super();
    }

    public ApplicationException(String message) {
        super(message, null, false, false);
    }

    public ApplicationException(String message, Throwable e) {
        super(message, e, false, false);
    }

    public ApplicationException(Throwable e) {
        super(null, e, false, false);
    }
}
