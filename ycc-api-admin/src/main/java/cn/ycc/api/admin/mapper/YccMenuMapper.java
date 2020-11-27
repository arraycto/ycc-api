package cn.ycc.api.admin.mapper;

import cn.ycc.api.admin.entity.YccApiInfo;
import cn.ycc.api.admin.entity.YccMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface YccMenuMapper extends BaseMapper<YccMenu> {
    List<YccMenu> getUserMenusByRoleId(String roleId);
}
