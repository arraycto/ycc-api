package cn.ycc.api.admin.entity;

import cn.ycc.api.admin.commons.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("ycc_menu")
@Data
public class YccMenu extends BaseEntity {

    private String parentId;

    private String menuName;

    private String routePath;

    private String menuTheme;

    private String menuPermiPrifex;

    private Integer sort;
}