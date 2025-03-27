package cn.wenfee.ssyx.acl.service.impl;

import cn.wenfee.ssyx.acl.mapper.AdminMapper;
import cn.wenfee.ssyx.acl.service.IAdminRoleService;
import cn.wenfee.ssyx.acl.service.IAdminService;
import cn.wenfee.ssyx.model.acl.Admin;
import cn.wenfee.ssyx.model.acl.AdminRole;
import cn.wenfee.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Wenfee
 * @date 2025/3/27
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    private IAdminRoleService adminRoleService;

    @Autowired
    public void setAdminRoleService(IAdminRoleService adminRoleService) {
        if (Objects.isNull(adminRoleService)) {
            throw new NullPointerException("adminRoleService is null");
        }
        this.adminRoleService = adminRoleService;
    }

    @Override
    public IPage<Admin> selectPage(Page<Admin> pageParam, AdminQueryVo userQueryVo) {
        String name = userQueryVo.getName();
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<Admin>()
                .like(!StringUtils.isEmpty(name), Admin::getName, name);
        Page<Admin> adminPage = baseMapper.selectPage(pageParam, queryWrapper);
        return adminPage;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeByAdminId(Long id) {
        baseMapper.deleteById(id);
        // 删除用户与角色关联表信息
        adminRoleService.remove(new LambdaQueryWrapper<AdminRole>()
                .eq(AdminRole::getAdminId, id));
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean batchRemove(List<Long> idList) {
        baseMapper.deleteBatchIds(idList);
        // 删除用户与角色关联表信息
        List<AdminRole> adminRoleList = adminRoleService.list(new LambdaQueryWrapper<AdminRole>()
                .in(AdminRole::getAdminId, idList));
        List<Long> adminRoleIds = adminRoleList.stream()
                .filter(Objects::nonNull)
                .map(item -> item.getId())
                .collect(Collectors.toList());
        adminRoleService.removeByIds(adminRoleIds);
        return true;
    }
}
