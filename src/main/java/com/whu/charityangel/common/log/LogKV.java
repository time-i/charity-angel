package com.whu.charityangel.common.log;



public class LogKV {

    private String key;
    private String value;

    public LogKV(String key, Object value) {
        this.key = key;
        if (value == null) {
            value = "";
        }
        this.value = value.toString();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
