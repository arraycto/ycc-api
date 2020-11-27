package cn.ycc.api.admin.entity;

import cn.ycc.api.admin.commons.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@TableName("ycc_user")
@Data
public class YccUser extends BaseEntity {

    private String username;

    private String password;

    private String passSalt;

    private String roleId;

}