package cn.ycc.api.admin.entity;

import cn.ycc.api.admin.commons.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("ycc_api_projects")
public class YccApiProjects extends BaseEntity {

    private String appName;

    private String projectName;

    private String profileTestUrl;

    private String profileDevUrl;

}