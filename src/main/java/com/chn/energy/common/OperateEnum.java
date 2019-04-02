package com.chn.energy.common;

/**
 * Created by zhouxianwu on 2019/3/31.
 */
public enum OperateEnum {
    UPDATE(1,"修改","/plan/plan/toUpdate.do?id=","LINK"),
    DELETE(2,"删除","/plan/plan/delete.do?id=","DO"),
    PASS(3,"通过审核","/plan/plan/passPlan.do?id=","DO"),
    APPLYCHECK(4,"申请验收","/plan/plan/applyCheck.do?id=","DO"),
    PASSCHECK(5,"通过验收申请","/plan/plan/passCheck.do?id=","DO"),
    REJECTCHECK(6,"驳回验收申请","/plan/plan/rejectCheck.do?id=","DO"),
    APPLYCANCEL(7,"申请作废","/plan/plan/applyCancel.do?id=","DO"),
    PASSCANCEL(8,"通过作废申请","/plan/plan/passCancel.do?id=","DO"),
    APPLYDELAY(9,"申请延期","/plan/plan/applyDelay.do?id=","DO"),
    PASSDELAY(10,"通过延期申请","/plan/plan/passDelay.do?id=","DO");

    private int code;
    private String name;
    private String urlPrefix;
    private String type;

    OperateEnum(int code, String name, String urlPrefix, String type) {
        this.code = code;
        this.name = name;
        this.urlPrefix = urlPrefix;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static OperateEnum codeOf(int code){
        for(OperateEnum operateEnum:OperateEnum.class.getEnumConstants()){
            if (operateEnum.getCode() == code){
                return operateEnum;
            }
        }
        return null;
    }
}
