package cn.ycc.api.admin.entity;

import cn.ycc.api.admin.commons.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("ycc_test_his")
@Data
public class YccTestHis extends BaseEntity {

    private String apiInfoId;

    private String url;

    private String method;

    private String testReqContent;

    private String testReqHeaders;

    private String testResInfo;

}