package cn.wenfee.ssyx.acl.service;

import cn.wenfee.ssyx.model.acl.Role;
import cn.wenfee.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author Wenfee
 * @date 2025/3/26
 */
public interface IRoleService extends IService<Role> {

    /**
     * 角色列表：分页查询
     *
     * @param rolePage
     * @param roleQueryVo 查询对象
     * @return
     */
    IPage<Role> selectPage(Page<Role> rolePage, RoleQueryVo roleQueryVo);

    /**
     * 根据用户id 获取拥有的角色信息
     *
     * @param adminId 用户id
     * @return
     */
    Map<String, Object> findRoleByUserId(Long adminId);

    /**
     * 根据用户分配角色
     *
     * @param adminId 用户id
     * @param roleIds 角色id列表
     */
    void saveUserRoleRealtionShip(Long adminId, Long[] roleIds);
}
