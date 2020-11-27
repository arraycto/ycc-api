package cn.ycc.api.admin.commons.shiro.realm;

import cn.ycc.api.admin.commons.dto.MenuNode;
import cn.ycc.api.admin.commons.shiro.JwtAutheticationToken;
import cn.ycc.api.admin.commons.shiro.JwtTokenAuthenticationException;
import cn.ycc.api.admin.commons.shiro.po.LoginUser;
import cn.ycc.api.admin.commons.utils.JwtUtis;
import cn.ycc.api.admin.commons.utils.LoginUserUtils;
import cn.ycc.api.admin.commons.utils.MenuTreeUtils;
import cn.ycc.api.admin.entity.YccMenu;
import cn.ycc.api.admin.entity.YccPerm;
import cn.ycc.api.admin.entity.YccRole;
import cn.ycc.api.admin.entity.YccUser;
import cn.ycc.api.admin.mapper.YccMenuMapper;
import cn.ycc.api.admin.service.PermissionService;
import cn.ycc.api.admin.service.RoleService;
import cn.ycc.api.admin.service.UserService;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yuchaoqun
 * @email chaoqunyu9311@163.com
 * @date 2020.11.23 18:58
 */
@Slf4j
public class CoutomRealm extends AuthorizingRealm {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private YccMenuMapper menuMapper;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        LoginUser loginUserInfo = LoginUserUtils.getLoginUserInfo(LoginUserUtils.getCurrentUser());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(Collections.singleton(loginUserInfo.getRole().getRoleCode()));
        simpleAuthorizationInfo.setStringPermissions(loginUserInfo.getUserPerms());
        return simpleAuthorizationInfo;
    }

    private LoginUser loadLoginUserInfo(String userId) {
        YccUser yccUser = userService.getById(userId);
        YccRole yccRole = roleService.getById(yccUser.getRoleId());

        List<YccPerm> permList = permissionService.getPermissionByRoleId(yccRole.getId());
        Set<String> permissionSet = permList.stream().map(YccPerm::getPermCode).collect(Collectors.toSet());

        List<YccMenu> yccMenus = menuMapper.getUserMenusByRoleId(yccRole.getId());
        List<MenuNode> menuNodes = MenuTreeUtils.buildMeneTree(yccMenus);
        return new LoginUser(yccUser,yccRole,permissionSet, new LinkedHashSet<>(menuNodes));
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if(authenticationToken instanceof JwtAutheticationToken){
            JwtAutheticationToken tokenHolder = (JwtAutheticationToken) authenticationToken;
            String jwtToken = tokenHolder.getToken();
            try {
                DecodedJWT decodedJWT = JwtUtis.parseJwt(jwtToken);
                String uid = decodedJWT.getClaim("uid").asString();
                LoginUserUtils.setCurrentUser(uid);

                LoginUser loginUserInfo = LoginUserUtils.getLoginUserInfo(LoginUserUtils.getCurrentUser());
                if(loginUserInfo == null){
                    loginUserInfo = loadLoginUserInfo(uid);
                    LoginUserUtils.setLoginUserInfo(loginUserInfo);
                }
            }catch (JWTVerificationException e){
                throw new JwtTokenAuthenticationException(e);
            }
        }
        return new SimpleAuthenticationInfo("a","a","Coutom");
    }


}
