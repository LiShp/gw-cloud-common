package com.gw.cloud.common.base.exception;

/**
 * 业务异常
 *
 * @author cgb
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message, null, false, false);
    }

    public BusinessException(String message, Throwable e) {
        super(message, e, false, false);
    }

    public BusinessException(String message, Throwable e, boolean enableSuppression, boolean writableStackTrace) {
        super(message, e, enableSuppression, writableStackTrace);
    }

    public BusinessException(Throwable e) {
        super(e);
    }
}
