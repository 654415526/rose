package com.lx.rose.retry.core.enums;

/**
 * @author LiXin
 * @version v1.0
 * @date 2021-04-03 22:16
 */
public enum RetryModeEnum {
    /**
     * 模板方式
     */
    TEMPLATE(0, "模板"),
    /**
     * 切面方式
     */
    ASPECT(1, "切面"),
    /**
     * 消息总线
     */
    EVENT_BUS(2, "消息");

    private Integer code;

    private String desc;

    RetryModeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
