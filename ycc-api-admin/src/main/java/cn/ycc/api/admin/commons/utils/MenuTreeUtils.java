package cn.ycc.api.admin.commons.utils;

import cn.ycc.api.admin.commons.dto.MenuNode;
import cn.ycc.api.admin.entity.YccMenu;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

public abstract class MenuTreeUtils {

    public static List<MenuNode> buildMeneTree(List<YccMenu> menuList){
        if(ObjectUtils.isEmpty(menuList)){
            return Collections.EMPTY_LIST;
        }

        // 对menulist进行分组
        Map<String, List<YccMenu>> groupPostMap = menuList.stream().collect(Collectors.groupingBy(m -> m.getParentId()));

        // parentId为0 表示为父类根节点
        List<YccMenu> yccMenus = groupPostMap.get("0");
        Collections.sort(yccMenus,(m1,m2)->m1.getSort()-m1.getSort());

        // 便利得到MenuNodes
        List<MenuNode> menuNodes = new ArrayList<>();
        for (YccMenu yccMenu : yccMenus) {
            addMenuToMenuNodes(yccMenu,menuNodes,groupPostMap);
        }

        return menuNodes;
    }

    private static void addMenuToMenuNodes(YccMenu menu, List<MenuNode> resultMenuNode, Map<String, List<YccMenu>> groupPostMap) {
        //创建menuNode添加到结果集中
        MenuNode menuNode = new MenuNode();
        menuNode.setMenuId(menu.getId());
        menuNode.setMenuName(menu.getMenuName());
        menuNode.setMenuTheme(menu.getMenuTheme());
        menuNode.setRoutePath(menu.getRoutePath());
        menuNode.setIsParent(true);

        resultMenuNode.add(menuNode);

        // 得到子节点
        List<YccMenu> yccMenus = groupPostMap.get(menu.getId());
        if(ObjectUtils.isEmpty(yccMenus)){
            menuNode.setIsParent(false);
            menuNode.setChildren(Collections.EMPTY_LIST);
            return;
        }

        // 将子节点设置到父节点node中
        List<MenuNode> children = new ArrayList<>();
        menuNode.setChildren(children);

        Collections.sort(yccMenus,(m1,m2)->m1.getSort()-m1.getSort());
        // 构建子节点
        for (YccMenu yccMenu : yccMenus) {
            addMenuToMenuNodes(yccMenu,children,groupPostMap);
        }

    }
}
