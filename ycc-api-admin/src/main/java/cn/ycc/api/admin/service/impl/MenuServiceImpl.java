package cn.ycc.api.admin.service.impl;

import cn.ycc.api.admin.commons.dto.MenuNode;
import cn.ycc.api.admin.commons.exceptions.YccException;
import cn.ycc.api.admin.commons.utils.MenuTreeUtils;
import cn.ycc.api.admin.entity.YccMenu;
import cn.ycc.api.admin.mapper.YccMenuMapper;
import cn.ycc.api.admin.service.MenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<YccMenuMapper, YccMenu> implements MenuService {

    @Override
    public List<MenuNode> getMenuTree() throws YccException {
        List<YccMenu> menus = baseMapper.selectList(new QueryWrapper<>());
        List<MenuNode> menuNodes = MenuTreeUtils.buildMeneTree(menus);
        return menuNodes;
    }
}
