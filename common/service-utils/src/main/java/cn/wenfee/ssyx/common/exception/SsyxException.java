package cn.wenfee.ssyx.common.exception;

import cn.wenfee.ssyx.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * @author Wenfee
 * @date 2025/3/25
 */
@Data
public class SsyxException extends RuntimeException {
    /**
     * 异常状态码
     */
    private Integer code;

    /**
     * 状态码和异常信息构建异常
     *
     * @param code    错误状态码
     * @param message 错误消息
     */
    public SsyxException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 枚举类构建异常
     *
     * @param resultCodeEnum 枚举类
     */
    public SsyxException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }
}
