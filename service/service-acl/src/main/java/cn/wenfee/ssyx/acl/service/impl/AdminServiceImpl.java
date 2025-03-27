package cn.wenfee.ssyx.acl.service.impl;

import cn.wenfee.ssyx.acl.mapper.AdminMapper;
import cn.wenfee.ssyx.acl.service.IAdminService;
import cn.wenfee.ssyx.model.acl.Admin;
import cn.wenfee.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Wenfee
 * @date 2025/3/27
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Override
    public IPage<Admin> selectPage(Page<Admin> pageParam, AdminQueryVo userQueryVo) {
        String name = userQueryVo.getName();
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<Admin>()
                .like(!StringUtils.isEmpty(name), Admin::getName, name);
        Page<Admin> adminPage = baseMapper.selectPage(pageParam, queryWrapper);
        return adminPage;
    }


}
