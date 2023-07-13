package cn.hellochaos.generator.exception;

import lombok.Getter;


@Getter
public class ViewException extends RuntimeException {
    private final Integer code;

    public ViewException(ViewExceptionType viewExceptionType) {
        super(viewExceptionType.getMessage());
        this.code = viewExceptionType.getCode();
    }

    public ViewException(ViewExceptionType viewExceptionType, String message) {
        super(message);
        this.code = viewExceptionType.getCode();
    }
}
