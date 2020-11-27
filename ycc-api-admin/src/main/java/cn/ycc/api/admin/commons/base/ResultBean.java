package cn.ycc.api.admin.commons.base;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ResultBean implements Serializable {
    private String code;
    private String msg;
    private Object data;
}
