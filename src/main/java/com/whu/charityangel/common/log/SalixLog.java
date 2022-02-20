
package com.whu.charityangel.common.log;

import java.util.ArrayList;
import java.util.List;


public class SalixLog {

    private static String DEFAULT_DLTAG = "undef";

    private static ThreadLocal<ExtElement> extElements = new ThreadLocal<ExtElement>() {
        @Override
        protected ExtElement initialValue() {
            return new ExtElement();
        };
    };

    private String dltag = "";
    private String cspanId = "";
    private List<LogKV> logElements = new ArrayList<LogKV>();

    public static String getTraceId() {
        return extElements.get().getTraceId();
    }

    public static String getSpanId() {
        return extElements.get().getSpanId();
    }

    public static void setTraceId(String traceId) {
        extElements.get().setTraceId(traceId);
    }

    public static void setSpanId(String spanId) {
        extElements.get().setSpanId(spanId);
    }

    public static String generatorNewSpanid() {
        String newSpanid = SpanIdGenerator.getSpanId();
        return newSpanid;
    }

    public static String genertorNewTraceid() {
        String traceid = SpanIdGenerator.getTraceId();
        return traceid;
    }

    public SalixLog setCspanId(String cspanId) {
        this.cspanId = cspanId;
        return this;
    }

    public SalixLog setDltag(String dltag) {
        this.dltag = dltag;
        return this;
    }

    public SalixLog add(String key, Object value) {
        LogKV logKV = new LogKV(key, value);
        logElements.add(logKV);

        return this;
    }

    public List<LogKV> getLogElements() {
        return logElements;
    }

    public void setLogElements(List<LogKV> logElements) {
        this.logElements = logElements;
    }

    public String getDltag() {
        if (dltag == null || dltag.equals("")) {
            return DEFAULT_DLTAG;
        }
        return dltag;
    }

    public String getCspanId() {
        return cspanId;
    }

    public static void remove() {
        extElements.remove();
    }

    public String toString() {
        ExtElement extElement = extElements.get();
        StringBuffer sb = new StringBuffer();
        if(!DEFAULT_DLTAG.equals(getDltag())) {
            sb.append(getDltag());
            sb.append("||traceid=");
        } else {
            sb.append("traceid=");
        }
        sb.append(extElement.getTraceId());
        sb.append("||spanid=");
        sb.append(extElement.getSpanId());
        sb.append("||cspanid=");
        sb.append(getCspanId());

        if (logElements.size() > 0) {
            for (LogKV param : logElements) {
                sb.append("||");
                sb.append(param.getKey());
                sb.append("=");
                sb.append(param.getValue());
            }
        }
        return sb.toString();
    }
}
