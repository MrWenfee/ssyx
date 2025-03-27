package cn.wenfee.ssyx.acl.service.impl;

import cn.wenfee.ssyx.acl.mapper.RoleMapper;
import cn.wenfee.ssyx.acl.service.IAdminRoleService;
import cn.wenfee.ssyx.acl.service.IRoleService;
import cn.wenfee.ssyx.common.exception.SsyxException;
import cn.wenfee.ssyx.common.result.ResultCodeEnum;
import cn.wenfee.ssyx.model.acl.AdminRole;
import cn.wenfee.ssyx.model.acl.Role;
import cn.wenfee.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Wenfee
 * @date 2025/3/26
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    private IAdminRoleService adminRoleService;

    @Autowired
    public void setAdminRoleService(IAdminRoleService adminRoleService) {
        if (Objects.isNull(adminRoleService)) {
            throw new NullPointerException("adminRoleService is null");
        }
        this.adminRoleService = adminRoleService;
    }

    @Override
    public IPage<Role> selectPage(Page<Role> rolePage, RoleQueryVo roleQueryVo) {
        String roleName = roleQueryVo.getRoleName();
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<Role>()
                .like(!StringUtils.isEmpty(roleName), Role::getRoleName, roleName);
        Page<Role> page = baseMapper.selectPage(rolePage, queryWrapper);
        return page;
    }

    @Override
    public Map<String, Object> findRoleByUserId(Long adminId) {
        // 获取所有角色信息
        List<Role> roleList = baseMapper.selectList(null);

        // 获取用户表与角色表关联信息，根据用户id获取所有角色表id
        List<AdminRole> existUserRoleList = adminRoleService.list(new LambdaQueryWrapper<AdminRole>()
                .eq(AdminRole::getAdminId, adminId));
        // 获取所有角色id
        List<Long> userRoleIdList = existUserRoleList.stream()
                .filter(Objects::nonNull)
                .map(item -> item.getRoleId())
                .collect(Collectors.toList());

        // 通过用户id查询出来的角色id，在所有角色信息中筛选出来
        ArrayList<Role> assignRoles = new ArrayList<>();
        for (Role role : roleList) {
            if (userRoleIdList.contains(role.getId())) {
                assignRoles.add(role);
            }
        }

        // 组装响应数据
        HashMap<String, Object> map = new HashMap<>();
        map.put("assignRoles", assignRoles);
        map.put("allRolesList", roleList);
        return map;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUserRoleRealtionShip(Long adminId, Long[] roleIds) {
        // 先删除该用户全部角色信息
        adminRoleService.remove(new LambdaQueryWrapper<AdminRole>()
                .eq(AdminRole::getAdminId, adminId));

        // 根据角色id列表，批量分配橘色
        List<AdminRole> adminRoles = new ArrayList<>();
        for (Long roleId : roleIds) {
            AdminRole adminRole = new AdminRole();
            adminRole.setRoleId(roleId);
            adminRole.setAdminId(adminId);
            adminRoles.add(adminRole);
        }

        if (adminRoles.size() == 0) {
            throw new SsyxException(ResultCodeEnum.FAIL.getCode(), "角色分配失败");
        }
        adminRoleService.saveBatch(adminRoles);
    }
}
