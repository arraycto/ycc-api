package cn.ycc.api.admin.commons.shiro.po;

import cn.ycc.api.admin.commons.dto.MenuNode;
import cn.ycc.api.admin.entity.YccRole;
import cn.ycc.api.admin.entity.YccUser;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author yuchaoqun
 * @email chaoqunyu9311@163.com
 * @date 2020.11.24 21:39
 */
public class LoginUser implements Serializable {

    private static final long serialVersionUID = -5817442642138622513L;

    private YccUser yccUser;
    private YccRole role;
    private Set<String> userPerms;
    private Set<MenuNode> menuNodes;


    public LoginUser() {
    }

    public LoginUser(YccUser yccUser, YccRole role, Set<String> userPerms, Set<MenuNode> menuNodes) {
        this.yccUser = yccUser;
        this.role = role;
        this.userPerms = userPerms;
        this.menuNodes = menuNodes;
    }

    public Set<MenuNode> getMenuNodes() {
        return menuNodes;
    }

    public void setMenuNodes(Set<MenuNode> menuNodes) {
        this.menuNodes = menuNodes;
    }

    public YccUser getUser() {
        return yccUser;
    }

    public void setUser(YccUser yccUser) {
        this.yccUser = yccUser;
    }

    public Set<String> getUserPerms() {
        return userPerms;
    }

    public void setUserPerms(Set<String> userPerms) {
        this.userPerms = userPerms;
    }

    public YccRole getRole() {
        return role;
    }

    public void setRole(YccRole role) {
        this.role = role;
    }
}
