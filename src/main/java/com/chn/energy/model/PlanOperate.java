package com.chn.energy.model;

import lombok.Data;

/**
 * 操作封装类，用于页面按钮控制
 * Created by zhouxianwu on 2019/3/31.
 */
@Data
public class PlanOperate {
    //操作码
    private int code;
    //操作名称
    private String name;
    //路径
    private String url;
    //动作函数
    private String type;
}
