package cn.wenfee.ssyx.acl.service.impl;

import cn.wenfee.ssyx.acl.mapper.AdminRoleMapper;
import cn.wenfee.ssyx.acl.service.IAdminRoleService;
import cn.wenfee.ssyx.model.acl.AdminRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Wenfee
 * @date 2025/3/27
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements IAdminRoleService {
}
