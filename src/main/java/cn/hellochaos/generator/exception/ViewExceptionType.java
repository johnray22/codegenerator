package cn.hellochaos.generator.exception;

import cn.hellochaos.generator.util.EnumUtil;
import lombok.Getter;
import org.springframework.util.Assert;

/**
*@author ZhongruiRao
*@create 2022-05-11 22:12
*/
@Getter
public enum ViewExceptionType {
    // 成功
    SUCCESS(200, "操作成功"),
    // 认证鉴权
    USER_INFO_FAILED(6001, "获取用户信息失败"),
    TOKEN_INVALID(6002, "token错误"),
    TOKEN_EXPIRED(6003, "token过期"),
    TOKEN_NOT_EXIST(6004, "token不存在"),
    NOT_REGISTER(6005, "用户未注册"),
    TOO_MANY_REQUEST(6006,"请求数过多"),
    WX_CODE_ERR(6007,"微信code相关错误"),
    USER_OR_PASSWORD_ERROR(6008, "用户或密码错误"),
    USERNAME_FAILED_UP_MAX_NUM(6009, "用户连续登录失败次数已达最大次数,账户已被冻结"),
    PASSWORD_NOT_MATCH(6010, "用户密码错误"),
    USER_NOT_EXISTS(6011, "用户未注册"),
    SMS_SEND_LIMIT(6012, "短信发送次数已达到最大值"),
    SMS_TIME_LIMIT(6013, "30秒内只能发送一次短信"),
    PHONE_NOT_EXIST(6014, "手机号未注册"),
    SMS_CODE_NOT_MATCH(6015, "验证码错误"),
    SMS_CODE_EXPIRE(6016, "验证码过期"),

    // 业务处理
    BAD_REQUEST(4001, "请求错误"),
    SERVER_ERROR(5001, "服务器错误"),
    DATA_ALREADY_EXISTS(5002, "数据已存在"),
    PHOTO_QUALITY_UNQUALIFIED(5003, "照片质量不合格"),
    ABILITY_TOKEN_WRONG(5004, "获取能力平台token错误"),
    ABILITY_API_WRONG(5005, "调用能力平台接口失败"),
    MEDIA_UPLOAD_WRONG(5006, "素材上传错误"),
    AUTHORIZE_TIME_TOOL_LONG(5008, "时间已超过5分钟，请重新授权"),
    AUTHORIZE_FAILED(5009, "授权失败"),

    PARAM_TYPE_ERROR(5014,"参数类型不正确"),
    EMAIL_FORMAT_ERROR(5015,"邮箱格式不正确"),
    USER_ALREADY_EXISTS(5016, "用户已存在"),
    USERNAME_FORMAT_ERROR(5017, "用户名不能包含\"-\""),
    LOGGED_USER_NOT_AVAILABLE(5018, "未获取到登录用户"),
    INSERT_FAILED(7001, "插入失败"),
    UPDATE_FAILED(7002, "更新失败"),
    DELETE_FAILED(7003, "删除失败"),
    TAG_INFO_NOT_FOUND(7004, "沒有找到对应tagId的信息"),
    NONE_REAL_TIME_INFO(7005, "近20分钟没有标签接触"),
    ;

    private final int code;
    private final String message;

    ViewExceptionType(int code, String message) {
        Assert.isTrue(!EnumUtil.exceptionTypeCodeSet.contains(code), "code不能重复");
        EnumUtil.exceptionTypeCodeSet.add(code);
        this.code = code;
        this.message = message;
    }
}
