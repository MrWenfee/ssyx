package cn.wenfee.ssyx.acl.service;

import cn.wenfee.ssyx.model.acl.Admin;
import cn.wenfee.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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


    /**
     * 删除用户
     *
     * @param id 用户id
     * @return
     */
    boolean removeByAdminId(Long id);

    /**
     * 批量删除
     *
     * @param idList 用户id列表
     * @return
     */
    boolean batchRemove(List<Long> idList);
}
