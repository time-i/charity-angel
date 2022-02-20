package com.whu.charityangel.common;

public enum SalixError {
    MSG_OK("0", "成功", "Success"),
    MSG_ERROR("1", "失败", "Error"),
    MSG_SERVER_EXCEPTION("2", "服务器异常", "Unknown error"),
    MSG_PARAMS_ERROR("3", "参数解析错误", "Server exception"),
    MSG_PARAMS_MISSING("4", "缺少必要参数", "Params parse error"),
    MSG_DATA_SOURCE_NULL("5", "数据来源为空", "data source null"),
    MSG_DATA_SOURCE_ERROR("6", "数据来源失败", "data source error"),
    MSG_PERMISSION_ERROR("7", "没有访问权限", "permission error"),
    MSG_PROJECT_ERROR("8", "该企业缺少必要数据规则", "permission error"),
    MSG_DATASOURCE_DEPLAY_ERROR("9", "依赖字段的数据来源设置错误", "permission error"),
    MSG_DEPLAY_FIELD_ERROR("10", "依赖字段设置错误", "permission error"),
    MSG_DEPLAY_FIELD_NULL("11", "依赖字段不存在", "permission error"),
    MSG_DATA_NOT_EXIST("12", "数据不存在", "data error"),
    MSG_USER_NOT_EXIST("13", "用户不存在", "user is not exist"),
    MSG_USER_GET_VCODE_ERROR("14", "获取溯源码失败", "get vocde error"),

    MSG_USER_NAME_PASSWORD_NULL("20", "用户名密码不能为空", "name or password is null"),
    MSG_USER_VCODE_TIMEOUT("21", "验证码已过期", "verify code time out"),
    MSG_USER_VCODE_NULL("22", "验证码不能为空", "verify code is null"),
    MSG_USER_VCODE_ERROR("22", "验证码不正确", "verify code error"),
    MSG_USER_VERIFY_ERROR("23", "用户认证失败", "verify user error"),
    MSG_USER_DISABLE("24", "用户被禁用", "user is disable"),
    MSG_USER_PASSWORD_ERROR("25", "密码不正确", "pwd is error"),
    MSG_USER_BOTH_PASSWORD_ERROR("26", "两次密码不一致", "both pwd is not the same"),
    MSG_USER_ACCOUNT_ERROR("27", "登录名设置不合法", "account name is error"),
    MSG_USER_PHONE_IS_NULL("28", "手机号不能为空", "phone is null"),
    MSG_USER_PHONE_IS_EXIST("29", "手机号已存在", "phone is exist"),
    MSG_USER_LOGINNAME_IS_EXIST("30", "登录名已存在", "login name is exist"),

    MSG_ENTERPRISE_ENTER_SUBMIT("100", "资料已提交审核，请耐心等待", "The data has been submitted for review, please be patient"),
    MSG_ENTERPRISE_ENTER_AUDIT("101", "贵公司资料处于审核过程中，请耐心等待", "Your company information is in the process of review, please be patient"),
    MSG_ENTERPRISE_ENTER_SUCCESS("102", "贵公司已入驻成功无需重复提交材料", "Enter successfully."),
    MSG_ENTERPRISE_ENTER_NOT_EXISTS("103","审核入驻任务不存在", "Enterprise enter job is not exist"),
    MSG_ENTERPRISE_ENTER_AUDIT_PASSED("104", "贵公司资料已审核通过，请勿重复提交", "The audit has been passed, please do not submit repeatedly"),

    MSG_PRODUCT_RULE_NOT_EXIST("200", "产品尚未绑定产品规则", "Product is not bind rule."),
    MSG_RULE_PRODUCT_NOT_EXIST("201", "该产品规则尚未绑定产品", "Product is not bind rule."),

    MSG_PRODUCT_CODE_ERROR("1000", "不存在该商品记录", "Product code is not exist"),
    MSG_PRODUCT_CODE_EXCEPTION("1007", "商品编码不合法", "Product code is illegal"),
    MSG_TRACE_DATA_ERROR("1001", "溯源数据不存在", "Trace data error"),
    MSG_BASE_DATA_NOT_EXIST("1002", "基础信息不存在", "BaseInfo is not exist!"),
    MSG_BASE_DATA_CONFIG_NOT_EXIST("1003", "租户信息不存在", "TenantId is not exists!"),
    MSG_DATABASE_ERROR("1004", "数据库操作异常", "Database error occurred"),
    MSG_STEPINFO_NOT_EXIST("1005", "业务项信息不存在", "StepInfo error occurred"),
    MSG_REQUEST_VERSION_TIMEOUT("1006", "查询版本超时", "Request version timeout"),
    MSG_PRODUCT_NOT_ACTIVE("1008", "溯源码未激活", "QRcode is not actived"),
    MSG_PRODUCT_UNQUALIFED("1009", "商品检测不合格", "unqualified"),
    MSG_NFC_UID_TXT_FORMAT_ERROR("1010", "文件格式应为txt", "txt format"),
    MSG_NFC_UID_BATCHID_NOT_MATCH("1011", "uid列表文件名应与批次号一致", "uid list file name mot match batchID"),
    MSG_NFC_UID_BATCHID_NOT_EXISTS("1012", "绑定批次号不存在", "batchId is not exists!"),
    RFID_NFC_MSG_BATCHID_NOT_EXIST("2002", "批次号不存在或该批次已激活", "batchId not exist"),





            ;

    private String rtCode;
    private String msgCn;
    private String msgEn;

    SalixError(String rtCode, String msgCn, String msgEn) {
        this.rtCode = rtCode;
        this.msgCn = msgCn;
        this.msgEn = msgEn;
    }

    public String getRtCode() {
        return rtCode;
    }

    public String getMsgCn() {
        return msgCn;
    }

    public String getMsgEn() {
        return msgEn;
    }

    public static String getMsgCnByCode(String code) {
        for(SalixError error: SalixError.values()) {
            if(code.equals(error.getRtCode())) {
                return error.getMsgCn();
            }
        }
        return "";
    }
}
