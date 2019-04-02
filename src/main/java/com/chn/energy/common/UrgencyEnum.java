package com.chn.energy.common;

/**
 * Created by zhouxianwu on 2019/3/30.
 */
public enum UrgencyEnum {

    VURGENT(1,"非常紧急"),URGENCY(2,"紧急"),NORMAL(3,"一般"),NURGENCY(4,"不紧急");

    UrgencyEnum(int id, String name) {
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

    public static UrgencyEnum codeOf(int id){
        switch (id){
            case 1:
                return VURGENT;
            case 2:
                return URGENCY;
            case 3:
                return NORMAL;
            case 4:
                return NURGENCY;
            default:
                return NORMAL;
        }
    }
}
