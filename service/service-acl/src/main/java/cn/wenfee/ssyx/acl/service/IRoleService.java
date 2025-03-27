package cn.wenfee.ssyx.acl.service;

import cn.wenfee.ssyx.model.acl.Role;
import cn.wenfee.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Wenfee
 * @date 2025/3/26
 */
public interface IRoleService extends IService<Role> {

    /**
     * 角色列表：分页查询
     * @param rolePage
     * @param roleQueryVo 查询对象
     * @return
     */
    IPage<Role> selectPage(Page<Role> rolePage, RoleQueryVo roleQueryVo);
}
