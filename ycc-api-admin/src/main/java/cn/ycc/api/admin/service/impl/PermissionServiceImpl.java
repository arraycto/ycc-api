package cn.ycc.api.admin.service.impl;

import cn.ycc.api.admin.commons.exceptions.YccException;
import cn.ycc.api.admin.entity.YccPerm;
import cn.ycc.api.admin.entity.YccRole;
import cn.ycc.api.admin.mapper.YccPermMapper;
import cn.ycc.api.admin.mapper.YccRoleMapper;
import cn.ycc.api.admin.service.PermissionService;
import cn.ycc.api.admin.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuchaoqun
 * @email chaoqunyu9311@163.com
 * @date 2020.11.24 21:21
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<YccPermMapper, YccPerm> implements PermissionService {

    @Override
    public List<YccPerm> getPermissionByRoleId(String roleId) throws YccException {
        return baseMapper.getPermissionByRoleId(roleId);
    }
}
