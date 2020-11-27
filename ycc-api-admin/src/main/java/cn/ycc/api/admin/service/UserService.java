package cn.ycc.api.admin.service;

import cn.ycc.api.admin.commons.exceptions.YccException;
import cn.ycc.api.admin.entity.YccUser;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<YccUser> {
    String login(YccUser user) throws YccException;

    void registeUser(YccUser user) throws YccException;
}
