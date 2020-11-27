package cn.ycc.api.admin.commons.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class YccException extends RuntimeException {

    private static final long serialVersionUID = 5538106768351472490L;
    protected String code;

    public YccException() {
    }

    public YccException(String message) {
        this("500",message);
    }

    public YccException(String code,String message) {
        this(code,message,null);
    }

    public YccException(String code,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

}
