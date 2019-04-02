package com.chn.energy.common;

/**
 * Created by zhouxianwu on 2019/3/30.
 */
public enum RoleEnum {

    PLAN(1,"计划员"),DIRECTOR(2,"主任"),LEADER(3,"领导"),ADMIN(4,"管理员");

    RoleEnum(int id, String name) {
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

    public static RoleEnum codeOf(int id){
        switch (id){
            case 1:
                return PLAN;
            case 2:
                return DIRECTOR;
            case 3:
                return LEADER;
            case 4:
                return ADMIN;
            default:
                return PLAN;
        }
    }
}
