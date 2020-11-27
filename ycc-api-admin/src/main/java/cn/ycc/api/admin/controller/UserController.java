package cn.ycc.api.admin.controller;

import cn.ycc.api.admin.commons.annotation.LogInfo;
import cn.ycc.api.admin.commons.base.ResultBean;
import cn.ycc.api.admin.commons.exceptions.YccException;
import cn.ycc.api.admin.commons.shiro.po.LoginUser;
import cn.ycc.api.admin.commons.utils.LoginUserUtils;
import cn.ycc.api.admin.entity.YccUser;
import cn.ycc.api.admin.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUserInfo")
    @LogInfo("获取用户信息")
    public ResultBean getUserInfo(){
        LoginUser userInfo = LoginUserUtils.getLoginUserInfo(LoginUserUtils.getCurrentUser());
        return ResultBean.builder().code("200").data(userInfo).build();
    }

    @PostMapping("/login")
    @LogInfo("用户登录")
    public ResultBean login(@RequestBody YccUser user)throws YccException {
        String token = userService.login(user);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("token",token);

        return ResultBean.builder()
                .code("200")
                .data(resultMap)
                .build();
    }

    @PostMapping("/create")
    @RequiresRoles("Administrator")
    @LogInfo("创建用户")
    public ResultBean regist(@RequestBody YccUser user)throws YccException {
        if(ObjectUtils.isEmpty(user.getUsername())){
            throw new YccException("用户名不能为空");
        }
        if(ObjectUtils.isEmpty(user.getPassword())){
            throw new YccException("密码不能为空");
        }
        if(ObjectUtils.isEmpty(user.getRoleId())){
            throw new YccException("用户角色ID不能为空");
        }
        userService.registeUser(user);
        return ResultBean.builder()
                .code("200")
                .msg("注册成功")
                .build();
    }


}
