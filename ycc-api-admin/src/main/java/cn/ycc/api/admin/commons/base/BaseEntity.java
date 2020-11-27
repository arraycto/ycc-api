package cn.ycc.api.admin.commons.base;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;


@Data
public class BaseEntity {


    @TableId
    protected String id;
    protected Date createTime;
    protected Date updateTime;

    @TableLogic
    protected Integer del;
}