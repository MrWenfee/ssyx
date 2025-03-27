package cn.wenfee.ssyx.acl.controller;

import cn.wenfee.ssyx.common.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author Wenfee
 * @date 2025/3/26
 */
@RestController
@RequestMapping("/admin/acl/index")
public class IndexController {

    /**
     * 登陆接口
     * TODO 简单跳过登陆页面，具体逻辑未实现
     *
     * @return Token
     */
    @PostMapping("login")
    public Result login() {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", "admin-token");
        return Result.success(map);
    }

    /**
     * 过去用户信息   TODO 获取用户信息，未完成
     *
     * @return
     */
    @GetMapping("info")
    public Result info() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "admin");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.success(map);
    }

    /**
     * 退出登陆接口       TODO 未完成，简单实现跳过网页逻辑
     *
     * @return
     */
    @GetMapping("logout")
    public Result logout() {
        return Result.success();
    }
}
