package cn.wenfee.ssyx.acl.service;

import cn.wenfee.ssyx.model.acl.Admin;
import cn.wenfee.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Wenfee
 * @date 2025/3/27
 */
public interface IAdminService extends IService<Admin> {
    /**
     * 分页查询用户
     *
     * @param pageParam
     * @param userQueryVo 查询对象
     * @return
     */
    IPage<Admin> selectPage(Page<Admin> pageParam, AdminQueryVo userQueryVo);
}
