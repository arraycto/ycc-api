package cn.ycc.api.admin.entity;

import cn.ycc.api.admin.commons.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("ycc_api_view_group_visit_log")
public class YccApiViewGroupVisitLog extends BaseEntity {

    private String viewGroupId;

    private String ipAddr;

    private String visitInfo;

    private Date lastCloseTime;

}