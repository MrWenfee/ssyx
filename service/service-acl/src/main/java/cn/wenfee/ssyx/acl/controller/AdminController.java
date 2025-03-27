package cn.wenfee.ssyx.acl.controller;

import cn.wenfee.ssyx.acl.service.IAdminService;
import cn.wenfee.ssyx.common.result.Result;
import cn.wenfee.ssyx.model.acl.Admin;
import cn.wenfee.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author Wenfee
 * @date 2025/3/27
 */
@Api(tags = "用户管理")
@RequestMapping("/admin/acl/user")
@RestController
public class AdminController {

    private IAdminService adminService;

    @Autowired
    public void setAdminService(IAdminService adminService) {
        if (Objects.isNull(adminService)) {
            throw new NullPointerException("adminService is null");
        }
        this.adminService = adminService;
    }

    /**
     * 分页查询用户列表
     *
     * @param page  页码
     * @param limit 每页数量
     * @return
     */
    @ApiOperation(("用户列表"))
    @GetMapping("{page}/{limit}")
    public Result list(
            @ApiParam(name = "page", value = "页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页数量", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "userQueryVo", value = "查询对象", required = false)
            AdminQueryVo userQueryVo) {
        Page<Admin> pageParam = new Page<>(page, limit);
        IPage<Admin> pageModel = adminService.selectPage(pageParam, userQueryVo);
        return Result.success(pageModel);
    }

    /**
     * 根据用户id查询用户信息
     *
     * @param id 用户id
     * @return
     */
    @ApiOperation("获取用户信息")
    @GetMapping("get/{id}")
    public Result getById(@ApiParam(name = "id", value = "用户id", required = true)
                          @PathVariable Long id) {
        Admin admin = adminService.getById(id);
        return Result.success(admin);
    }

    @ApiOperation("添加用户")
    @PostMapping("save")
    public Result save(@RequestBody Admin user) {
        boolean save = adminService.save(user);
        if (!save) {
            return Result.fail();
        }
        return Result.success();
    }

    @ApiOperation("删除用户")
    @DeleteMapping("remove/{id}")
    public Result removeById(@PathVariable Long id) {
        boolean b = adminService.removeById(id);
        if (!b) {
            return Result.fail();
        }
        return Result.success();
    }


    @ApiOperation("批量删除用户")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        boolean b = adminService.removeByIds(idList);
        if (!b) {
            return Result.fail();
        }
        return Result.success();
    }

    @ApiOperation("更新用户信息")
    @PutMapping("update")
    public Result update(@RequestBody Admin user) {
        adminService.updateById(user);
        return Result.success();
    }
}
