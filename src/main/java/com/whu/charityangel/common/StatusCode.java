
package com.whu.charityangel.common;

public enum StatusCode {
    AUTH_ACCESS_DENIED("100", "用户或授权服务器拒绝授权", "Access denied"),
    AUTH_INVALID_CLIENT("101", "用户认证失败", "Invalid Client"),
    AUTH_INVALIDE_GRANT("102", "非法授权信息", "Invalid Grant"),
    AUTH_UNAUTHORIZED_CLIENT("103", "应用未被授权", "Unauthorized Client"),
    AUTH_NO_PERMISSON("104", "无权限访问该用户数据", "No permission to access user data"),
    AUTH_ID_DENIED("105", "请求来自未授权IP地址", "IP Access Denied"),
    AUTH_ERROR_TOKEN("106", "token无效或过期", "Expired or invalid JWT token"),
    AUTH_ERROR("107", "认证服务异常", "Authorization Exception"),
    MSG_OK("0", "成功", "Success"),
    MSG_ERROR("1", "失败", "Error"),
    MSG_UNKOWN_ERROR("2", "未知错误", "Unknown error"),
    MSG_SERVER_EXCEPTION("3", "服务器异常", "Server exception"),
    MSG_PARAMS_ERROR("4", "参数解析异常", "Params parse error"),
    MSG_PARAMS_MISSING("5", "缺少必要参数", "Params missing"),

    MSG_TENANT_IS_NOT_EXIST("200", "租户不存在", "tenant is not exist"),

    MSG_PARAM_PROJECT_ERROR("20", "规则不存在", "Project is not exist"),
    MSG_PARAM_PHASE_ERROR("21", "阶段不存在", "Phase is not exist"),
    MSG_DATA_SOURCE_RELATION("30", "其他字段数据来源依赖该字段，请删除数据来源", ""),

    DATABASE_DATA_EXISTS("620", "数据已存在", "Data already exits"),
    DATABASE_DUPLICATE_KEY("621", "标识重复", "Duplicate Key Exception"),
    MSG_DATABASE_ERROR("601", "数据库操作异常", "Database error occurred"),
    DATABASE_DATA_NOT_EXIST("610", "查询数据不存在", "Query data do not exist"),

    MSG_ROLE_EXIST("650", "角色已存在", "Role exist"),

    MSG_SYS_PROD_STEP_ERROR("701", "选择前后业务阶段必须相邻", "Last stepId and next stepId"),
    MSG_PROD_STEP_ERROR("702", "添加的业务阶段（项）与选择的前后的业务类型（项）一致，即同时为业务阶段或业务项", "Last stepId and next stepId"),
    MSG_PROD_STEP_NOT_EXIST("703", "选择业务阶段(项)不存在", "StepId is not exist"),
    MSG_SUB_STEP_MUST_HAVE_STEP_ID("704", "添加业务项必须至少选择一个相邻的业务项", "Add new sub step must have step id"),
    MSG_DELETE_RELATION_EXISTS("710", "字段关联存在无法删除","cannot delete, because field release exists"),

    MSG_MATERIAL_NOT_EXISTS("800", "原辅料不存在", "material is not exists"),
    MSG_PRODUCE_RECORD_NOT_EXISTS("801", "生产记录不存在", "produce is not exists"),

    MSG_PHASE_RELATION_EXIST("901", "业务阶段存在关联", "phase relation exist"),
    MSG_TDCODE_NOT_ACTIVE("1008", "溯源码未激活", "error"),
    MSG_NOT_CHECKT("1009", "产品检查未通过", "error"),

    MSG_PRODUCT_IS_NOT_EXIST("1000", "产品不存在", "query product is error"),

    MSG_JOB_TYPE_ERROR("1100", "不支持审核类型", "error"),



    MSG_JOB_TDCODE_PRODUCT_NOT_NULL("3001", "产品标识不能为空", "Product code is null"),
    MSG_JOB_TDCODE_PARAM_ERROR("3002", "参数错误", "param code is error"),
    MSG_JOB_TDCODE_QUERY_PRODUCT_ERROR("3003", "查询产品失败", "query product is error"),
    MSG_JOB_TDCODE_QUERY_NODEID_ERROR("3004", "查询数据失败", "query product is error"),


    MSG_NOTICE_CODE_NOT_NULL("4001", "查询消息标识不能为空", "query notice code is null"),
    MSG_NOTICE_INFO_ERROR("4002", "查询消息为空", "query notice info is null"),

    MSG_VALID_NUM("100", "字段校验失败", "input number")

            ;



    private String rtCode;
    private String msgCn;
    private String msgEn;

    StatusCode(String rtCode, String msgCn, String msgEn) {
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
}
