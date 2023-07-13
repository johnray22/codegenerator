package cn.hellochaos.generator.entity.dto;

import cn.hellochaos.generator.exception.ViewExceptionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> implements Serializable {


    private static final long serialVersionUID = 3361054312887907259L;


//    @ApiModelProperty(notes = "返回状态码", name = "code", value = "返回200，调用成功")
    private int code;


//    @ApiModelProperty(notes = "返回信息", name = "info", value = "返回信息描述")
    private String info;


//    @ApiModelProperty(value = "请求报文", name = "body")
    private T data;

    public ResponseData(T data) {
        this(ViewExceptionType.SUCCESS);
        this.data = data;
    }

    public ResponseData(ViewExceptionType viewExceptionType) {
        this.code = viewExceptionType.getCode();
        this.info = viewExceptionType.getMessage();
    }

    public ResponseData(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public ResponseData(T data, HttpStatus httpStatus) {
        this.data = data;
        this.code = httpStatus.value();
        this.info = httpStatus.getReasonPhrase();
    }
}
