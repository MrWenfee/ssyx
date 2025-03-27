package cn.wenfee.ssyx.acl.service.impl;

import cn.wenfee.ssyx.acl.mapper.RoleMapper;
import cn.wenfee.ssyx.acl.service.IRoleService;
import cn.wenfee.ssyx.model.acl.Role;
import cn.wenfee.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Wenfee
 * @date 2025/3/26
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Override
    public IPage<Role> selectPage(Page<Role> rolePage, RoleQueryVo roleQueryVo) {
        String roleName = roleQueryVo.getRoleName();
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<Role>()
                .like(!StringUtils.isEmpty(roleName), Role::getRoleName, roleName);
        Page<Role> page = baseMapper.selectPage(rolePage, queryWrapper);
        return page;
    }
}
