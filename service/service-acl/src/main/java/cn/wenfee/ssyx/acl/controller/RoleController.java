package cn.wenfee.ssyx.acl.controller;

import cn.wenfee.ssyx.acl.service.IRoleService;
import cn.wenfee.ssyx.common.result.Result;
import cn.wenfee.ssyx.model.acl.Role;
import cn.wenfee.ssyx.vo.acl.RoleQueryVo;
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
 * 角色管理
 *
 * @author Wenfee
 * @date 2025/3/26
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/admin/acl/role")
public class RoleController {

    private IRoleService roleService;

    @Autowired
    public void setRoleService(IRoleService roleService) {
        if (Objects.isNull(roleService)) {
            throw new NullPointerException("roleService is null");
        }
        this.roleService = roleService;
    }

    @ApiOperation("角色列表")
    @GetMapping("{page}/{limit}")
    public Result list(
            @ApiParam(name = "page", value = "页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页数量", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "roleQueryVo", value = "请求对象", required = false)
            RoleQueryVo roleQueryVo) {
        Page<Role> rolePage = new Page<>(page, limit);
        IPage<Role> pageModel = roleService.selectPage(rolePage, roleQueryVo);
        return Result.success(pageModel);
    }

    /**
     * 根据角色id获取角色信息
     *
     * @param roleId 角色id
     * @return
     */
    @ApiOperation("获取角色")
    @GetMapping("{roleId}")
    public Result getRole(
            @ApiParam(name = "roleId", value = "角色id", required = true)
            @PathVariable Integer roleId) {
        Role role = roleService.getById(roleId);
        return Result.success(role);
    }

    /**
     * 新增角色
     *
     * @param role 角色信息
     * @return
     */
    @ApiOperation("新增角色")
    @PostMapping("save")
    public Result saveRole(@RequestBody Role role) {
        boolean save = roleService.save(role);
        if (save) {
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    @ApiOperation("更新角色")
    @PutMapping("update")
    public Result updateById(@RequestBody Role role) {
        roleService.updateById(role);
        return Result.success();
    }

    @ApiOperation("删除角色")
    @DeleteMapping("remove/{id}")
    public Result remove(
            @ApiParam(name = "id", value = "角色id", required = true)
            @PathVariable Integer id) {
        roleService.deleteById(id);
        return Result.success();
    }

    @ApiOperation("批量删除角色")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Integer> idList) {
        roleService.batchRemoveByIds(idList);
        return Result.success();
    }

}
