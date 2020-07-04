package com.chong.MyException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author
 * @decription
 * @date:2020/7/4 17:01
 */
@ResponseStatus(HttpStatus.NOT_FOUND)//返回状态--->404
public class NotFindException extends RuntimeException {
    public NotFindException() {
        super();
    }

    public NotFindException(String message) {
        super(message);
    }

    public NotFindException(String message, Throwable cause) {
        super(message, cause);
    }
}
