package com.chn.energy.common;

/**
 * Created by zhouxianwu on 2019/3/30.
 */
public enum PlanStatusEnum {

    CREATE(1,"创建待审批"),APPROVED(2,"计划通过审核"),WAITINGCHECK(3,"待验收"),CHECKFAIL(4,"验收失败"),CHECKSUCCESS(5,"验收成功")
    ,TIMEOUT(6,"过期"),WAITINGDELAY(7,"申请延期"),WAITINGCANCEL(8,"申请作废"),CANCEL(9,"作废"),DELAY(7,"合理延期");

    PlanStatusEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private int  id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static PlanStatusEnum codeOf(int id){
        for (PlanStatusEnum planStatusEnum:PlanStatusEnum.class.getEnumConstants()){
            if (planStatusEnum.getId() == id){
                return planStatusEnum;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
