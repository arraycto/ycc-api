package cn.ycc.api.admin.service.impl;

import cn.ycc.api.admin.entity.YccRole;
import cn.ycc.api.admin.mapper.YccRoleMapper;
import cn.ycc.api.admin.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuchaoqun
 * @email chaoqunyu9311@163.com
 * @date 2020.11.24 21:21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<YccRoleMapper, YccRole> implements RoleService {

}
