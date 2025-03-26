package cn.wenfee.ssyx.common.result;

import lombok.Data;

import java.util.Objects;

/**
 * 统一返回结果类
 *
 * @author Wenfee
 * @date 2025/3/25
 */
@Data
public class Result<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 接口响应消息
     */
    private String message;
    /**
     * 接口响应数据
     */
    private T data;

    /**
     * 无参构造私有化
     */
    private Result() {
    }

    public static <T> Result<T> build(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> build(T data, ResultCodeEnum resultCodeEnum) {
        Result<T> result = new Result<>();
        // 数据不为空
        if (Objects.nonNull(data)) {
            result.setData(data);
        }
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(result.getMessage());
        return result;
    }

    /**
     * 成功不响应数据
     *
     * @return
     */
    public static <T> Result<T> success() {
        Result<Object> build = build(null, ResultCodeEnum.SUCCESS);
        return (Result<T>) build;
    }

    /**
     * 成功并响应数据
     *
     * @param data 响应数据
     * @return
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = build(data, ResultCodeEnum.SUCCESS);
        return result;
    }

    /**
     * 响应失败
     *
     * @return
     */
    public static <T> Result<T> fail() {
        Result<Object> build = build(null, ResultCodeEnum.SUCCESS);
        return (Result<T>) build;
    }

    /**
     * 响应失败
     *
     * @param data 响应数据
     * @return
     */
    public static <T> Result<T> fail(T data) {
        Result<Object> build = build(data, ResultCodeEnum.SUCCESS);
        return (Result<T>) build;
    }

}
