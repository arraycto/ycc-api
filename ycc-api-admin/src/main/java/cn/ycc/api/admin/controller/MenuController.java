package cn.ycc.api.admin.controller;

import cn.ycc.api.admin.commons.annotation.LogInfo;
import cn.ycc.api.admin.commons.base.ResultBean;
import cn.ycc.api.admin.commons.dto.MenuNode;
import cn.ycc.api.admin.entity.YccMenu;
import cn.ycc.api.admin.service.MenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/tree")
    @LogInfo("查询菜单树")
    public ResultBean getMenuTree(){
        List<MenuNode> menuTree = menuService.getMenuTree();
        return ResultBean.builder().code("200").data(menuTree).build();
    }

    @GetMapping("/{menuId}")
    @LogInfo("获取菜单详情")
    public ResultBean getMenuById(@PathVariable String menuId){
        YccMenu menu = menuService.getById(menuId);
        return ResultBean.builder().code("200").data(menu).build();
    }

    @GetMapping("/children/{parentId}")
    @LogInfo("查询菜单子节点")
    public ResultBean getMenuChildren(@PathVariable(name = "parentId") String parentId){
        List<YccMenu> menus = menuService.list(new QueryWrapper<YccMenu>().eq("parent_id",parentId));
        return ResultBean.builder().code("200").data(menus).build();
    }

    @PostMapping("")
    @RequiresRoles("Administarator")
    @LogInfo("更新菜单")
    public ResultBean updateMenu(@RequestBody YccMenu yccMenu){
        menuService.saveOrUpdate(yccMenu);
        return ResultBean.builder().code("200").msg("操作成功").build();
    }
}
