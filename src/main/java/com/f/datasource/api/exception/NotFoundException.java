package com.f.datasource.api.exception;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-20 上午11:29
 **/
public class NotFoundException extends BusinessException {
    public NotFoundException(String message) {
        super(message, 404);
    }

    public NotFoundException() {
        this("data not found");
    }
}
