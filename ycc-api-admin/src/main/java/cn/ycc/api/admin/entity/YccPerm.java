package cn.ycc.api.admin.entity;

import cn.ycc.api.admin.commons.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("ycc_perm")
@Data
public class YccPerm extends BaseEntity {

    private Integer permType;

    private String menuId;

    private String permName;

    private String permCode;
}