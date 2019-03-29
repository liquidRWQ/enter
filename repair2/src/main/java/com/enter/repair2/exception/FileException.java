package com.enter.repair2.exception;

/**
* @类名： FileException
*
* @author Liquid
*
* @描述： 文件上传异常
*
* @date   2018/12/28
*/
public class FileException extends RuntimeException{
    private static final long serialVersionUID = -9132827718892071477L;

    public FileException() {
        super();
    }

    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileException(Throwable cause) {
        super(cause);
    }

    protected FileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
