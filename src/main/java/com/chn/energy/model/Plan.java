package com.chn.energy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Plan {

    private Integer planId;

    private String planName;

    private String planContent;

    private Integer creator;

    private String creatorName;

    private Integer deptId;

    private String deptName;

    private Integer urgency;

    private String urgencyDesc;

    private Integer status;

    private String statusDesc;

    private String createTime;

    private String updateTime;

    private String startTime;

    private String endTime;

}