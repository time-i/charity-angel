package com.whu.charityangel.common.log;


public class ExtElement {

    private String traceId = "";
    private String spanId = "";

    public String getTraceId() {
        if(traceId == null || "".equals(traceId)) {
            traceId = SpanIdGenerator.getTraceId();
        }
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSpanId() {
        if (spanId == null || spanId.equals("")) {
            spanId = SpanIdGenerator.getSpanId();
        }
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

}
