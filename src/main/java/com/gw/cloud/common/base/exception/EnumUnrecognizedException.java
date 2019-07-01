package com.gw.cloud.common.base.exception;

/**
 * 未识别的枚举类异常
 *
 * @author cgb
 */
public class EnumUnrecognizedException extends RuntimeException {

    public EnumUnrecognizedException() {
        super();
    }

    public EnumUnrecognizedException(String message) {
        super(message, null, false, false);
    }

    public EnumUnrecognizedException(String message, Throwable e) {
        super(message, e, false, false);
    }

    public EnumUnrecognizedException(String message, Throwable e, boolean enableSuppression, boolean writableStackTrace) {
        super(message, e, enableSuppression, writableStackTrace);
    }

    public EnumUnrecognizedException(Throwable e) {
        super(e);
    }
}
