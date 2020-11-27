package cn.ycc.api.admin.service;

import cn.ycc.api.admin.commons.exceptions.YccException;
import cn.ycc.api.admin.entity.YccPerm;
import cn.ycc.api.admin.entity.YccRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author yuchaoqun
 * @email chaoqunyu9311@163.com
 * @date 2020.11.24 21:20
 */
public interface PermissionService extends IService<YccPerm> {
    List<YccPerm> getPermissionByRoleId(String roleId) throws YccException;
}
