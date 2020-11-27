package cn.ycc.api.admin.entity;

import cn.ycc.api.admin.commons.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("ycc_api_info")
public class YccApiInfo extends BaseEntity {

    private String groupId;

    private String apiUrl;

    private String reqMethod;

    private String reqHeaders;

    private String reqDataType;

    private String reqContent;

    private String resDataType;

    private String resContent;

    private String projectId;

    private String apiDesc;

}