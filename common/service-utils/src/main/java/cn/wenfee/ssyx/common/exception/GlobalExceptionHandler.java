package cn.wenfee.ssyx.common.exception;

import cn.wenfee.ssyx.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类
 *
 * @author Wenfee
 * @date 2025/3/25
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获Exception.class异常
     *
     * @param e 异常
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }

    /**
     * 捕获SsyxException.class异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(SsyxException.class)
    @ResponseBody
    public Result error(SsyxException e) {
        return Result.build(e.getCode(), e.getMessage());
    }
}
