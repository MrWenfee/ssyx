package cn.wenfee.ssyx.common.result;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 *
 * @author Wenfee
 * @date 2025/3/25
 */
@Getter
public enum ResultCodeEnum {
    /**
     * 成功
     */
    SUCCESS(200, "成功"),
    /**
     * 失败
     */
    FAIL(201, "失败"),
    /**
     * 服务异常
     */
    SERVICE_ERROR(2012, "服务异常"),
    /**
     * 数据异常
     */
    DATA_ERROR(204, "数据异常"),
    /**
     * 非法请求
     */
    ILLEGAL_REQUEST(205, "非法请求"),
    /**
     * 重复提交
     */
    REPEAT_SUBMIT(206, "重复提交"),
    /**
     * 未登陆
     */
    LOGIN_AUTH(208, "未登陆"),
    /**
     * 没有权限
     */
    PERMISSION(209, "没有权限"),
    /**
     * 订单商品价格变化
     */
    ORDER_PRICE_ERROR(210, "订单商品价格变化"),
    /**
     * 订单库存锁定失败
     */
    ORDER_STOCK_FALL(204, "订单库存锁定失败"),
    /**
     * 创建订单失败
     */
    CREATE_ORDER_FAIL(210, "创建订单失败"),
    /**
     * 优惠券已经领取
     */
    COUPON_GET(220, "优惠券已经领取"),
    /**
     * 优惠券已发放完毕
     */
    COUPON_LIMIT_GET(221, "优惠券已发放完毕"),
    /**
     * URL编码失败
     */
    URL_ENCODE_ERROR(216, "URL编码失败"),
    /**
     * 非法回调请求
     */
    ILLEGAL_CALLBACK_REQUEST_ERROR(217, "非法回调请求"),
    /**
     * 获取accessToken失败
     */
    FETCH_ACCESS_TOKEN_FAILED(218, "获取accessToken失败"),
    /**
     * 获取用户信息失败
     */
    FETCH_USERINFO_ERROR(219, "获取用户信息失败"),
    /**
     * 购买个数不能大于限购个数
     */
    SKU_LIMIT_ERROR(230, "购买个数不能大于限购个数"),
    /**
     * 该区域已开通
     */
    REGION_OPEN(240, "该区域已开通"),
    /**
     * 该区域未开通
     */
    REGION_NO_OPEN(240, "该区域未开通"),
    ;


    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
