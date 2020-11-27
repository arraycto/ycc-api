package cn.ycc.api.admin.service;

import cn.ycc.api.admin.commons.dto.MenuNode;
import cn.ycc.api.admin.commons.exceptions.YccException;
import cn.ycc.api.admin.entity.YccMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface MenuService extends IService<YccMenu> {
    List<MenuNode> getMenuTree() throws YccException;
}
