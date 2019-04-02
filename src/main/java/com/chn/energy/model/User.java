package com.chn.energy.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer id;

    private String userName;

    private String realName;

    private String passWord;

    private Integer role;

    private String roleName;

    private Integer deptId;

    private String deptName;

    private String telphone;

    private String email;

    private String createDate;

    private String updateDate;

    private Integer valid;

}