package cn.ycc.api.admin.service.impl;

import cn.ycc.api.admin.commons.exceptions.YccException;
import cn.ycc.api.admin.commons.utils.JwtUtis;
import cn.ycc.api.admin.commons.utils.PasswordEncoder;
import cn.ycc.api.admin.entity.YccUser;
import cn.ycc.api.admin.mapper.YccUserMapper;
import cn.ycc.api.admin.service.UserService;
import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserserviceImpl extends ServiceImpl<YccUserMapper,YccUser> implements UserService {

    @Override
    @Transactional
    public String login(YccUser user) throws YccException {

        YccUser dbUser = getBaseMapper().selectOne(new QueryWrapper<YccUser>().eq("username", user.getUsername()));
        if(dbUser == null){
            throw new YccException("用户不存在");
        }

        if(!dbUser.getPassword().equals(PasswordEncoder.encode(user.getPassword(),dbUser.getPassSalt()))){
            throw new YccException("密码错误");
        }
        Map<String, String> claimMap = new HashMap<>();
        claimMap.put("uid",dbUser.getId());

        return JwtUtis.createJwtStr(claimMap,7, TimeUnit.DAYS);
    }

    @Override
    public void registeUser(YccUser user) throws YccException {
        if(user == null){
            throw new YccException("注册用户不能为空");
        }
        String salt = UUID.randomUUID().toString().replace("-","");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setPassSalt(salt);
        user.setPassword(PasswordEncoder.encode(user.getPassword(),salt));
        getBaseMapper().insert(user);
    }
}
