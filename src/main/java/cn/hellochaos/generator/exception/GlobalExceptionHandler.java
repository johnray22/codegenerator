package cn.hellochaos.generator.exception;

import cn.hellochaos.generator.entity.dto.ResponseData;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

/**
 * @author Weng Yang
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseData validationErrorHandler(Exception e) {
        List<FieldError> fieldErrors = Lists.newArrayListWithCapacity(0);
        List<ObjectError> objectErrors = Lists.newArrayListWithCapacity(0);
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            fieldErrors = ex.getBindingResult().getFieldErrors();
            objectErrors = ex.getBindingResult().getGlobalErrors();
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            fieldErrors = ex.getBindingResult().getFieldErrors();
            objectErrors = ex.getBindingResult().getGlobalErrors();
        }

        List<ValidationError> errors = Lists.newArrayListWithCapacity(fieldErrors.size() + objectErrors.size());
        for (FieldError fieldError : fieldErrors) {
            errors.add(new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        for (ObjectError objectError : objectErrors) {
            errors.add(new ValidationError(objectError.getObjectName(), objectError.getDefaultMessage()));
        }

        return new ResponseData<>(ImmutableMap.of("errors", errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ViewException.class)
    public ResponseData viewExceptionHandler(ViewException ex) {
        StringBuilder stringBuilder = new StringBuilder(ex.getMessage());
        if (!ObjectUtils.isEmpty(ex.getStackTrace())) {
            StackTraceElement traceElement = ex.getStackTrace()[0];
            stringBuilder.append(" location: ").append(traceElement.getClassName()).append(":").append(traceElement.getLineNumber());
        }
        log.error(stringBuilder.toString());
        return new ResponseData(ex.getCode(), ex.getMessage());
    }

//    @ExceptionHandler(DuplicateKeyException.class)
//    public ResponseData duplicateKeyExceptionHandler(DuplicateKeyException e) {
//        log.error(e.getMessage(), e);
//        String duplicateStr = ExceptionUtil.parseDuplicateStr(e);
//        return new ResponseData(ViewExceptionType.DATA_ALREADY_EXISTS.getCode(), ViewExceptionType.DATA_ALREADY_EXISTS.getMessage() + ": " + duplicateStr);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseData<Map<String, Object>> defaultErrorHandler(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseData<>(ImmutableMap.of("message", "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
