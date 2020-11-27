package cn.ycc.api.admin.mapper;

import cn.ycc.api.admin.entity.YccMenu;
import cn.ycc.api.admin.entity.YccPerm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YccPermMapper extends BaseMapper<YccPerm> {

    List<YccPerm> getPermissionByRoleId(@Param("roleId") String roleId);
}
